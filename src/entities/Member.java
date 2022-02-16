package entities;

import java.io.Serializable;
/**
 * This class defines members of the restauarant
 * @author ISHITA SARAF
 *
 */
public class Member implements Serializable {
	private static final long serialVersionUID = 4743545238689522397L;
	private int memberId;
	private String name;
	private String contact;
	
	public Member(int memberId, String name, String contact) { 
		this.memberId = memberId;
		this.name = name;
		this.contact = contact;
	}
	
	public int getMemberId() {
		return memberId;
	}

	public String getName() {
		return name;
	}

	public String getContact() {
		return contact;
	}
	
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
}