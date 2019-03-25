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
	
	public void sendRequest(String[] orders, OrderType o) {
		if (orders != null && orders.length > 0) {
			try {
				switch(o) {
				case Radiology:
					HL7.sendO01Message(patient, orders);
					return;
				case Laboratory:
					HL7.sendO21Message(patient, orders);
					return;
				case Pharmacy:
					HL7.sendRDEO01Message(patient, orders);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
