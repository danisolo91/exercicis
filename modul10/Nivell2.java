package exercicis;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class Nivell2 {

	public static void main(String[] args) {
		String[] matriu = { "Exercici", "paraula", "32", "amet", "lorem", "16", "impsum", "9" };

		// Punt 1
		Arrays.sort(matriu, (s1, s2) -> s1.length() - s2.length());
		// System.out.println(matriu);

		// Punt 2
		Arrays.sort(matriu, Collections.reverseOrder((s1, s2) -> s1.length() - s2.length()));
		// System.out.println(matriu);

		// Punt 3
		Arrays.sort(matriu);
		// System.out.println(matriu);

		// Punt 4
		String[] primers = Arrays.stream(matriu).filter(c -> c.contains("e")).toArray(String[]::new);
		String[] laResta = Arrays.stream(matriu).filter(c -> !c.contains("e")).toArray(String[]::new);
		matriu = Stream.concat(Arrays.stream(primers), Arrays.stream(laResta)).toArray(String[]::new);
		// System.out.println(matriu);

		// Punt 5
		matriu = Arrays.stream(matriu).map(c -> c.replace("a", "4")).toArray(String[]::new);
		// System.out.println(matriu);

		// Punt 6
		matriu = Arrays.stream(matriu).filter(c -> c.matches("\\d+")).toArray(String[]::new);
		// System.out.println(matriu);

		// Exercici Functional Interface
		Nivell2Interface suma = (a, b) -> a + b;
		Nivell2Interface resta = (a, b) -> a - b;
		Nivell2Interface multiplicacio = (a, b) -> a * b;
		Nivell2Interface divisio = (a, b) -> a / b;

		/* System.out.println(suma.operacio(8, 2));
		 System.out.println(resta.operacio(8, 2));
		 System.out.println(multiplicacio.operacio(8, 2));
		 System.out.println(divisio.operacio(8, 2)); */
		
	}

}
