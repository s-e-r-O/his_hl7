package bioinfo.pis_hl7;

import java.util.HashMap;
import java.util.Map;

import upb.bio.models.Patient;

public class ScheduleManager {
	
	private Server s;
	
	private Map<Patient, Object> visits;
	
	public ScheduleManager() {
    	s = new Server(); 
	}
	
	public void init() {
		s.init();
	}
	
	public Map<Patient, Object> getVisits(){
		if (visits == null) {
			visits = new HashMap<Patient, Object>();
		}
		return visits;
	}
	public void setVisits(Map<Patient, Object> visits){
		this.visits = visits;
	}
	
	public void putVisit(Patient p) {
		if (!getVisits().containsKey(p)) {
			getVisits().put(p, null);
		}
		System.out.println("Visits:");
		for(Map.Entry<Patient, Object> entry : getVisits().entrySet()) {
			System.out.println("Patient: (" + entry.getKey().getSetIDPID() + ") " + entry.getKey().getGivenName() + " " + entry.getKey().getFamilyName());	
		}
	}
	
}
