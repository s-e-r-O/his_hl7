package bioinfo.pis_hl7.requests;

import bioinfo.pis_hl7.HL7;
import upb.bio.models.Patient;

public class OrderManager {
	private Patient patient;
	
	public OrderManager(Patient patient) {
		setPatient(patient);
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public void sendRequest(String[] orders) {
		if (orders != null && orders.length > 0) {
			try {
				HL7.sendO01Message(patient, orders);			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
