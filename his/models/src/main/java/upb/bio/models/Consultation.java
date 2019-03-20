package upb.bio.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v24.message.ADT_A05;
@Entity
public class Consultation {
	@Id
	private int id;
	
	@ManyToMany
	@JoinColumn(name = "Patient_id")
	private String patientId;
	
	@ManyToMany
	@JoinColumn(name = "Doctor_id")
	private int doctorId;
	
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date consultationDate;
	
	
	
	public Consultation() {}
	
	public Consultation(Date consultationDate, String patientId, int doctorId ) {
		this.setConsultationDate(consultationDate);
		this.setPatientId(patientId);
		this.setDoctorId(doctorId);
	}
	
	public Consultation(ADT_A05 adt) throws DataTypeException {
		this.setPatientId(adt.getPID().getSetIDPID().getValue());
		this.setConsultationDate(adt.getPV1().getAdmitDateTime().getTimeOfAnEvent().getValueAsDate());
		this.setDoctorId(adt.getPV1().getConsultingDoctorReps());
	}
	
	
	
	public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
    
    public Date getConsultationDate() {
    	return consultationDate;
    }
    
    public void setConsultationDate(Date consultationDate) {
    	this.consultationDate = consultationDate;
    }
    
    public String getPatientId() {
        return patientId;
    }
 
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    
    public int getDoctorId() {
        return doctorId;
    }
 
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
	
}
