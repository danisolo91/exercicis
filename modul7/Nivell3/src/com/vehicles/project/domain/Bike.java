package com.vehicles.project.domain;

import java.util.List;

public class Bike extends Vehicle {

	public Bike(String plate, String brand, String color) {
		super(plate, brand, color);
	}

	/**
	 * Re-implementem el mètode addWheels perquè les motos només tenen una roda
	 * davantera y una trasera
	 */
	@Override
	public void addWheels(List<Wheel> frontWheels, List<Wheel> backWheels) throws Exception {
		if (frontWheels.size() != 1)
			throw new Exception("Les motos han de tenir una roda davantera.");
		if (backWheels.size() != 1)
			throw new Exception("Les motos han de tenir una roda trasera.");

		this.wheels.add(frontWheels.get(0));
		this.wheels.add(backWheels.get(0));
	}

}
