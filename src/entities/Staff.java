package entities;

import java.io.Serializable;
/**
 * Staff class represents the employees working at the restaurant who are assigned to serve customers.
 *
 */
public class Staff implements Serializable {
	private static final long serialVersionUID = 4743545238689522397L;
	public enum GenderType {GENDER_DEFAULT, GENDER_MALE, GENDER_FEMALE};
	private int staffId;
	private String name;
	private String jobTitle;
	private GenderType gender;
	
	public Staff(int staffId, String name, String jobTitle, GenderType gender) { 
		this.staffId = staffId;
		this.name = name;
		this.jobTitle = jobTitle;
		this.gender = gender;
	}
	
	public int getStaffId() {
		return staffId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public String toString() {
		return this.getName() + "(ID : " + this.getStaffId() + " )";
	}
	
}
