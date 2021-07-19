package com.vehicles.project;

import java.util.Date;

public abstract class Person {

	protected String firstname;
	protected String lastname;
	protected Date birthDate;

	public Person(String firstname, String lastname, Date birthDate) throws Exception {
		if (birthDate.after(new Date()))
			throw new Exception("La data de naixement no pot ser posterior al día d'avui.");

		this.firstname = firstname;
		this.lastname = lastname;
		this.birthDate = birthDate;
	}

}
