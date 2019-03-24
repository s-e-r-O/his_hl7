package bioinfo.his_hl7;

import java.util.Date;
import java.util.List;

import upb.bio.models.Patient;
import bioinfo.dal_hl7.CRUDService;

public class PatientRegistrationHandler {
		
	private List<Patient> patients;
	private CRUDService<Patient> service;
	private static PatientRegistrationHandler handler;
	
	public static PatientRegistrationHandler getInstance() {
		if (handler == null) {
			handler = new PatientRegistrationHandler();
		}
		return handler;
	}
	
    private PatientRegistrationHandler() 
    {
    	service = new CRUDService<Patient>();
    	patients = service.get("from Patient");        
    } 
  	
	public Patient registerNewPatient(String givenName, String familyName, Date birthDate, char gender, String address, String phone, MaritalStatus maritalStatus) {
		Patient patient = new Patient(givenName, familyName, birthDate, gender, address, phone, maritalStatus.toString());
		Integer id = service.save(patient);
		patient.setId(id);
		patients.add(patient); //falta ver que paciente no esta registrado
		return patient;
	}
}

enum MaritalStatus {
	Annulled,
	Divorced,
	Interlocutory,
	LegallySeparated,
	Married,
	Polygamous,
	NeverMarried,
	DomesticPartner,
	Unmarried,
	Widowed,
	Unknown
}
