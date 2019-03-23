package bioinfo.his_hl7;

import java.util.Date;

import bioinfo.dal_hl7.CRUDService;
import upb.bio.models.Doctor;
import upb.bio.models.Patient;

public class App 
{	
    public static void main(String[] args) throws Exception { 
    	PatientRegistrationHandler patHandler = new PatientRegistrationHandler();
    	ConsultManager conHandler = new ConsultManager();
    	
    	//register patient
    	Patient newPatient = patHandler.registerNewPatient("Isabella", "Defilippis", new Date("01/08/1997"));
    	
    	Doctor newDoctor = new Doctor("Dr", "Seuss");
    	CRUDService<Doctor> service = new CRUDService<Doctor>();
    	Long id = service.save(newDoctor);
    	newDoctor.setId(id);
    	conHandler.registerEmergencyConsult(newPatient, newDoctor);
    }
}