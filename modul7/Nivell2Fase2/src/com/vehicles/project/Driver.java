package com.vehicles.project;

import java.util.Date;

public class Driver extends Person {

	private DrivingLicense drivingLicense;

	public Driver(String firstname, String lastname, Date birthDate, DrivingLicense drivingLicense) throws Exception {
		super(firstname, lastname, birthDate);

		if (birthDate.after(Utilities.addSubstractYears(birthDate, -18)))
			throw new Exception("El conductor ha de ser major d'edat (18 anys).");
		if (drivingLicense == null)
			throw new Exception("El conductor ha de tenir llicència de conduir.");

		this.drivingLicense = drivingLicense;
	}

}
