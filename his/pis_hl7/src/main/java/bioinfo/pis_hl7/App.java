package bioinfo.pis_hl7;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Server s;
    	try {
        	s = new Server();
        	s.init();
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
