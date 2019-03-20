package bioinfo.his_hl7;

import java.util.Date;
import java.util.List;

import upb.bio.models.Patient;
import bioinfo.dal_hl7.CRUDService;

public class PatientRegistrationHandler {
		
	private List<Patient> patients;
	private CRUDService<Patient> service;
	
    public PatientRegistrationHandler() 
    {
    	service = new CRUDService<Patient>();
    	patients = service.get("from Patient");        
    } 
  	
	public Patient registerNewPatient(String givenName, String familyName, Date birthDate) {
		Patient patient = new Patient(givenName, familyName, birthDate);
		Long id = service.save(patient);
		patient.setId(id);
		patients.add(patient); //falta ver que paciente no esta registrado
		return patient;
	}
}
