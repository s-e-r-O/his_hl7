package upb.bio.models;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Consultation {
	
	/*public enum ConsultTypes{
		Emergency('E'), Routine('R');
		
		 public char asChar() {
		        return asChar;
		    }

		    private final char asChar;

		    ConsultTypes(char asChar) {
		        this.asChar = asChar;
		    }
	}*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "Patient_id")
	private Patient patient;
	
	@OneToOne
	@JoinColumn(name = "Doctor_id")
	private Doctor doctor;
	
	private String type;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date consultationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishedAt;
	
	private String diagnosis;
	
	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Date getFinishedAt() {
		return finishedAt;
	}

	public void setFinishedAt(Date finishedAt) {
		this.finishedAt = finishedAt;
	}

	public Date getDischargedAt() {
		return dischargedAt;
	}

	public void setDischargedAt(Date dischargedAt) {
		this.dischargedAt = dischargedAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	private Date dischargedAt;
	
		
	public Consultation() {}
	
	public Consultation(Date consultationDate, Patient patient, Doctor doctor, String type) {
		this.setConsultationDate(consultationDate);
		this.setPatient(patient);
		this.setDoctor(doctor);
		this.setType(type);
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
    
    public String getType() {
    	return type;
    }
    
    public void setType(String type) {
    	this.type = type;
    }
    
    @Override
    public String toString() {
    	LocalDateTime ldt = LocalDateTime.ofInstant(getConsultationDate().toInstant(), ZoneId.systemDefault());
    	return getPatient().getFullName() + " (" + ldt.format(DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH)) +")";
    }
	
}
