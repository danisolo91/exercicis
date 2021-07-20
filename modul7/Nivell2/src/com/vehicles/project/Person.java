package com.vehicles.project;

import java.util.Date;

public abstract class Person {

	protected String firstname;
	protected String lastname;
	protected Date birthDate;

	public Person(String firstname, String lastname, Date birthDate) throws Exception {
		if (firstname.matches(".*\\d+.*"))
			throw new Exception("Els noms de les persones no contenen números.");
		if (lastname.matches(".*\\d+.*"))
			throw new Exception("Els cognoms no contenen números.");
		if (birthDate.after(new Date()))
			throw new Exception("La data de naixement no pot ser posterior al día d'avui.");

		this.firstname = firstname;
		this.lastname = lastname;
		this.birthDate = birthDate;
	}

	public String getFullname() {
		return firstname + " " + lastname;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
}
