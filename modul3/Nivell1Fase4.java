package modul3;

import java.util.Scanner;

public class Nivell1Fase4 {

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

		char[] arr1 = new char[nom1.length()];
		char[] arr2 = new char[nom2.length()];
		char[] arr3 = new char[nom3.length()];
		char[] arr4 = new char[nom4.length()];
		char[] arr5 = new char[nom5.length()];
		char[] arr6 = new char[nom6.length()];

		System.out.println("Ciutats amb els noms invertits:");

		for (int i = 1; i <= 6; i++) {
			if (i == 1) {
				for (int j = 0; j < nom1.length(); j++) {
					arr1[nom1.length() - 1 - j] = nom1.charAt(j);
				}
				System.out.println(nom1 + " - " + String.valueOf(arr1));
			} else if (i == 2) {
				for (int j = 0; j < nom2.length(); j++) {
					arr2[nom2.length() - 1 - j] = nom2.charAt(j);
				}
				System.out.println(nom2 + " - " + String.valueOf(arr2));
			} else if (i == 3) {
				for (int j = 0; j < nom3.length(); j++) {
					arr3[nom3.length() - 1 - j] = nom3.charAt(j);
				}
				System.out.println(nom3 + " - " + String.valueOf(arr3));
			} else if (i == 4) {
				for (int j = 0; j < nom4.length(); j++) {
					arr4[nom4.length() - 1 - j] = nom4.charAt(j);
				}
				System.out.println(nom4 + " - " + String.valueOf(arr4));
			} else if (i == 5) {
				for (int j = 0; j < nom5.length(); j++) {
					arr5[nom5.length() - 1 - j] = nom5.charAt(j);
				}
				System.out.println(nom5 + " - " + String.valueOf(arr5));
			} else if (i == 6) {
				for (int j = 0; j < nom6.length(); j++) {
					arr6[nom6.length() - 1 - j] = nom6.charAt(j);
				}
				System.out.println(nom6 + " - " + String.valueOf(arr6));
			}
		}

	}

}
