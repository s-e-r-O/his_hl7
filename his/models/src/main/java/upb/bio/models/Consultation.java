package upb.bio.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
public class Consultation {
	
	public enum ConsultTypes{
		Emergency('E'), Routine('R');
		
		 public char asChar() {
		        return asChar;
		    }

		    private final char asChar;

		    ConsultTypes(char asChar) {
		        this.asChar = asChar;
		    }
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "Patient_id")
	private Patient patient;
	
	@OneToOne
	@JoinColumn(name = "Doctor_id")
	private Doctor doctor;
	
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date consultationDate;
	
	private ConsultTypes type;
	
	

	
	
	public Consultation() {}
	
	public Consultation(Date consultationDate, Patient patient, Doctor doctor, ConsultTypes type) {
		this.setConsultationDate(consultationDate);
		this.setPatient(patient);
		this.setDoctor(doctor);
		this.setType(type);
	}
	
	
	public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getConsultationDate() {
    	return consultationDate;
    }
    
    public void setConsultationDate(Date consultationDate) {
    	this.consultationDate = consultationDate;
    }
    
    public Patient getPatient() {
        return patient;
    }
 
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public Doctor getDoctor() {
        return doctor;
    }
 
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    public ConsultTypes getType() {
        return type;
    }
 
    public void setType(ConsultTypes type) {
        this.type = type;
    }
	
}

