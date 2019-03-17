package bioinfo.far_hl7;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.HL7Service;
import ca.uhn.hl7v2.protocol.ReceivingApplication;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	/*
    	 * Before we can send, let's create a server to listen for incoming
    	 * messages. The following section of code establishes a server listening
    	 * on port 1011 for new connections.
    	 */
    	int port = 1011; // The port to listen on
    	boolean useTls = false; // Should we use TLS/SSL?
    	HapiContext context = new DefaultHapiContext();
    	HL7Service server = context.newServer(port, useTls);
    	
    	/*
    	 * The server may have any number of "application" objects registered to
    	 * handle messages. We are going to create an application to listen to
    	 * ADT^A01 messages.
    	 * 
    	 * You might want to look at the source of ExampleReceiverApplication
    	 * (it's a nested class below) to see how it works.
    	 */
    	ReceivingApplication handler = new ExampleReceiverApplication();
    	server.registerApplication("ADT", "A01", handler);
    	
    	/*
    	 * We are going to register the same application to handle ADT^A02
    	 * messages. Of course, we coud just as easily have specified a different
    	 * handler.
    	 */
    	server.registerApplication("ADT", "A02", handler);
    	
    	/*
    	 * Another option would be to specify a single application to handle all
    	 * messages, like this:
    	 * 
    	 * server.registerApplication("*", "*", handler);
    	 */
    	
    	/*
    	 * If you want to be notified any time a new connection comes in or is
    	 * lost, you might also want to register a connection listener (see the
    	 * bottom of this class to see what the listener looks like). It's fine
    	 * to skip this step.
    	 */
    	//server.registerConnectionListener(new MyConnectionListener());
    	
    	/*
    	 * If you want to be notified any processing failures when receiving,
    	 * processing, or responding to messages with the server, you can 
    	 * also register an exception handler. (See the bottom of this class
    	 * to see what the listener looks like. ) It's also fine to skip this
    	 * step, in which case exceptions will simply be logged. 
    	 */
    	//server.setExceptionHandler(new MyExceptionHandler());
    	
    	// Start the server listening for messages
    	server.startAndWait();
    	
    	/*
    	 * Note: if you don't want to wait for the server to initialize itself, it
    	 * can start in the background:
    	 */
    	
    	// server.start();
    }
}