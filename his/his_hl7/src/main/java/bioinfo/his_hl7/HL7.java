package bioinfo.his_hl7;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.Parser;

public class HL7 {
	public static void test_client() throws Exception {
		int port = 1011; // The port to listen on
        boolean useTls = false; // Should we use TLS/SSL?
        HapiContext context = new DefaultHapiContext();
    	// Create a message to send
    	String msg = "MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A01|12345|P|2.2\r"
    		+ "PID|0001|00009874|00001122|A00977|SMITH^JOHN^M|MOM|19581119|F|NOTREAL^LINDA^M|C|564 SPRING ST^^NEEDHAM^MA^02494^US|0002|(818)565-1551|(425)828-3344|E|S|C|0000444444|252-00-4414||||SA|||SA||||NONE|V1|0001|I|D.ER^50A^M110^01|ER|P00055|11B^M011^02|070615^BATMAN^GEORGE^L|555888^NOTREAL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^NOTREAL^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|199904101200||||5555112333|||666097^NOTREAL^MANNY^P\r"
    		+ "NK1|0222555|NOTREAL^JAMES^R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|||||||ORGANIZATION\r"
    	    + "PV1|0001|I|D.ER^1F^M950^01|ER|P000998|11B^M011^02|070615^BATMAN^GEORGE^L|555888^OKNEL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^VOICE^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|||||5555112333|||666097^DNOTREAL^MANNY^P\r"
    	    + "PV2|||0112^TESTING|55555^PATIENT IS NORMAL|NONE|||19990225|19990226|1|1|TESTING|555888^NOTREAL^BOB^K^DR^MD||||||||||PROD^003^099|02|ER||NONE|19990225|19990223|19990316|NONE\r"
    	    + "AL1||SEV|001^POLLEN\r"
    	    + "GT1||0222PL|NOTREAL^BOB^B||STREET^OTHER STREET^CITY^ST^77787|(444)999-3333|(222)777-5555||||MO|111-33-5555||||NOTREAL GILL N|STREET^OTHER STREET^CITY^ST^99999|(111)222-3333\r"
    	    + "IN1||022254P|4558PD|BLUE CROSS|STREET^OTHER STREET^CITY^ST^00990||(333)333-6666||221K|LENIX|||19980515|19990515|||PATIENT01 TEST D||||||||||||||||||02LL|022LP554";
    	Parser p = context.getPipeParser();
    	Message adt = p.parse(msg);
    	
    	// Remember, we created our HAPI Context above like so:
    	// HapiContext context = new DefaultHapiContext();
    	
    	// A connection object represents a socket attached to an HL7 server
    	Connection connection = context.newClient("localhost", port, useTls);
    	
    	// The initiator is used to transmit unsolicited messages
    	Initiator initiator = connection.getInitiator();
    	Message response = initiator.sendAndReceive(adt);
    	
    	String responseString = p.encode(response);
    	System.out.println("Received response:\n" + responseString);
    	
    	/*
    	 * MSH|^~\&|||||20070218200627.515-0500||ACK|54|P|2.2 MSA|AA|12345
    	 */
    	
    	/*
    	 * If you want to send another message to the same destination, it's fine
    	 * to ask the context again for a client to attach to the same host/port.
    	 * The context will be smart about it and return the same (already
    	 * connected) client Connection instance, assuming it hasn't been closed.
    	 */
    	connection = context.newClient("localhost", port, useTls);
    	initiator = connection.getInitiator();
    	response = initiator.sendAndReceive(adt);
    	
    	/*
    	 * Close the connection when you are done with it. If you are designing a
    	 * system which will continuously send out messages, you may want to
    	 * consider not closing the connection until you have no more messages to
    	 * send out. This is more efficient, as most (if not all) HL7 receiving
    	 * applications are capable of receiving lots of messages in a row over
    	 * the same connection, even with a long delay between messages.
    	 * 
    	 * See
    	 * http://hl7api.sourceforge.net/xref/ca/uhn/hl7v2/examples/SendLotsOfMessages.html 
    	 * for an example of this.
    	 */
    	connection.close();
	}
}
