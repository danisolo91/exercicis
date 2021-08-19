package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.CoetController;

public class AppView {

	private static final Scanner SCAN = new Scanner(System.in);

	public static void main(String[] args) {
		CoetController coetController = new CoetController();
		CoetView coetView = new CoetView(coetController);

		boolean executa = true;
		String opcio;
		int potenciaObjectiu;

		while (executa) {
			coetView.estatActualDelsCoets();

			System.out.println("\nQuè vols fer?\na)Volar a potència objectiu\nb)Sortir");
			opcio = SCAN.nextLine();

			switch (opcio.toLowerCase()) {
			case "a":
				try {
					System.out.println("\nIntrodueix potència objectiu:");
					potenciaObjectiu = SCAN.nextInt();
					SCAN.nextLine(); // consumim token \n

					if (potenciaObjectiu >= 0) {
						coetController.assolirPotenciaObjectiu(potenciaObjectiu);

						// Esperem fins que tots els propulsors arribin a la seva potència objectiu
						while (coetController.propulsorsActius()) {
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					} else {
						System.out.println("--> La potència no pot ser negativa <--");
					}
				} catch (InputMismatchException e) {
					System.out.println("--> El valor introduït no es un número enter <--");
					SCAN.nextLine();
				}
				break;
			case "b":
				System.out.println("--> Programa finalitzat <--");
				executa = false;
				break;
			default:
				System.out.println("--> L'opció introduïda no existeix <--");
			}
		}
	}

}
