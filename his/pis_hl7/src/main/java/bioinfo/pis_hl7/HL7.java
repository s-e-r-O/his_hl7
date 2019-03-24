package bioinfo.pis_hl7;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v24.message.ORM_O01;
import ca.uhn.hl7v2.model.v24.segment.OBR;
import ca.uhn.hl7v2.model.v24.segment.ORC;
import ca.uhn.hl7v2.parser.Parser;
import upb.bio.global.HL7Binders;
import upb.bio.global.NetworkConstants;
import upb.bio.models.Patient;

public class HL7 {
	
	public static void sendO01Message(Patient patient) throws Exception {
		ORM_O01 orm = new ORM_O01();
		orm.initQuickstart("ORM", "O01", "P");
		HL7Binders.bind(orm.getPATIENT().getPID(), patient);
		
		String actualDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMddHHmmss", Locale.ENGLISH));
		ORC orc = orm.getORDER().getORC();
		orc.getPlacerOrderNumber().getEi1_EntityIdentifier().setValue(actualDate);
		OBR obr = orm.getORDER().getORDER_DETAIL().getOBR();
		obr.getPlacerOrderNumber().getEi1_EntityIdentifier().setValue(actualDate);
		obr.getUniversalServiceIdentifier().getCe1_Identifier().setValue("algo");
		obr.getObr6_RequestedDateTime().getTimeOfAnEvent().setValue(actualDate);

		sendMessage(orm);
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
