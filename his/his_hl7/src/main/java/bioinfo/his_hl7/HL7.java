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
import ca.uhn.hl7v2.model.v24.message.ADT_A05;
import ca.uhn.hl7v2.model.v24.segment.EVN;
import ca.uhn.hl7v2.model.v24.segment.MSH;
import ca.uhn.hl7v2.model.v24.segment.PID;
import ca.uhn.hl7v2.model.v24.segment.PV1;
import ca.uhn.hl7v2.parser.Parser;
import upb.bio.global.HL7Binders;
import upb.bio.global.NetworkConstants;
import upb.bio.models.Consultation;
import upb.bio.models.Doctor;
import upb.bio.models.Patient;

public class HL7 {
	public static void test_client() throws Exception {
		int port = 1012; // The port to listen on
        boolean useTls = false; // Should we use TLS/SSL?
        HapiContext context = new DefaultHapiContext();
    	// Create a message to send
    	String msg = "MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A04|12345|P|2.4\r"
    		+ "PID|0001|00009874|00001122|A00977|SMITH^JOHN^M|MOM|19581119|F|NOTREAL^LINDA^M|C|564 SPRING ST^^NEEDHAM^MA^02494^US|0002|(818)565-1551|(425)828-3344|E|S|C|0000444444|252-00-4414||||SA|||SA||||NONE|V1|0001||D.ER^50A^M110^01|ER|P00055|11B^M011^02|070615^BATMAN^GEORGE^L|555888^NOTREAL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^NOTREAL^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|199904101200||||5555112333|||666097^NOTREAL^MANNY^P\r"
    		+ "NK1|0222555|NOTREAL^JAMES^R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|||||||ORGANIZATION\r"
    	    + "PV1|0001|I|D.ER^1F^M950^01|ER|P000998|11B^M011^02|070615^BATMAN^GEORGE^L|555888^OKNEL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^VOICE^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|||||5555112333|||666097^DNOTREAL^MANNY^P\r"
    	    + "PV2|||0112^TESTING|55555^PATIENT IS NORMAL|NONE|||19990225|19990226|1|1|TESTING|555888^NOTREAL^BOB^K^DR^MD||||||||||PROD^003^099|02|ER||NONE|19990225|19990223|19990316|NONE\r"
    	    + "AL1||SEV|001^POLLEN\r"
    	    + "GT1||0222PL|NOTREAL^BOB^B||STREET^OTHER STREET^CITY^ST^77787|(444)999-3333|(222)777-5555||||MO|111-33-5555||||NOTREAL GILL N|STREET^OTHER STREET^CITY^ST^99999|(111)222-3333\r"
    	    + "IN1||022254P|4558PD|BLUE CROSS|STREET^OTHER STREET^CITY^ST^00990||(333)333-6666||221K|LENIX|||19980515|19990515|||PATIENT01 TEST D||||||||||||||||||02LL|022LP554";
    	Parser p = context.getPipeParser();
    	Message adt = p.parse(msg);
    	
    	// A connection object represents a socket attached to an HL7 server
    	Connection connection = context.newClient("localhost", port, useTls);
    	
    	// The initiator is used to transmit unsolicited messages
    	Initiator initiator = connection.getInitiator();
    	Message response = initiator.sendAndReceive(adt);
    	String responseString = p.encode(response);
    	System.out.println("Received response:\n" + responseString);
    	
    	connection.close();
	}
	
	
	public static void sendA04Message(Patient patient, Doctor doctor, Date date, String type) throws Exception {
		ADT_A01 adt = new ADT_A01();
		adt.initQuickstart("ADT", "A01", "P");
		
		MSH mshSegment = adt.getMSH();
		mshSegment.getSendingApplication().getNamespaceID().setValue("PatientConsultRegistrationSystem");
		mshSegment.getSequenceNumber().setValue("123"); //change this
		
		EVN evn = adt.getEVN();
		//evn.getEventTypeCode().setValue("A04");
        evn.getRecordedDateTime().getTimeOfAnEvent().setValue(date);
		
        HL7Binders.bind(adt.getPID(), patient);
		
		HL7Binders.bind(adt.getPV1(), new Consultation(date, patient, doctor, type));
		
		sendMessage(adt);
	}
	
	public static void sendA05Message(Patient patient, Doctor doctor, Date date, String type) throws Exception {
		ADT_A05 adt = new ADT_A05();
		adt.initQuickstart("ADT", "A05", "P");
		
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
		pid.getMaritalStatus().getCe1_Identifier().setValue(patient.getMaritalStatus());
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
        
        HL7Binders.bind(adt.getPID(), consult.getPatient());
		
		HL7Binders.bind(adt.getPV1(), consult);
		
		sendMessage(adt);
	}
	
	public static void sendMessage(Message message) throws Exception {
		int port = 1012; // The port to listen on
        boolean useTls = false; // Should we use TLS/SSL?
        HapiContext context = new DefaultHapiContext();
    	    	
    	// A connection object represents a socket attached to an HL7 server
    	Connection connection = context.newClient("localhost", port, useTls);
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
