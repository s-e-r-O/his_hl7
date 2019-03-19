package bioinfo.his_hl7;

import java.util.Date;

import upb.bio.models.Patient;
import bioinfo.dal_hl7.Database;

public class PatientRegistrationHandler {
	
	public void registerNewPatient(String givenName, String familyName, Date birthDate) {
		Patient patient = new Patient(givenName, familyName, birthDate);
		Database.saveToDB(patient);
	}
}
