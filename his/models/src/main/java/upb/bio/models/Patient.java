package upb.bio.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ca.uhn.hl7v2.model.v24.segment.PID;

@Entity
public class Patient {
	@Id
	private int id;
	private String setIDPID;
    private String familyName;
    private String givenName;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
    
    
    public Patient() {}
    
    public Patient(String givenName, String familyName, Date birthDate) {
    	this.setFamilyName(familyName);
    	this.setGivenName(givenName);
    	this.setBirthDate(birthDate);
    }
    
    public Patient(PID p) {
    	this.setSetIDPID(p.getSetIDPID().getValue());
    	this.setFamilyName(p.getPatientName(0).getFamilyName().getSurname().getValue());
    	this.setGivenName(p.getPatientName(0).getGivenName().getValue());
    	try {
    		this.setBirthDate(p.getDateTimeOfBirth().getTimeOfAnEvent().getValueAsDate());    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
    
    public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date date) {
		this.birthDate = date;
	}

	public String getSetIDPID() {
		return setIDPID;
	}

	public void setSetIDPID(String setIdPid) {
		this.setIDPID = setIdPid;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
		      return true;
		    }
		    if (!(o instanceof Patient)) {
		      return false;
		    }
		    Patient p = (Patient)o;
		    return setIDPID.equals(p.setIDPID);
	}
	
	@Override
	public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (setIDPID != null ? setIDPID.hashCode() : 0);
        return hash;
	}
}
