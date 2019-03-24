package bioinfo.pis_hl7;

import java.awt.EventQueue;

public class App 
{
	private static Server s;
	private static ScheduleFrame frame;
	private static ScheduleFrame getFrame() {
		if (frame == null) {
			frame = new ScheduleFrame();
		}
		return frame;
	}
	public static ScheduleManager getManager() {
		return getFrame().getManager();
	}
	
	public static void main( String[] args )
    {
		s = new Server();
		s.init();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
