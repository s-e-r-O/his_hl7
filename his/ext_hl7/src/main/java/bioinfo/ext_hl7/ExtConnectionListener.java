package bioinfo.ext_hl7;

import ca.uhn.hl7v2.app.ConnectionListener;
import ca.uhn.hl7v2.app.Connection;

public class ExtConnectionListener implements ConnectionListener {
	
   public void connectionReceived(Connection theC) {
      System.out.println("New connection received: " + theC.getRemoteAddress().toString());
   }
	
   public void connectionDiscarded(Connection theC) {
      System.out.println("Lost connection from: " + theC.getRemoteAddress().toString());
   }
	
}
