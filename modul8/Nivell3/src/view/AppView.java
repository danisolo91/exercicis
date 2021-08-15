package view;

import application.UserController;
import domain.User;
import utilities.NotEmptyScanner;

public class AppView {

	private static final NotEmptyScanner SCAN = new NotEmptyScanner();
	private static UserController userController = new UserController();
	private static UserView userView = new UserView();
	private static boolean run = true;

	public static void main(String[] args) {
		logo();

		User loggedUser;

		while (run) {
			loggedUser = userController.getLoggedUser();
			if (loggedUser == null) {
				mainMenu();
			} else {
				userView.mainMenu();
			}
		}
	}

	private static void logo() {
		System.out.println("=======================================");
		System.out.println("|              VIDEO APP              |");
		System.out.println("=======================================");
	}

	private static void mainMenu() {
		System.out.println("\nQuè vols fer?\na) Crear un nou usuari\nb) Iniciar sessió\nc) Sortir de l'aplicació");
		String option = SCAN.nextLine();

		switch (option.toLowerCase()) {
		case "a":
			userView.createUser();
			break;
		case "b":
			userView.login();
			break;
		case "c":
			System.out.println("\n--> Programa finalitzat. <--");
			run = false;
			break;
		default:
			System.out.println("--> L'opció introduïda no existeix. <--");
		}
	}
}
