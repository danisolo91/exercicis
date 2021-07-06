package modul3;

import java.util.Scanner;

public class Nivell1Fase1 {

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

		System.out.println(nom1 + ", " + nom2 + ", " + nom3 + ", " + nom4 + ", " + nom5 + ", " + nom6);

	}

}
