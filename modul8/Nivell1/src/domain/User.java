package domain;

import java.util.Date;

public class User {

	private int id;
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private Date regitrationDate;

	private static int COUNTER = 1;

	public User() {
	}

	public User(String username, String firstname, String lastname, String password) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.regitrationDate = new Date();

		id = COUNTER;
		COUNTER++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegitrationDate() {
		return regitrationDate;
	}

	public void setRegitrationDate(Date regitrationDate) {
		this.regitrationDate = regitrationDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", password=" + password + ", regitrationDate=" + regitrationDate + "]";
	}
}
