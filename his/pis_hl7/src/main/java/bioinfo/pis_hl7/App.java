package bioinfo.pis_hl7;

public class App 
{
	private static Manager manager;
	public static Manager getManager() {
		if (manager == null) {
			manager = new Manager();
		}
		return manager;
	}
	
	public static void main( String[] args )
    {
    	Server s = new Server();
    	s.init();
    	
    }
}
