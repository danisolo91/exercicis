package com.vehicles.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static final Scanner SCAN = new Scanner(System.in);

	public static void main(String[] args) {

		// CREAR COTXE -------------------------------------------------------/
		Car car = createCar();

		// CREAR RODES -------------------------------------------------------/
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

		// AFEGIR RODES AL COTXE ----------------------------------------------/
		try {
			car.addWheels(frontWheels, backWheels);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(car);

		SCAN.close();

	}

	private static Car createCar() {
		System.out.println("CREAR COTXE");

		String plate;
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
		String brand = SCAN.nextLine();
		System.out.println("Introdueix el color:");
		String color = SCAN.nextLine();

		return new Car(plate, brand, color);
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
