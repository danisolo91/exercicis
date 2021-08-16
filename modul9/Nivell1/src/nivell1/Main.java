package nivell1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	private static final Scanner SCAN = new Scanner(System.in);

	public static void main(String[] args) {

		int[][] propulsors1 = { { 0, 0, 10 }, { 0, 0, 30 }, { 0, 0, 80 }, };
		int[][] propulsors2 = { { 0, 0, 30 }, { 0, 0, 40 }, { 0, 0, 50 }, { 0, 0, 50 }, { 0, 0, 30 }, { 0, 0, 10 } };

		Coet coet1 = new Coet("32WESSDS", propulsors1);
		Coet coet2 = new Coet("LDSFJA32", propulsors2);

		String opcio;
		int potenciaObjectiu;
		boolean executa = true;

		while (executa) {
			System.out.println("\nESTAT ACTUAL DELS COETS:");
			System.out.println(coet1.getId() + ": " + coet1.getPropulsorsToString());
			System.out.println(coet2.getId() + ": " + coet2.getPropulsorsToString());
			System.out.println("\nQuè vols fer?\na)Introduïr potència objectiu\nb)Sortir");
			opcio = SCAN.nextLine();

			switch (opcio.toLowerCase()) {
			case "a":
				System.out.println("\nIntrodueix potència objectiu:");
				try {
					// Asignem la potència objectiu a cada coet
					potenciaObjectiu = SCAN.nextInt();
					SCAN.nextLine(); // consumim token \n
					coet1.setPotenciaObjectiu(potenciaObjectiu);
					coet2.setPotenciaObjectiu(potenciaObjectiu);

					// Llancem un thread per a cada propulsor
					for (int i = 0; i < coet1.getPropulsors().length; i++) {
						PropulsorThread t = new PropulsorThread(coet1, i);
						t.start();
					}

					for (int i = 0; i < coet2.getPropulsors().length; i++) {
						PropulsorThread t = new PropulsorThread(coet2, i);
						t.start();
					}

					// Esperem fins que tots els propulsors arribin a l'objectiu de propulsió
					while (!coet1.haTerminat() || !coet2.haTerminat()) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
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