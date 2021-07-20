package com.vehicles.project.domain;

import java.util.Date;

import com.vehicles.project.utils.Utilities;

public class Driver extends Person {

	private DrivingLicense drivingLicense;

	public Driver(String firstname, String lastname, Date birthDate, DrivingLicense drivingLicense) throws Exception {
		super(firstname, lastname, birthDate);

		if (birthDate.after(Utilities.addSubstractYears(new Date(), -18)))
			throw new Exception("El conductor ha de ser major d'edat (18 anys).");
		if (drivingLicense == null)
			throw new Exception("El conductor ha de tenir llicència de conduir.");

		this.drivingLicense = drivingLicense;
	}

	public DrivingLicense getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(DrivingLicense drivingLicense) {
		this.drivingLicense = drivingLicense;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append(super.toString());
		str.append(", drivingLicense: ");
		str.append(drivingLicense.getType());

		return str.toString();
	}

}
