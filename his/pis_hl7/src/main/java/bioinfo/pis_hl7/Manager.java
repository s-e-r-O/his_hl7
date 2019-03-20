package bioinfo.pis_hl7;

import java.util.HashMap;
import java.util.Map;

import upb.bio.models.Patient;

public class Manager {
	
	private Map<Patient, Object> visits;
	
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
		getVisits().put(p, null);
		System.out.println("Visits:");
		for(Map.Entry<Patient, Object> entry : getVisits().entrySet()) {
			System.out.println("Patient: (" + entry.getKey().getId() + ") " + entry.getKey().getGivenName() + " " + entry.getKey().getFamilyName());	
		}
	}
	
}
