package Utilities;

import java.util.Scanner;

public abstract class Utils {

	private static final Scanner SCAN = new Scanner(System.in);

	/**
	 * Demana per consola una resposta S/N i retorna un valor boolean.
	 * 
	 * @return boolean
	 */
	public static boolean scanBoolean() {
		String response;
		boolean passed = false;

		do {
			response = SCAN.nextLine();
			if (!response.equalsIgnoreCase("S") && !response.equalsIgnoreCase("N")) {
				System.out.println("Resposta incorrecta. Has de posar S/N (si/no):");
			} else {
				passed = true;
			}
		} while (!passed);

		return response.equalsIgnoreCase("S") ? true : false;
	}

	/**
	 * Donat un text, substitueix els espais en blanc per guions i elimina tots els
	 * caràcters no alfanumèrics excepte els guions i les barres baixes, per tal de
	 * que quedi un text vàlid per a utilitzar-lo com a URL.
	 * 
	 * @param text
	 * @return String
	 */
	public static String makeUrl(String text) {
		String url = text.toLowerCase();

		url = url.replace(' ', '-');
		url = url.replaceAll("[^\\s\\w\\-\\_]", "");

		return url;
	}
}
