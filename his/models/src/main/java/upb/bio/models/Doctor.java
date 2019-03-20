package upb.bio.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Doctor {
	@Id
	private int id;
	private String givenName;
	private String familyName;
	
	public Doctor() {}
	
	public Doctor(String givenName, String familyName) {
		this.givenName = givenName;
		this.familyName = familyName;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getGivenName() {
		return this.givenName;
	}
	
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	public String getFamilyName() {
		return this.familyName;
	}
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
}
