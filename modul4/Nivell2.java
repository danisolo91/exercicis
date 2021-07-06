package modul4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Nivell2 {

	public static void main(String[] args) {

		// Variables
		int m1, m2, b5, b10, b20, b50, b100, b200, b500;
		int importTotal = 0;
		String[] plats = new String[10];
		int[] preus = new int[10];
		int seleccio;
		int seguirDemanant = 1;
		List<Integer> comanda = new ArrayList<Integer>();
		Scanner scanner = new Scanner(System.in);

		// Ompla menú y preus
		HashMap<String, Integer> preuPlat = new HashMap<String, Integer>();
		preuPlat.put("Bufalina", 12);
		preuPlat.put("4 Formaggi", 12);
		preuPlat.put("Pino Daniele", 18);
		preuPlat.put("Margherita", 9);
		preuPlat.put("Prosciutto", 10);
		preuPlat.put("Parmiggiana", 11);
		preuPlat.put("Al Tonno", 13);
		preuPlat.put("Carbonara", 11);
		preuPlat.put("Massimo Troise", 17);
		preuPlat.put("Ortolana", 12);

		int c = 0;

		for (Map.Entry<String, Integer> entry : preuPlat.entrySet()) {
			plats[c] = entry.getKey();
			preus[c] = entry.getValue();
			c++;
		}

		// Mostra menú i preus, i pregunta comanda
		System.out.println("          MENÚ           ");
		System.out.println("=========================");
		for (int i = 0; i < plats.length; i++) {
			System.out.println((i) + ") " + plats[i] + " - " + preus[i] + "€");
		}
		System.out.println("=========================");

		while (seguirDemanant == 1) {

			try {
				System.out.println("Què vols per menjar? (introdueix el número del plat)");

				seleccio = scanner.nextInt();

				if (seleccio >= 0 && seleccio < plats.length) {
					comanda.add(seleccio);
				} else {
					throw new Exception();
				}

				seguirDemanant = 0; // Parem bucle (continuará només si l'usuari pulsa 1)

				while (seguirDemanant != 1 && seguirDemanant != 2) {
					try {
						System.out.println("Vols seguir comprant?");
						System.out.println("1) Si");
						System.out.println("2) No");

						seguirDemanant = scanner.nextInt();

						if (seguirDemanant != 1 && seguirDemanant != 2) {
							throw new Exception();
						}
					} catch (InputMismatchException e) {
						System.out.println("Has d'introduïr un nombre enter.");
						scanner.next(); // consumim token en cas d'error
					} catch (Exception e) {
						System.out.println("Opció incorrecta.");
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Has d'introduïr un nombre enter.");
				scanner.next(); // consumim token en cas d'error
			} catch (Exception e) {
				System.out.println("El plat escollit no existeix.");
			}
		}

		scanner.close();

		// Mostra comanda i preu total
		System.out.println("\nLA TEVA COMANDA:");
		System.out.println("=========================");
		for (int i : comanda) {
			if (i >= 0 && i < plats.length) {
				System.out.println(i + ") " + plats[i] + " - " + preus[i] + "€");
				importTotal += preus[i];
			} else {
				System.out.println(i + ") No existeix");
			}
		}
		System.out.println("-------------------------");
		System.out.println("TOTAL A PAGAR: " + importTotal + "€");
		System.out.println("-------------------------");

		// Mostra billets amb els que s'hauria de pagar
		System.out.println("\nEs pot pagar de la següent manera:");
		b500 = importTotal / 500;
		if (b500 >= 1)
			System.out.println(b500 + " billets de 500.");
		importTotal = importTotal % 500;
		b200 = importTotal / 200;
		if (b200 >= 1)
			System.out.println(b200 + " billets de 200.");
		importTotal = importTotal % 200;
		b100 = importTotal / 100;
		if (b100 >= 1)
			System.out.println(b100 + " billets de 100.");
		importTotal = importTotal % 100;
		b50 = importTotal / 50;
		if (b50 >= 1)
			System.out.println(b50 + " billets de 50.");
		importTotal = importTotal % 50;
		b20 = importTotal / 20;
		if (b20 >= 1)
			System.out.println(b20 + " billets de 20.");
		importTotal = importTotal % 20;
		b10 = importTotal / 10;
		if (b10 >= 1)
			System.out.println(b10 + " billets de 10.");
		importTotal = importTotal % 10;
		b5 = importTotal / 5;
		if (b5 >= 1)
			System.out.println(b5 + " billets de 5.");
		importTotal = importTotal % 5;
		m2 = importTotal / 2;
		if (m2 >= 1)
			System.out.println(m2 + " monedes de 2.");
		importTotal = importTotal % 2;
		m1 = importTotal / 1;
		if (m1 >= 1)
			System.out.println(m1 + " monedes de 1.");
		importTotal = importTotal % 1;

	}

}
