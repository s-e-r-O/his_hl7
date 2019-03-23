package bioinfo.his_hl7;

import java.util.Date;
import java.util.List;

import bioinfo.dal_hl7.CRUDService;
import upb.bio.models.Consultation;
import upb.bio.models.Doctor;
import upb.bio.models.Patient;

public class ConsultRegistrationHandler {

	private List<Consultation> consults;
	private CRUDService<Consultation> service;
	
	public ConsultRegistrationHandler () {
		service = new CRUDService<Consultation>();
		consults = service.get("from Consultation");
	}
	
	public void registerEmergencyConsult(Patient patient, Doctor doctor) {
		Date actualDate = new Date();
		Consultation consult = new Consultation(actualDate, patient, doctor);
		Long id = service.save(consult);
		consult.setId(id);
		consults.add(consult);
		try {
			HL7.sendA04Message(patient, doctor, actualDate, ConsultTypes.Emergency.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

enum ConsultTypes{
	Emergency, Routine;
}