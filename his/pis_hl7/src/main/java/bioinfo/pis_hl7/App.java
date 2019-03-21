package bioinfo.pis_hl7;

import java.awt.EventQueue;

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleFrame window = new ScheduleFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
