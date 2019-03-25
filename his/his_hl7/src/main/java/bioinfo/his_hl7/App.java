package bioinfo.his_hl7;

import java.awt.EventQueue;
import java.util.Date;

import bioinfo.dal_hl7.CRUDService;
import upb.bio.models.Doctor;
import upb.bio.models.Patient;

public class App 
{	
    public static void main(String[] args) throws Exception { 
    	PatientManager patHandler = PatientManager.getInstance();
    	ConsultManager conHandler = ConsultManager.getInstance();
    	
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthFrame frame = new AuthFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}