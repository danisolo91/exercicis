package view;

import java.util.Scanner;

public class AppView {

	private static final Scanner SCAN = new Scanner(System.in);

	public static void main(String[] args) {
		CoetView coetView = new CoetView();

		boolean executa = true;
		String opcio;

		while (executa) {
			coetView.estatActualDelsCoets();

			System.out.println("\nQuè vols fer?\na)Assolir una potència objectiu");
			System.out.println("b)Assolir una determinada velocitat\nc)Sortir");
			opcio = SCAN.nextLine();

			switch (opcio.toLowerCase()) {
			case "a":
				coetView.assolirPotenciaObjectiu();
				break;
			case "b":
				coetView.assolirDeterminadaVelocitat();
				break;
			case "c":
				System.out.println("--> Programa finalitzat <--");
				executa = false;
				break;
			default:
				System.out.println("--> L'opció introduïda no existeix <--");
			}
		}
	}

}
