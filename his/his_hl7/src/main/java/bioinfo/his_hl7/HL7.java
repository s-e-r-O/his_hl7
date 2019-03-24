package bioinfo.his_hl7;

import java.util.Date;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v24.datatype.XCN;
import ca.uhn.hl7v2.model.v24.message.ADT_A01;
import ca.uhn.hl7v2.model.v24.message.ADT_A03;
import ca.uhn.hl7v2.model.v24.segment.EVN;
import ca.uhn.hl7v2.model.v24.segment.MSH;
import ca.uhn.hl7v2.model.v24.segment.PID;
import ca.uhn.hl7v2.model.v24.segment.PV1;
import ca.uhn.hl7v2.parser.Parser;
import upb.bio.global.NetworkConstants;
import upb.bio.models.Consultation;
import upb.bio.models.Doctor;
import upb.bio.models.Patient;

public class HL7 {
	
	public static void sendA04Message(Patient patient, Doctor doctor, Date date, String type) throws Exception {
		ADT_A01 adt = new ADT_A01();
		adt.initQuickstart("ADT", "A04", "P");
		
		MSH mshSegment = adt.getMSH();
		mshSegment.getSendingApplication().getNamespaceID().setValue("PatientConsultRegistrationSystem");
		mshSegment.getSequenceNumber().setValue("123"); //change this
		
		EVN evn = adt.getEVN();
		//evn.getEventTypeCode().setValue("A04");
        evn.getRecordedDateTime().getTimeOfAnEvent().setValue(date);
		
		PID pid = adt.getPID(); 
		pid.getPatientName(0).getFamilyName().getSurname().setValue(patient.getFamilyName());
		pid.getPatientName(0).getGivenName().setValue(patient.getGivenName());
		pid.getPatientAddress(0).getStreetAddress().getStreetName().setValue(patient.getAddress());
		pid.getPhoneNumberHome(0).getPhoneNumber().setValue(patient.getPhone());
		pid.getMaritalStatus(); //USAME!!
		pid.getAdministrativeSex().setValue(patient.getGender()+"");
		pid.getPatientIdentifierList(0).getID().setValue(patient.getId()+""); //do we need a PID?
	
		PV1 pv1 = adt.getPV1();
		pv1.getPatientClass().setValue(type);
		pv1.insertConsultingDoctor(0);
		XCN doc = pv1.getConsultingDoctor(0);
		doc.getGivenName().setValue(doctor.getGivenName());
		doc.getFamilyName().getSurname().setValue(doctor.getFamilyName());
		pv1.getConsultingDoctor(0).getIDNumber().setValue(doctor.getId()+"");
		
		sendMessage(adt);
	}
	
	public static void sendA11Message(Consultation consult) throws Exception {
		ADT_A03 adt = new ADT_A03();
		adt.initQuickstart("ADT", "A11", "P");
		
		MSH mshSegment = adt.getMSH();
		mshSegment.getSendingApplication().getNamespaceID().setValue("PatientConsultRegistrationSystem");
		mshSegment.getSequenceNumber().setValue("123"); //change this
		
		EVN evn = adt.getEVN();
		//evn.getEventTypeCode().setValue("A04");
        evn.getRecordedDateTime().getTimeOfAnEvent().setValue(consult.getConsultationDate());
        
		PID pid = adt.getPID(); 
		pid.getPatientName(0).getFamilyName().getSurname().setValue(consult.getPatient().getFamilyName());
		pid.getPatientName(0).getGivenName().setValue(consult.getPatient().getGivenName());
		pid.getPatientAddress(0).getStreetAddress().getStreetName().setValue(consult.getPatient().getAddress());
		pid.getPhoneNumberHome(0).getPhoneNumber().setValue(consult.getPatient().getPhone());
		pid.getMaritalStatus(); //USAME!!
		pid.getAdministrativeSex().setValue(consult.getPatient().getGender()+"");
		pid.getPatientIdentifierList(0).getID().setValue(consult.getPatient().getId()+"");
		
		PV1 pv1 = adt.getPV1();
		pv1.getPatientClass().setValue(consult.getType());
		pv1.insertConsultingDoctor(0);
		XCN doc = pv1.getConsultingDoctor(0);
		doc.getGivenName().setValue(consult.getDoctor().getGivenName());
		doc.getFamilyName().getSurname().setValue(consult.getDoctor().getFamilyName());
		pv1.getConsultingDoctor(0).getIDNumber().setValue(consult.getDoctor().getId()+"");
		
		sendMessage(adt);
	}
	
	public static void sendMessage(Message message) throws Exception {
		boolean useTls = false; // Should we use TLS/SSL?
        HapiContext context = new DefaultHapiContext();
    	    	
    	// A connection object represents a socket attached to an HL7 server
    	Connection connection = context.newClient(NetworkConstants.PIS_REMOTE_HOST, NetworkConstants.PIS_REMOTE_PORT, useTls);
    	Parser p = context.getPipeParser();
    	
    	// The initiator is used to transmit unsolicited messages
    	Initiator initiator = connection.getInitiator();
    	Message response = initiator.sendAndReceive(message);
    	String responseString = p.encode(response);
    	System.out.println("Received response:\n" + responseString);
    	
    	connection.close();
    	context.close();
	}
}
