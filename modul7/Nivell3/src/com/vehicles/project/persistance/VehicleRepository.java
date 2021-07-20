package com.vehicles.project.persistance;

import java.util.ArrayList;
import java.util.List;

import com.vehicles.project.domain.Vehicle;

public class VehicleRepository {

	private List<Vehicle> vehicles = new ArrayList<Vehicle>();

	public List<Vehicle> getAllVehicles() {
		return this.vehicles;
	}

	public Vehicle getVehicleById(int id) {
		return this.vehicles.get(id);
	}

	public void addVehicle(Vehicle vehicle) {
		this.vehicles.add(vehicle);
	}

}
