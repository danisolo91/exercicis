package com.vehicles.project.domain;

import java.util.Date;

import com.vehicles.project.utils.Utilities;

public class VehicleOwner extends Driver {

	private boolean carInsurance;
	private boolean garage;

	public VehicleOwner(String firstname, String lastname, Date birthDate, DrivingLicense drivingLicense,
			boolean carInsurance, boolean garage) throws Exception {
		super(firstname, lastname, birthDate, drivingLicense);

		if (birthDate.after(Utilities.addSubstractYears(new Date(), -18)))
			throw new Exception("El titular ha de ser major d'edat (18 anys).");
		if (drivingLicense == null)
			throw new Exception("El titular ha de tenir llicència de conduir.");

		this.carInsurance = carInsurance;
		this.garage = garage;
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
