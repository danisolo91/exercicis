package com.vehicles.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static final Scanner SCAN = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			// CREAR TITULAR
			VehicleOwner owner = createVehicleOwner();

			// CREAR VEHICLE
			int vehicleType = selectVehicleType();

			if (!isValidLicense(vehicleType, owner.getDrivingLicense()))
				throw new Exception("La llicencia del titular no es válida per crear aquest vehicle.");

			Vehicle vehicle = createVehicle(vehicleType);

			// CREAR CONDUCTOR
			Driver driver = null;

			System.out.println("El titular del cotxe serà també el conductor del mateix? (S/N):");
			boolean ownerIsDriver = scanBoolean();

			if (ownerIsDriver) {
				driver = new Driver(owner.getFirstname(), owner.getLastname(), owner.getBirthDate(),
						owner.getDrivingLicense());
			} else {
				driver = createDriver();
				if (!isValidLicense(vehicleType, driver.getDrivingLicense()))
					throw new Exception("El conductor no té una llicència vàlida per conduir el vehicle creat.");
			}

			System.out.println(owner);
			System.out.println(vehicle);
			System.out.println(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}

		SCAN.close();
	}

	/**
	 * Mètode per crear un conductor nou.
	 * 
	 * @return Driver
	 * @throws Exception
	 */
	private static Driver createDriver() throws Exception {
		System.out.println("CREAR CONDUCTOR");

		System.out.println("Introdueix nom:");
		String firstname = SCAN.nextLine();

		System.out.println("Introdueix cognoms:");
		String lastname = SCAN.nextLine();

		System.out.println("Introdueix data de naixement (dd/mm/aaaa):");
		Date birthDate = scanDate();

		String fullname = firstname + " " + lastname;
		DrivingLicense drivingLicense = createDrivingLicense(fullname);

		return new Driver(firstname, lastname, birthDate, drivingLicense);
	}

	/**
	 * Crea un titular de vehicle.
	 * 
	 * @return VehicleOwner
	 */
	private static VehicleOwner createVehicleOwner() throws Exception {
		System.out.println("CREAR TITULAR");

		System.out.println("Introdueix nom:");
		String firstname = SCAN.nextLine();

		System.out.println("Introdueix cognoms:");
		String lastname = SCAN.nextLine();

		System.out.println("Introdueix data de naixement (dd/mm/aaaa):");
		Date birthDate = scanDate();

		String fullname = firstname + " " + lastname;
		DrivingLicense drivingLicense = createDrivingLicense(fullname);

		System.out.println("Té assegurança? (S/N):");
		boolean carInsurance = scanBoolean();

		System.out.println("Té garatge propi? (S/N):");
		boolean garage = scanBoolean();

		return new VehicleOwner(firstname, lastname, birthDate, drivingLicense, carInsurance, garage);
	}

	/**
	 * Demana per consola una resposta S/N i retorna un valor boolean.
	 * 
	 * @return boolean
	 */
	private static boolean scanBoolean() {
		String response;
		boolean passed = false;

		do {
			response = SCAN.nextLine();
			if (!response.equalsIgnoreCase("S") && !response.equalsIgnoreCase("N")) {
				System.out.println("Resposta incorrecta. Has de posar S/N (si/no):");
			} else {
				passed = true;
			}
		} while (!passed);

		return response.equalsIgnoreCase("S") ? true : false;
	}

	/**
	 * Crea per consola una llicència de conduir a partir d'un nom complet rebut per
	 * paràmetre.
	 * 
	 * @param fullname
	 * @return DrivingLicense
	 * @throws Exception
	 */
	private static DrivingLicense createDrivingLicense(String fullname) throws Exception {
		String type = null;
		boolean passed = false;

		System.out.println("Tipus de llicència de conduir (A, B o C):");

		do {
			type = SCAN.nextLine();
			if (!type.equalsIgnoreCase("A") && !type.equalsIgnoreCase("B") && !type.equalsIgnoreCase("C")) {
				System.out.println("El valor introduït no és vàlid. Torna a intentar-ho (A, B o C):");
			} else {
				passed = true;
			}
		} while (!passed);

		System.out.println("Data de caducitat (dd/mm/aaaa):");
		Date expiryDate = scanDate();

		return new DrivingLicense(type.toUpperCase(), fullname, expiryDate);
	}

	/**
	 * Demana per consola la introducció d'una data en format dd/mm/aaaa i la
	 * retorna en format Date. Atenció!! Si s'introdueix una data invàlida com
	 * 50/03/1991, el parser passarà els dies que sobren del mes introduït al mes
	 * següent, en aquest exemple la data resultant seria 19/04/1991.
	 * 
	 * @return Date
	 */
	private static Date scanDate() throws ParseException {
		String date;
		boolean passed = false;

		do {
			date = SCAN.nextLine();

			if (!date.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d")) {
				System.out.println("Format incorrecte. Ha de ser dd/mm/aaaa. Torna a intentar-ho:");
			} else {
				passed = true;
			}
		} while (!passed);

		return new SimpleDateFormat("dd/MM/yyyy").parse(date);
	}

	/**
	 * Mostra un menú per seleccionar un dels tres tipus de vehicles que es poden
	 * crear. No retorna res fins que es selecciona una opció correcta (1, 2 ó 3).
	 * 
	 * @return int
	 */
	private static int selectVehicleType() {
		int vehicleType;

		do {
			System.out.println("Quin tipus de vehicle vols crear?");
			System.out.println("1) Cotxe");
			System.out.println("2) Camió");
			System.out.println("3) Moto");
			vehicleType = SCAN.nextInt();
			SCAN.nextLine(); // throw away the \n not consumed by nextInt()
		} while (vehicleType != 1 && vehicleType != 2 && vehicleType != 3);

		return vehicleType;
	}

	/**
	 * Mètode que comprova si una llicència es vàlida per a un determinat tipus de
	 * vehicle.
	 * 
	 * @param vehicleType
	 * @param owner
	 * @return Vehicle
	 * @throws Exception
	 */
	private static boolean isValidLicense(int vehicleType, DrivingLicense drivingLicense) throws Exception {
		boolean result = false;

		switch (vehicleType) {
		case 1:
			if (drivingLicense.getType().equals("B")) {
				result = true;
			}
			break;
		case 2:
			if (drivingLicense.getType().equals("C")) {
				result = true;
			}
			break;
		case 3:
			if (drivingLicense.getType().equals("A")) {
				result = true;
			}
			break;
		default:
			System.out.println("Error a l'hora de comprovar la llicència.");
		}

		return result;
	}

	/**
	 * Crea un vehicle segons el tipus indicat per paràmetre.
	 * 
	 * @param vehicleType
	 * @return Vehicle
	 */
	private static Vehicle createVehicle(int vehicleType) {
		Vehicle vehicle = null;
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

		if (vehicleType != 3) {
			backWheels.add(backWheel);
			backWheels.add(backWheel);
			frontWheels.add(frontWheel);
			frontWheels.add(frontWheel);
			if (vehicleType == 1)
				vehicle = new Car(plate, brand, color);
			if (vehicleType == 2)
				vehicle = new Truck(plate, brand, color);
		} else {
			backWheels.add(backWheel);
			frontWheels.add(frontWheel);
			vehicle = new Bike(plate, brand, color);
		}

		// AFEGIR RODES AL VEHICLE
		try {
			vehicle.addWheels(frontWheels, backWheels);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vehicle;
	}

	/**
	 * Crea una roda per consola.
	 * 
	 * @return Wheel
	 */
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
