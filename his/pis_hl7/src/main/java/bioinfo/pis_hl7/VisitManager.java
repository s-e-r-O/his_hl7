package bioinfo.pis_hl7;

import upb.bio.models.Patient;

public class VisitManager {
	private Patient patient;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
