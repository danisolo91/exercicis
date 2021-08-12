package view;

import java.util.Scanner;

import application.UserController;
import domain.User;

public class AppView {

	private static final Scanner SCAN = new Scanner(System.in);
	private static UserController userController = new UserController();
	private static UserView userView = new UserView();
	private static boolean run = true;

	public static void main(String[] args) {
		showLogo();
		User loggedUser;

		while (run) {
			loggedUser = userController.getLoggedUser();
			if (loggedUser == null) {
				showMainMenu();
			} else {
				userView.showMainMenu();
			}
		}
	}

	private static void showLogo() {
		System.out.println("=======================================");
		System.out.println("|              VIDEO APP              |");
		System.out.println("=======================================");
	}

	private static void showMainMenu() {
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
			System.out.println("\nPrograma finalitzat.");
			run = false;
			break;
		default:
			System.out.println("L'opció introduïda no existeix.");
		}
	}
}
