package domain;

import java.util.Date;

public class User {

	private int id;
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private Date registrationDate;

	private static int COUNTER = 1;

	public User() {
	}

	public User(String firstname, String lastname, String username, String password) throws Exception {
		if(username.isBlank() || firstname.isBlank() || lastname.isBlank() || password.isBlank())
			throw new Exception("--> T'has deixat algún camp en blanc. <--");
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.registrationDate = new Date();

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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", password=" + password + ", registrationDate=" + registrationDate + "]";
	}
}
