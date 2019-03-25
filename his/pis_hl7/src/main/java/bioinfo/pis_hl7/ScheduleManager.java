package bioinfo.pis_hl7;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import bioinfo.dal_hl7.CRUDService;
import upb.bio.models.Consultation;
import upb.bio.models.Patient;

public class ScheduleManager {
	private CRUDService<Consultation> service;
	private List<ScheduleFrame> observers;
	private List<Consultation> visits;
	
	public ScheduleManager() {
    	service = new CRUDService<Consultation>();
	}
	
	public List<Consultation> getVisits(){
		if (visits == null) {
	    	visits = service.get("from Consultation");
	    	visits.removeIf(new Predicate<Consultation>() {
				@Override
				public boolean test(Consultation visit) {
					return !visit.getArrived() || visit.getFinishedAt() != null;
				}
			});
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
