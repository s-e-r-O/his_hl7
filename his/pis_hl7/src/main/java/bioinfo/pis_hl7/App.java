package bioinfo.pis_hl7;

public class App 
{
	private static ScheduleManager manager;
	public static ScheduleManager getManager() {
		if (manager == null) {
			manager = new ScheduleManager();
		}
		return manager;
	}
	
	public static void main( String[] args )
    {
		getManager().init();
    }
}
