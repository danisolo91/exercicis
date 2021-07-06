package modul4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Nivell1Fase2 {

	public static void main(String[] args) {

		// Variables
		int m1, m2, b5, b10, b20, b50, b100, b200, b500;
		int importTotal = 0;
		String[] plats = new String[10];
		int[] preus = new int[10];
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
			System.out.println("Què vols per menjar? (introdueix el número del plat)");
			comanda.add(scanner.nextInt());
			System.out.println("Vols seguir comprant?");
			System.out.println("1) Si");
			System.out.println("2) No");
			seguirDemanant = scanner.nextInt();
		}

		scanner.close();

	}

}
