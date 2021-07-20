package com.vehicles.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.vehicles.project.domain.Bike;
import com.vehicles.project.domain.Car;
import com.vehicles.project.domain.Driver;
import com.vehicles.project.domain.DrivingLicense;
import com.vehicles.project.domain.Person;
import com.vehicles.project.domain.Truck;
import com.vehicles.project.domain.Vehicle;
import com.vehicles.project.domain.VehicleOwner;
import com.vehicles.project.domain.Wheel;
import com.vehicles.project.persistance.PersonRepository;
import com.vehicles.project.persistance.VehicleRepository;

public class Main {

	private static final Scanner SCAN = new Scanner(System.in);
	private static PersonRepository personRepository = new PersonRepository();
	private static VehicleRepository vehicleRepository = new VehicleRepository();

	public static void main(String[] args) {
		boolean run = true;

		while (run) {
			showDatabase();
			try {
				System.out.println("\nQuè vols fer?");
				System.out.println("a) Crear persona\nb) Crear vehicle\nc) Sortir");
				String option = SCAN.nextLine();

				switch (option.toLowerCase()) {
				case "a":
					System.out.println("\nQuin tipus de persona vols crear?");
					System.out.println("a) Titular");
					System.out.println("b) Conductor");
					String option2 = SCAN.nextLine();

					switch (option2.toLowerCase()) {
					case "a":
						VehicleOwner owner = createVehicleOwner();
						personRepository.addPerson(owner);
						System.out.println("Titular creat amb èxit.");
						break;
					case "b":
						Driver driver = createDriver();
						personRepository.addPerson(driver);
						System.out.println("Conductor creat amb èxit.");
						break;
					default:
						System.out.println("El valor introduït no existeix.");
					}
					break;
				case "b":
					int vehicleType = selectVehicleType();
					Vehicle vehicle = createVehicle(vehicleType);
					vehicleRepository.addVehicle(vehicle);
					System.out.println("Vehicle creat amb èxit.");
					break;
				case "c":
					System.out.println("Programa finalitzat.");
					run = false;
					break;
				default:
					System.out.println("El valor introduït no existeix.");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		SCAN.close();
	}

	/**
	 * Mostra el totes les persones i tots els vehicles guardats als repositoris
	 */
	private static void showDatabase() {
		List<Person> persons = personRepository.getAllPersons();
		List<Vehicle> vehicles = vehicleRepository.getAllVehicles();

		System.out.println("\nBASE DE DADES:");
		System.out.println("Persones:");

		if (persons.size() > 0) {
			for (Person p : persons) {
				System.out.println(p.getClass().getSimpleName() + " [" + p.toString() + "]");
			}
		} else {
			System.out.println("0");
		}

		System.out.println("\nVehicles:");

		if (vehicles.size() > 0) {
			for (Vehicle v : vehicles) {
				System.out.println(v.getClass().getSimpleName() + " [" + v.toString() + "]");
			}
		} else {
			System.out.println("0");
		}
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
			System.out.println("\nQuin tipus de vehicle vols crear?");
			System.out.println("1) Cotxe");
			System.out.println("2) Camió");
			System.out.println("3) Moto");
			vehicleType = SCAN.nextInt();
			SCAN.nextLine(); // throw away the \n not consumed by nextInt()
		} while (vehicleType != 1 && vehicleType != 2 && vehicleType != 3);

		return vehicleType;
	}

	/**
	 * Mètode que permet seleccionar una persona pel seu ID (posició que té en la
	 * llista de persones del repositori). Si no hi ha cap persona al repositori
	 * retorna un Person null.
	 * 
	 * @return Person
	 */
	private static Person selectPerson() {
		Person person = null;
		List<Person> persons = personRepository.getAllPersons();

		if (persons.size() > 0) {

			// Mostrem les persones del repositori
			System.out.println("\nPERSONES:");
			for (int i = 0; i < persons.size(); i++) {
				System.out.println("ID: " + i + " [" + persons.get(i).toString() + "]");
			}

			int selectedId = 0;
			boolean passed = false;

			// No avança fins que es selecciona un ID vàlid
			while (!passed) {
				System.out.println("\nIntrodueix l'ID de la persona que vols seleccionar:");
				selectedId = SCAN.nextInt();
				SCAN.nextLine(); // throw away the \n not consumed by nextInt()

				try {
					person = persons.get(selectedId);
					passed = true;
				} catch (IndexOutOfBoundsException e) {
					System.out.println("El ID introduït no existeix.");
				}
			}

			person = persons.get(selectedId);
		}

		return person;
	}

	/**
	 * Mètode que permet seleccionar un VehicleOwner pel seu ID (posició que té en
	 * la llista de persones del repositori). Si no hi ha cap titular al repositori
	 * retorna un VehicleOwner null.
	 * 
	 * @return VehicleOwner
	 */
	private static VehicleOwner selectOwner() {
		VehicleOwner owner = null;
		List<Person> persons = personRepository.getAllPersons();

		if (persons.size() > 0) {
			List<Integer> ids = new ArrayList<Integer>();
			Person person;

			System.out.println("\nTITULARS:");

			for (int i = 0; i < persons.size(); i++) {
				person = persons.get(i);

				// Mostrem únicament titulars, no conductors
				if (person.getClass().getSimpleName().equals("VehicleOwner")) {
					System.out.println("ID: " + i + " [" + person.toString() + "]");
					ids.add(i); // guardem la posició que té dins de l'array persons
				}
			}

			int selectedId = 0;
			boolean passed = false;

			while (!passed) {
				System.out.println("\nIntrodueix l'ID de la persona que vols seleccionar com a titular:");
				selectedId = SCAN.nextInt();
				SCAN.nextLine(); // throw away the \n not consumed by nextInt()

				// Comprova que efectivament el ID introduït correspon a un VehicleOwner
				if (!ids.contains(selectedId)) {
					System.out.println("El ID introduït no correspon a cap titular.");
				} else {
					passed = true;
				}
			}

			owner = (VehicleOwner) persons.get(selectedId);
		}

		return owner;
	}

	/**
	 * Metode que retorna un titular, ja sigui creant un de nou o seleccionant-lo de
	 * la base de dades.
	 * 
	 * @return VehicleOwner
	 */
	private static VehicleOwner getOwner() {
		VehicleOwner owner = null;

		// No avança fins que es crea/selecciona un titular
		while (owner == null) {
			System.out.println("\nASIGNAR TITULAR:");
			System.out.println("a) Crear titular");
			System.out.println("b) Seleccionar titular");
			String option3 = SCAN.nextLine();

			switch (option3.toLowerCase()) {
			case "a":
				try {
					owner = createVehicleOwner();
					personRepository.addPerson(owner);
					System.out.println("Titular creat amb èxit.");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "b":
				owner = selectOwner();
				break;
			}
		}

		return owner;
	}

	/**
	 * Mètode per gestionar la creació d'un conductor, ja sigui creant un de nou o
	 * seleccionant-lo del repositori.
	 * 
	 * @return Driver
	 */
	private static Driver getDriver() {
		Driver driver = null;

		// No avança fins que es crea/selecciona un conductor
		while (driver == null) {
			System.out.println("\nASIGNAR CONDUCTOR:");
			System.out.println("a) Crear conductor");
			System.out.println("b) Seleccionar conductor");
			String option = SCAN.nextLine();

			switch (option.toLowerCase()) {
			case "a":
				try {
					driver = createDriver();
					personRepository.addPerson(driver);
					System.out.println("Conductor creat amb èxit.");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "b":
				driver = (Driver) selectPerson();
				break;
			}
		}

		return driver;
	}

	/**
	 * Mètode per crear un conductor nou.
	 * 
	 * @return Driver
	 * @throws Exception
	 */
	private static Driver createDriver() throws Exception {
		System.out.println("\nCREAR CONDUCTOR");

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
		VehicleOwner owner;
		System.out.println("\nCREAR TITULAR");

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

		owner = new VehicleOwner(firstname, lastname, birthDate, drivingLicense, carInsurance, garage);

		return owner;
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
	 * Mètode que comprova si una llicència es vàlida per a un determinat tipus de
	 * vehicle. Si no es vàlid, llança una excepció.
	 * 
	 * @param vehicleType
	 * @param drivingLicense
	 * @throws Exception
	 */
	private static void validateLicense(int vehicleType, DrivingLicense drivingLicense) throws Exception {
		boolean isValid = false;

		switch (vehicleType) {
		case 1:
			if (drivingLicense.getType().equals("B")) {
				isValid = true;
			}
			break;
		case 2:
			if (drivingLicense.getType().equals("C")) {
				isValid = true;
			}
			break;
		case 3:
			if (drivingLicense.getType().equals("A")) {
				isValid = true;
			}
			break;
		default:
			System.out.println("Error a l'hora de comprovar la llicència.");
		}

		if (!isValid) {
			throw new Exception("La llicencia no es vàlida.");
		}
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
		System.out.println("\nRODES TRASERES:");
		Wheel backWheel = createWheel();
		List<Wheel> backWheels = new ArrayList<Wheel>();

		System.out.println("\nRODES DAVANTERES:");
		Wheel frontWheel = createWheel();
		List<Wheel> frontWheels = new ArrayList<Wheel>();

		if (vehicleType != 3) {
			// Si no es una moto, s'afegeixen dues rodes traseres i dues davanteres
			backWheels.add(backWheel);
			backWheels.add(backWheel);
			frontWheels.add(frontWheel);
			frontWheels.add(frontWheel);

			if (vehicleType == 1)
				vehicle = new Car(plate, brand, color);
			if (vehicleType == 2)
				vehicle = new Truck(plate, brand, color);
		} else {
			// A la moto afegim només una roda al davant i una al darrere
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

		// ASIGNAR TITULAR
		VehicleOwner owner = null;
		boolean isValidLicense = false;

		// No avança fins que s'obté un titular amb llicència vàlida
		while (!isValidLicense) {
			owner = getOwner();

			try {
				validateLicense(vehicleType, owner.getDrivingLicense());
				isValidLicense = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		vehicle.setVehicleOwner(owner);

		// ASIGNAR CONDUCTORS (OPCIONAL)
		List<Driver> drivers = new ArrayList<Driver>();
		boolean addDriver = true;

		/*
		 * Cada vegada que s'afegeix un conductor es torna a preguntar si vol afegir un
		 * altre fins que indiqui que no, per tal de que pugui afegir més d'un.
		 */
		while (addDriver) {
			System.out.println("\nVols afegir un conductor? (S/N):");
			addDriver = scanBoolean();

			if (addDriver) {
				Driver driver = null;
				isValidLicense = false;

				// No avança fins que s'obté un conductor amb llicència vàlida
				while (!isValidLicense) {
					driver = getDriver();
					try {
						validateLicense(vehicleType, driver.getDrivingLicense());
						isValidLicense = true;
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}

				drivers.add(driver);
				System.out.println("Conductor asignat correctament.");
			}
		}

		vehicle.setDrivers(drivers);

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

}
