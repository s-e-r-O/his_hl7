package upb.bio.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Consultation {
	@Id
	private int id;
	
	@ManyToMany
	@JoinColumn(name = "Patient_id")
	private int patientId;
	
	@ManyToMany
	@JoinColumn(name = "Doctor_id")
	private int doctorId;
	
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date consultationDate;
	
	public Consultation() {}
	
	public Consultation(Date consultationDate, int patientId, int doctorId ) {
		this.setConsultationDate(consultationDate);
		this.setPatientId(patientId);
		this.setDoctorId(doctorId);
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
    
    public int getPatientId() {
        return patientId;
    }
 
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    
    public int getDoctorId() {
        return doctorId;
    }
 
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
	
}
