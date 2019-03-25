package bioinfo.his_hl7;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bioinfo.dal_hl7.CRUDService;
import upb.bio.models.Consultation;
import upb.bio.models.Doctor;
import upb.bio.models.Patient;

public class ConsultManager {

	private List<Consultation> consults;
	private CRUDService<Consultation> service;
	private List<ConsultCancellationFrame> observers;
	
	private static ConsultManager manager;
	
	private ConsultManager () {
		service = new CRUDService<Consultation>();
		consults = service.get("from Consultation");
		observers = new ArrayList<ConsultCancellationFrame>();
	}
	
	public static ConsultManager getInstance() {
		if (manager == null) {
			manager = new ConsultManager();
		}
		return manager;
	}
	
	public void registerConsult(Patient patient, Doctor doctor, Date date, ConsultTypes type) {
		Consultation consult = new Consultation(date, patient, doctor, type.toString());
		Integer id = service.save(consult);
		consult.setId(id);
		consults.add(consult);
		notifyAll(consult);
		try {
			switch (type) {
				case Routine:
					HL7.sendA05Message(patient, doctor, date, type.toString());
					break;
				case Emergency:
					HL7.sendA04Message(patient, doctor, date, type.toString());
					break;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public List<Consultation> getConsults() {
		return consults;
	}
	
	public void cancelConsult(Consultation consult) {
		service.delete(consult);
		try {
			HL7.sendA11Message(consult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void registerObserver(ConsultCancellationFrame frame) {
		observers.add(frame);
	}
	
	private void notifyAll(Consultation consult) {
		for (ConsultCancellationFrame frame: observers) {
			frame.addConsult(consult);
		}
	}
}

enum ConsultTypes{
	Emergency, Routine;
}