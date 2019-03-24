package bioinfo.his_hl7;

import java.util.Date;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v24.message.ADT_A01;
import ca.uhn.hl7v2.model.v24.message.ADT_A05;
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
		
		// Populate the MSH Segment
		MSH mshSegment = adt.getMSH();
		mshSegment.getSendingApplication().getNamespaceID().setValue("PatientConsultRegistrationSystem");
		mshSegment.getSequenceNumber().setValue("123"); //change this
		
		// Populate the PID Segment
		PID pid = adt.getPID(); 
		pid.getPatientName(0).getFamilyName().getSurname().setValue(patient.getFamilyName());
		pid.getPatientName(0).getGivenName().setValue(patient.getGivenName());
		pid.getPatientIdentifierList(0).getID().setValue(patient.getId()+""); //do we need a PID?
	
		PV1 pv1 = adt.getPV1();
		pv1.getPatientClass().setValue(type);
		
		sendMessage(adt);
	}
	
	public static void sendA11Message(Consultation consult) throws Exception {
		ADT_A03 adt = new ADT_A03();
		adt.initQuickstart("ADT", "A11", "P");
		
		// Populate the MSH Segment
		MSH mshSegment = adt.getMSH();
		mshSegment.getSendingApplication().getNamespaceID().setValue("PatientConsultRegistrationSystem");
		mshSegment.getSequenceNumber().setValue("123"); //change this
				
		//finish!
		
		sendMessage(adt);
	}
	
	public static void sendA05Message(Patient patient, Doctor doctor, Date date, String type) throws Exception {
		ADT_A05 adt = new ADT_A05();
		adt.initQuickstart("ADT", "A05", "P");
		
		// Populate the MSH Segment
		MSH mshSegment = adt.getMSH();
		mshSegment.getSendingApplication().getNamespaceID().setValue("PatientConsultRegistrationSystem");
		mshSegment.getSequenceNumber().setValue("123");
		
		// Populate the PID Segment
		PID pid = adt.getPID(); 
		pid.getPatientName(0).getFamilyName().getSurname().setValue(patient.getFamilyName());
		pid.getPatientName(0).getGivenName().setValue(patient.getGivenName());
		pid.getPatientIdentifierList(0).getID().setValue(patient.getId()+""); //do we need a PID?
	
		PV1 pv1 = adt.getPV1();
		pv1.getAdmissionType().setValue(type);
		
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
