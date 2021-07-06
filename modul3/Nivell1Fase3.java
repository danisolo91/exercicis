package modul3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Nivell1Fase3 {

	public static void main(String[] args) {
		
		String nom1 = "", nom2 = "", nom3 = "", nom4 = "", nom5 = "", nom6 = "";
		Scanner scanner = new Scanner(System.in);

		for (int i = 1; i <= 6; i++) {
			System.out.println("Introdueix el nom de la ciutat " + i + ":");
			switch (i) {
			case 1:
				nom1 = scanner.nextLine();
				break;
			case 2:
				nom2 = scanner.nextLine();
				break;
			case 3:
				nom3 = scanner.nextLine();
				break;
			case 4:
				nom4 = scanner.nextLine();
				break;
			case 5:
				nom5 = scanner.nextLine();
				break;
			case 6:
				nom6 = scanner.nextLine();
				break;
			default:
				System.out.println("Alguna cosa no ha anat bé :(");
			}
		}

		scanner.close();
		
		ArrayList<String> arrayCiutats = new ArrayList<String>();
		arrayCiutats.add(nom1);
		arrayCiutats.add(nom2);
		arrayCiutats.add(nom3);
		arrayCiutats.add(nom4);
		arrayCiutats.add(nom5);
		arrayCiutats.add(nom6);
		
		Collections.sort(arrayCiutats);
		
		ArrayList<String> arrayCiutatsModificades = new ArrayList<String>();
		
		for(String c: arrayCiutats) {
			arrayCiutatsModificades.add(c.replace('a', '4'));
		}
		
		System.out.println("Ciutats modificades per ordre alfabètic:");
		for(String c: arrayCiutatsModificades) {
			System.out.println(c);
		}
		
	}

}
