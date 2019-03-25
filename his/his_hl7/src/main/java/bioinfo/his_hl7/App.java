package bioinfo.his_hl7;

import java.awt.EventQueue;
import java.util.Date;

import bioinfo.dal_hl7.CRUDService;
import upb.bio.models.Doctor;
import upb.bio.models.Patient;
import upb.bio.models.Consultation.ConsultTypes;

public class App 
{	
    public static void main(String[] args) throws Exception { 
    	PatientManager patHandler = PatientManager.getInstance();
    	ConsultManager conHandler = ConsultManager.getInstance();
    	
    	//register patient
    	/*Patient newPatient = patHandler.registerNewPatient("Isabella", "Defilippis", new Date("01/08/1997"), 'F', "Av 123", "1234567", MaritalStatus.NeverMarried);
    	conHandler.registerEmergencyConsult(newPatient, newDoctor);*/
    	Doctor newDoctor = new Doctor("Dr", "Seuss");
    	CRUDService<Doctor> service = new CRUDService<Doctor>();
    	Integer id = service.save(newDoctor);
    	newDoctor.setId(id);
    	
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