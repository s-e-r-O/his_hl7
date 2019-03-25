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
	
	public void registerEmergencyConsult(Patient patient, Doctor doctor) {
		Date actualDate = new Date();
		Consultation consult = new Consultation(actualDate, patient, doctor, ConsultTypes.Emergency.toString());
		Integer id = service.save(consult);
		consult.setId(id);
		consults.add(consult);
		notifyAll(consult);
		try {
			HL7.sendA04Message(patient, doctor, actualDate, ConsultTypes.Emergency.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void registerRoutineConsult(Patient patient, Doctor doctor, Date date) {
		Consultation consult = new Consultation(date, patient, doctor, ConsultTypes.Routine.toString());
		Integer id = service.save(consult);
		consult.setId(id);
		consults.add(consult);
		try {
			HL7.sendA04Message(patient, doctor, date, ConsultTypes.Routine.toString());
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
	
	public void registerArrival(Consultation consult) {
		try 
		{
			HL7.sendA04Message(consult.getPatient(), consult.getDoctor(), consult.getConsultationDate(), consult.getType());
			consult.setArrived(true);
		}
		catch (Exception e) {
			
		}
		
			
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