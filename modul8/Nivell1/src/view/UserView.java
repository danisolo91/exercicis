package view;

import application.UserController;
import domain.User;
import utilities.NotEmptyScanner;

public class UserView {

	private static final NotEmptyScanner SCAN = new NotEmptyScanner();
	private static UserController userController = new UserController();
	private static VideoView videoView = new VideoView();

	public void login() {
		System.out.println("\nIntrodueix nom d'usuari:");
		String username = SCAN.nextLine();
		System.out.println("Introdueix contrasenya:");
		String password = SCAN.nextLine();

		try {
			userController.login(username, password);
			showWelcomeMessage();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void showWelcomeMessage() {
		User loggedUser = userController.getLoggedUser();
		System.out.println("\n=======================================");
		System.out.println("Benvingut, " + loggedUser.getFirstname() + " " + loggedUser.getLastname());
		System.out.println("=======================================");
	}

	public void mainMenu() {
		System.out.println("\nQuè vols fer?");
		System.out.println("a) Pujar un nou video \nb) Llistar els meus videos\nc) Tancar sessió");
		String option = SCAN.nextLine();

		switch (option.toLowerCase()) {
		case "a":
			videoView.uploadVideo();
			break;
		case "b":
			videoView.listVideos();
			break;
		case "c":
			userController.logout();
			break;
		default:
			System.out.println("--> L'opció introduïda no existeix. <--");
		}
	}

	public void createUser() {
		System.out.println("\nCREAR UN USUARI NOU");
		System.out.println("Introdueix el teu nom:");
		String firstname = SCAN.nextLine();
		System.out.println("Introdueix el teu cognom:");
		String lastname = SCAN.nextLine();
		System.out.println("Introdueix un nom d'usuari únic:");
		String username = SCAN.nextLine();
		System.out.println("Introdueix una contrasenya:");
		String password = SCAN.nextLine();

		try {
			User user = new User(firstname, lastname, username, password);

			if (!userController.isUniqueUser(user)) {
				throw new Exception("--> El nom d'usuari introduït ja existeix. <--");
			} else {
				userController.addUser(user);
				System.out.println("--> Usuari creat amb èxit. <--");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
