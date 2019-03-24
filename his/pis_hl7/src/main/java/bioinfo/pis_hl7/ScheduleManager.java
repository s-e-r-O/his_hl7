package bioinfo.pis_hl7;

import java.util.ArrayList;
import java.util.List;

import upb.bio.models.Consultation;

public class ScheduleManager {
	
	private List<ScheduleFrame> observers;
	private List<Consultation> visits;
	
	public ScheduleManager() {
    	 
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

	public void putVisit(Consultation c) {
		getVisits().add(c);
		for (ScheduleFrame sf : observers) {
			sf.putVisit(c);
		}
	}
	
	public void subscribe(ScheduleFrame sf) {
		getObservers().add(sf);
	}
	
}
