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
			System.out.println("2) Moto");
			vehicleType = SCAN.nextInt();
			SCAN.nextLine(); // throw away the \n not consumed by nextInt()
		} while (vehicleType != 1 && vehicleType != 2);

		if (vehicleType == 1) {
			Car car = createCar();
			System.out.println(car);
		} else {
			Bike bike = createBike();
			System.out.println(bike);
		}

		SCAN.close();

	}

	private static Car createCar() {
		Car car;
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

		car = new Car(plate, brand, color);

		// CREAR RODES
		System.out.println("RODES TRASERES:");
		Wheel backWheel = createWheel();
		List<Wheel> backWheels = new ArrayList<Wheel>();
		backWheels.add(backWheel);
		backWheels.add(backWheel);

		System.out.println("RODES DAVANTERES:");
		Wheel frontWheel = createWheel();
		List<Wheel> frontWheels = new ArrayList<Wheel>();
		frontWheels.add(frontWheel);
		frontWheels.add(frontWheel);

		// AFEGIR RODES AL COTXE
		try {
			car.addWheels(frontWheels, backWheels);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return car;
	}

	private static Bike createBike() {
		Bike bike;
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

		bike = new Bike(plate, brand, color);

		// CREAR RODES
		System.out.println("RODA TRASERA:");
		Wheel backWheel = createWheel();

		System.out.println("RODA DAVANTERA:");
		Wheel frontWheel = createWheel();

		// AFEGIR RODES A LA MOTO
		bike.addWheels(frontWheel, backWheel);

		return bike;
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
