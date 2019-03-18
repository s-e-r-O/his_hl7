package upb.bio.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Patient {
	@Id
	private int id;
    private String firstname;
    private String lastname;
    
    public Patient() {}
    
    public Patient(int id, String firstname, String lastname) {
    	this.setId(id);
    	this.setFirstname(firstname);
    	this.setLastname(lastname);
    }
    
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
    
    public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
