package com.vehicles.project;

import java.util.Date;

public class VehicleOwner extends Person {

	private DrivingLicense drivingLicense;
	private boolean carInsurance;
	private boolean garage;

	public VehicleOwner(String firstname, String lastname, Date birthDate, DrivingLicense drivingLicense,
			boolean carInsurance, boolean garage) throws Exception {
		super(firstname, lastname, birthDate);

		if (birthDate.after(Utilities.addSubstractYears(new Date(), -18)))
			throw new Exception("El titular ha de ser major d'edat (18 anys).");
		if (drivingLicense == null)
			throw new Exception("El titular ha de tenir llicència de conduir.");

		this.drivingLicense = drivingLicense;
		this.carInsurance = carInsurance;
		this.garage = garage;
	}

	public DrivingLicense getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(DrivingLicense drivingLicense) {
		this.drivingLicense = drivingLicense;
	}

	public boolean isCarInsurance() {
		return carInsurance;
	}

	public void setCarInsurance(boolean carInsurance) {
		this.carInsurance = carInsurance;
	}

	public boolean isGarage() {
		return garage;
	}

	public void setGarage(boolean garage) {
		this.garage = garage;
	}

}
