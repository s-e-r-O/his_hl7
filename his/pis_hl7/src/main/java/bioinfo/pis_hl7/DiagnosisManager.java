package bioinfo.pis_hl7;

import bioinfo.dal_hl7.CRUDService;
import upb.bio.models.Consultation;

public class DiagnosisManager {
	Consultation consultation;
	CRUDService<Consultation> service;
	public DiagnosisManager(Consultation c) {
		consultation = c;
		service = new CRUDService<Consultation>();
	}
	public void updateDiagnosis(String diag) {
		consultation.setDiagnosis(diag);
		service.update(consultation);
	}
}
