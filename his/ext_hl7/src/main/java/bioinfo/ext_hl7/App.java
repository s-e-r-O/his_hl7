package bioinfo.ext_hl7;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.HL7Service;
import ca.uhn.hl7v2.protocol.ReceivingApplication;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	int port = 1011; // The port to listen on
    	boolean useTls = false; // Should we use TLS/SSL?
    	HapiContext context = new DefaultHapiContext();
    	HL7Service server = context.newServer(port, useTls);
    	
    	ReceivingApplication radiologyHandler = new RadiologyReceiverApplication();
    	ReceivingApplication laboratoryHandler = new LaboratoryReceiverApplication();
    	ReceivingApplication pharmacyHandler = new PharmacyReceiverApplication();
    	server.registerApplication("OML", "O21", laboratoryHandler);
    	server.registerApplication("ORM", "O01", radiologyHandler);
    	server.registerApplication("RDE", "O11", pharmacyHandler);
    	    	
    	server.registerConnectionListener(new ExtConnectionListener());    	
    	
    	// Start the server listening for messages
    	server.startAndWait();
    }
}
