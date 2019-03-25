package bioinfo.his_hl7;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import upb.bio.models.Doctor;
import bioinfo.dal_hl7.CRUDService;

public class DoctorManager {
		
	private List<Doctor> doctors;
	private CRUDService<Doctor> service;
	private List<ConsultRegistrationFrame> observers;
	
	private static DoctorManager handler;
	
	public static DoctorManager getInstance() {
		if (handler == null) {
			handler = new DoctorManager();
		}
		return handler;
	}
	
    private DoctorManager() 
    {
    	service = new CRUDService<Doctor>();
    	doctors = service.get("from Doctor");
    	observers = new ArrayList<ConsultRegistrationFrame>();
    } 
  	
	
	public List<Doctor> getPatients() {
		return doctors;
	}
	
	public void registerObserver(ConsultRegistrationFrame jFrame) {
		this.observers.add(jFrame);
	}
	
}
