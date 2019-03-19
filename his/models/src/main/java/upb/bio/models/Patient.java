package upb.bio.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Patient {
	@Id
	private int id;
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
}
