package bioinfo.pis_hl7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import upb.bio.models.Consultation;
import upb.bio.models.Patient;

public class ScheduleManager {
	
	private Server s;
	private List<ScheduleFrame> observers;
	private List<Consultation> visits;
	
	public ScheduleManager() {
    	s = new Server(); 
	}
	
	public void init() {
		s.init();
	}
	
	public List<Consultation> getVisits(){
		if (visits == null) {
			visits = new ArrayList<Consultation>();
		}
		return visits;
	}
	public void setVisits(List<Consultation> visits){
		this.visits = visits;
	}
	
	public List<ScheduleFrame> getObservers() {
		if (observers == null) {
			observers = new ArrayList<ScheduleFrame>();
		}
		return observers;
	}

	public void setObservers(List<ScheduleFrame> observers) {
		this.observers = observers;
	}

	public void putVisit(Patient p) {
		Consultation c = new Consultation();
		c.setPatient(p);
		getVisits().add(c);
		for (ScheduleFrame sf : observers) {
			sf.action(c);
		}
	}
	
	public void subscribe(ScheduleFrame sf) {
		getObservers().add(sf);
	}
	
}
