package bioinfo.pis_hl7;

import upb.bio.models.Consultation;

public class VisitManager {
	private Consultation consultation;

	public Consultation getConsultation() {
		return consultation;
	}

	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}
	
	public VisitManager(Consultation c) {
		this.setConsultation(c);
	}
}
