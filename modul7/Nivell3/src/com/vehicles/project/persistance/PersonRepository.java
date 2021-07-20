package com.vehicles.project.persistance;

import java.util.ArrayList;
import java.util.List;

import com.vehicles.project.domain.Person;

public class PersonRepository {

	private List<Person> persons = new ArrayList<Person>();

	public List<Person> getAllPersons() {
		return this.persons;
	}

	public Person getPersonById(int id) {
		return this.persons.get(id);
	}

	public void addPerson(Person person) {
		persons.add(person);
	}

}
