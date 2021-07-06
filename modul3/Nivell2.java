package modul3;

import java.util.Scanner;

public class Nivell2 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int[][] notes = new int[5][3];

		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 3; j++) {
				System.out.println("Introdueix la nota " + j + " per a l'alumne " + i + ": ");
				notes[i - 1][j - 1] = scanner.nextInt();
			}
		}

		scanner.close();

		double mitjana;
		double suma;

		for (int i = 0; i < notes.length; i++) {
			suma = 0;

			for (int n : notes[i]) {
				suma += n;
			}

			mitjana = suma / notes[i].length;

			if (mitjana >= 5) {
				System.out.println("L'alumne " + (i + 1) + " ha aprovat amb un " + mitjana);
			} else {
				System.out.println("L'alumne " + (i + 1) + " ha suspès amb un " + mitjana);
			}
		}

	}

}
