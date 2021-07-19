package com.vehicles.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static final Scanner SCAN = new Scanner(System.in);

	public static void main(String[] args) {

		int vehicleType;

		// SELECCIONAR TIPUS VEHICLE
		do {
			System.out.println("Quin tipus de vehicle vols crear?");
			System.out.println("1) Cotxe");
			System.out.println("2) Camió");
			System.out.println("3) Moto");
			vehicleType = SCAN.nextInt();
			SCAN.nextLine(); // throw away the \n not consumed by nextInt()
		} while (vehicleType != 1 && vehicleType != 2 && vehicleType != 3);

		if (vehicleType == 1) {
			Vehicle car = createVehicle(1);
			System.out.println(car);
		} else if (vehicleType == 2) {
			Vehicle truck = createVehicle(2);
			System.out.println(truck);
		} else if (vehicleType == 3) {
			Vehicle bike = createVehicle(3);
			System.out.println(bike);
		}

		SCAN.close();

	}

	private static Vehicle createVehicle(int vehicleType) {
		Vehicle vehicle;
		String plate;
		String brand;
		String color;
		boolean passed = false;

		do {
			System.out.println("Introdueix la matrícula:");
			plate = SCAN.nextLine();

			if (plate.matches("\\d{4}\\w{2,3}")) {
				passed = true;
			} else {
				System.out.println("La matrícula ha de tenir 4 números i dues o tres lletres.");
			}
		} while (!passed);

		System.out.println("Introdueix la marca:");
		brand = SCAN.nextLine();
		System.out.println("Introdueix el color:");
		color = SCAN.nextLine();

		// CREAR RODES
		System.out.println("RODES TRASERES:");
		Wheel backWheel = createWheel();
		List<Wheel> backWheels = new ArrayList<Wheel>();

		System.out.println("RODES DAVANTERES:");
		Wheel frontWheel = createWheel();
		List<Wheel> frontWheels = new ArrayList<Wheel>();

		vehicle = new Car(plate, brand, color); // default vehicle type

		if (vehicleType != 3) {
			backWheels.add(backWheel);
			backWheels.add(backWheel);
			frontWheels.add(frontWheel);
			frontWheels.add(frontWheel);
			if (vehicleType == 2)
				vehicle = new Truck(plate, brand, color);
		} else {
			backWheels.add(backWheel);
			frontWheels.add(frontWheel);
			vehicle = new Bike(plate, brand, color);
		}

		// AFEGIR RODES
		try {
			vehicle.addWheels(frontWheels, backWheels);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vehicle;
	}

	private static Wheel createWheel() {
		System.out.println("Introdueix la marca:");
		String wheelBrand = SCAN.nextLine();

		double wheelDiameter;
		boolean passed = false;

		do {
			System.out.println("Introdueix el diàmetre:");
			wheelDiameter = SCAN.nextDouble();
			SCAN.nextLine(); // throw away the \n not consumed by nextDouble()

			if (wheelDiameter > 0.4 && wheelDiameter < 4) {
				passed = true;
			} else {
				System.out.println("El diàmetre ha de ser superior a 0.4 i inferior a 4.");
			}
		} while (!passed);

		return new Wheel(wheelBrand, wheelDiameter);
	}

}
