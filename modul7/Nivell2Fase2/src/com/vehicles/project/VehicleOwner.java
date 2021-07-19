package com.vehicles.project;

import java.util.Date;

public class VehicleOwner extends Person {

	private DrivingLicense drivingLicense;
	private boolean hasCarInsurance;
	private boolean hasGarage;

	public VehicleOwner(String firstname, String lastname, Date birthDate, DrivingLicense drivingLicense,
			boolean hasCarInsurance, boolean hasGarage) throws Exception {
		super(firstname, lastname, birthDate);

		if (birthDate.after(Utilities.addSubstractYears(birthDate, -18)))
			throw new Exception("El titular ha de ser major d'edat (18 anys).");
		if (drivingLicense == null)
			throw new Exception("El titular ha de tenir llicència de conduir.");
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthDate = birthDate;
		this.drivingLicense = drivingLicense;
		this.hasCarInsurance = hasCarInsurance;
		this.hasGarage = hasGarage;
	}

}
