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
		System.out.println("Introdueix la matrícula:");
		String plate = SCAN.nextLine();
		System.out.println("Introdueix la marca:");
		String brand = SCAN.nextLine();
		System.out.println("Introdueix el color:");
		String color = SCAN.nextLine();

		return new Car(plate, brand, color);
	}

	private static Wheel createWheel() {
		System.out.println("Introdueix la marca:");
		String wheelBrand = SCAN.nextLine();
		System.out.println("Introdueix el diàmetre:");
		double wheelDiameter = SCAN.nextDouble();
		SCAN.nextLine(); // throw away the \n not consumed by nextDouble()

		return new Wheel(wheelBrand, wheelDiameter);
	}

}
