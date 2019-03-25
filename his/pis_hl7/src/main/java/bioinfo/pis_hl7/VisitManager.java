package bioinfo.pis_hl7;

import java.util.Date;

import bioinfo.dal_hl7.CRUDService;
import upb.bio.models.Consultation;

public class VisitManager {
	private Consultation consultation;
	private CRUDService<Consultation> service;

	public VisitManager(Consultation c) {
		this.setConsultation(c);
		this.service = new CRUDService<Consultation>();
	}
	
	public Consultation getConsultation() {
		return consultation;
	}

	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}
	
	public void dischargeConsult() {
		this.consultation.setDischargedAt(new Date());
		this.service.update(consultation);
		try {
			HL7.sendA03Message(consultation);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void finalizeConsult() {
		this.consultation.setFinishedAt(new Date());
		this.service.update(consultation);
	}
}
