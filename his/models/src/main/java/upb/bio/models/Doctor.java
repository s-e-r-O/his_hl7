package upb.bio.models;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Doctor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String givenName;
	private String familyName;
	
	public Doctor() {}
	
	public Doctor(String givenName, String familyName) {
		this.givenName = givenName;
		this.familyName = familyName;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
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
	
	public String getFullName() {
		return getGivenName() + " " + getFamilyName();
	}
	
	@Override
    public String toString() {
    	return getFullName();
    }
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
		      return true;
		    }
		    if (!(o instanceof Doctor)) {
		      return false;
		    }
		    Doctor p = (Doctor)o;
		    return id.equals(p.id);
	}
	
	@Override
	public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (id != null ? id.hashCode() : 0);
        return hash;
	}
}
