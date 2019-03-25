package bioinfo.pis_hl7;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v24.group.OML_O21_ORDER_GENERAL;
import ca.uhn.hl7v2.model.v24.group.ORM_O01_ORDER;
import ca.uhn.hl7v2.model.v24.message.ADT_A03;
import ca.uhn.hl7v2.model.v24.message.OML_O21;
import ca.uhn.hl7v2.model.v24.message.ORM_O01;
import ca.uhn.hl7v2.model.v24.message.RDE_O11;
import ca.uhn.hl7v2.model.v24.segment.EVN;
import ca.uhn.hl7v2.model.v24.segment.MSH;
import ca.uhn.hl7v2.model.v24.segment.OBR;
import ca.uhn.hl7v2.model.v24.segment.ORC;
import ca.uhn.hl7v2.parser.Parser;
import upb.bio.global.HL7Binders;
import upb.bio.global.NetworkConstants;
import upb.bio.models.Consultation;
import upb.bio.models.Patient;

public class HL7 {
	
	public static void sendO01Message(Patient patient, String[] details) throws Exception {
		ORM_O01 orm = new ORM_O01();
		orm.initQuickstart("ORM", "O01", "P");
		HL7Binders.bind(orm.getPATIENT().getPID(), patient);
		
		String actualDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMddHHmmss", Locale.ENGLISH));
		for (int i = 0; i < details.length; i++) {
			ORM_O01_ORDER order = orm.insertORDER(i);
			ORC orc = order.getORC();
			orc.getOrderControl().setValue("NW");
			orc.getPlacerOrderNumber().getEi1_EntityIdentifier().setValue(actualDate);
			OBR obr = order.getORDER_DETAIL().getOBR();
			obr.getSetIDOBR().setValue(Integer.toString(i+1));
			obr.getPlacerOrderNumber().getEi1_EntityIdentifier().setValue(actualDate);
			obr.getUniversalServiceIdentifier().getCe1_Identifier().setValue(details[i]);
			obr.getObr6_RequestedDateTime().getTimeOfAnEvent().setValue(actualDate);			
		}

		sendMessage(orm);
	}
	
	public static void sendO21Message(Patient patient, String[] details) throws Exception {
		OML_O21 oml = new OML_O21();
		oml.initQuickstart("OML", "O21", "P");
		HL7Binders.bind(oml.getPATIENT().getPID(), patient);
		
		String actualDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMddHHmmss", Locale.ENGLISH));
		for (int i = 0; i < details.length; i++) {
			OML_O21_ORDER_GENERAL order = oml.insertORDER_GENERAL(i);
			ORC orc = order.getORDER().getORC();
			orc.getOrderControl().setValue("NW");
			orc.getPlacerOrderNumber().getEi1_EntityIdentifier().setValue(actualDate);
			OBR obr = order.getORDER().getOBSERVATION_REQUEST().getOBR();
			obr.getSetIDOBR().setValue(Integer.toString(i+1));
			obr.getPlacerOrderNumber().getEi1_EntityIdentifier().setValue(actualDate);
			obr.getUniversalServiceIdentifier().getCe1_Identifier().setValue(details[i]);
			obr.getObr6_RequestedDateTime().getTimeOfAnEvent().setValue(actualDate);			
		}

		sendMessage(oml);
	}
	
	public static void sendA03Message(Consultation consult) throws Exception {
		ADT_A03 adt = new ADT_A03();
		adt.initQuickstart("ADT", "A03", "P");
		
		MSH mshSegment = adt.getMSH();
		mshSegment.getSendingApplication().getNamespaceID().setValue("PhysicianInformationSystem");
		
		String actualDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMddHHmmss", Locale.ENGLISH));
		EVN evn = adt.getEVN();
		//evn.getEventTypeCode().setValue("A04");
        evn.getRecordedDateTime().getTimeOfAnEvent().setValue(actualDate);
        
        HL7Binders.bind(adt.getPID(), consult.getPatient());
        HL7Binders.bind(adt.getPV1(), consult);
        
        sendMessage(adt);
	}
	
	public static void sendRDEO01Message(Consultation consult, String[] meds, String[] amounts) throws Exception {
		RDE_O11 rde = new RDE_O11();
		rde.initQuickstart("RDE", "O11", "P");
		sendMessage(rde);
	}

	
	public static void sendMessage(Message message) throws Exception {
		boolean useTls = false; // Should we use TLS/SSL?
        HapiContext context = new DefaultHapiContext();
    	    	
    	// A connection object represents a socket attached to an HL7 server
    	Connection connection = context.newClient(NetworkConstants.EXT_REMOTE_HOST, NetworkConstants.EXT_REMOTE_PORT, useTls);
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
