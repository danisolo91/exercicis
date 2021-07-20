package com.vehicles.project.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {

	protected String plate;
	protected String brand;
	protected String color;
	protected List<Wheel> wheels = new ArrayList<Wheel>();
	protected VehicleOwner vehicleOwner;
	protected List<Driver> drivers = new ArrayList<Driver>();

	public Vehicle(String plate, String brand, String color) {
		this.plate = plate;
		this.brand = brand;
		this.color = color;
	}

	public void addWheels(List<Wheel> frontWheels, List<Wheel> backWheels) throws Exception {
		addTwoWheels(frontWheels);
		addTwoWheels(backWheels);
	}

	public void addTwoWheels(List<Wheel> wheels) throws Exception {
		if (wheels.size() != 2)
			throw new Exception("Ha d'haver-hi dues rodes.");

		Wheel rightWheel = wheels.get(0);
		Wheel leftWheel = wheels.get(1);

		if (!rightWheel.equals(leftWheel))
			throw new Exception("Les rodes no són iguals.");

		this.wheels.add(leftWheel);
		this.wheels.add(rightWheel);
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<Wheel> getWheels() {
		return wheels;
	}

	public VehicleOwner getVehicleOwner() {
		return vehicleOwner;
	}

	public void setVehicleOwner(VehicleOwner vehicleOwner) {
		this.vehicleOwner = vehicleOwner;
	}

	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append("plate: ");
		str.append(this.plate);
		str.append(", owner: ");
		str.append(this.vehicleOwner.getFullname());
		str.append(", drivers: ");

		if (this.drivers.size() > 0) {
			for (Driver d : this.drivers) {
				str.append(d.getFullname());
				str.append(", ");
			}
		} else {
			str.append("0");
		}

		return str.toString();
	}

}
