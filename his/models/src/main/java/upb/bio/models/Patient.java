package upb.bio.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ca.uhn.hl7v2.model.v24.segment.PID;

@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String setIDPID;
    private String familyName;
    private String givenName;
    private char gender;
    private String address;
    private String phone;
    private String maritalStatus;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
        
    public Patient() {}
    
    public Patient(String givenName, String familyName, Date birthDate, char gender, String address, String phone, String maritalStatus) {
    	this.setFamilyName(familyName);
    	this.setGivenName(givenName);
    	this.setBirthDate(birthDate);
    	this.setGender(gender);
    	this.setAddress(address);
    	this.setPhone(phone);
    	this.setMaritalStatus(maritalStatus);
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
    
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
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
	
	public char getGender() {
		return gender;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getMaritalStatus() {
		return maritalStatus;
	}
	
	public void setMaritalStatus(String status) {
		this.maritalStatus = status;
	}

	public String getSetIDPID() {
		return setIDPID;
	}

	public void setSetIDPID(String setIdPid) {
		this.setIDPID = setIdPid;
	}
	
	public String getFullName() {
		return givenName + " " + familyName;
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
		    return id.equals(p.id);
	}
	
	@Override
	public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (id != null ? id.hashCode() : 0);
        return hash;
	}
}
