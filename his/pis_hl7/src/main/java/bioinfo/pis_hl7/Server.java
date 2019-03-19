package bioinfo.pis_hl7;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.HL7Service;
import ca.uhn.hl7v2.protocol.ReceivingApplication;

public class Server {
	public void init() {
		int port = 1012; // The port to listen on
    	boolean useTls = false; // Should we use TLS/SSL?
    	HapiContext context = new DefaultHapiContext();
    	HL7Service server = context.newServer(port, useTls);
    	
    	ReceivingApplication medicalHandler = new MedicalReceiverApplication();
    	server.registerApplication("ADT", "A01", medicalHandler);
    	server.registerApplication("ADT", "A04", medicalHandler);
    	
    	try {
    		// Start the server listening for messages
    		server.startAndWait();    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
}
