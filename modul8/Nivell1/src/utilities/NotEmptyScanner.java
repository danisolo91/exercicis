package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe que conté alguns dels mètodes del Scanner de Java Util, però amb la
 * particularitat de que no deixa avançar si l'usuari no introdueix res.
 * 
 * @author daniel
 *
 */
public class NotEmptyScanner {

	private static final Scanner SCAN = new Scanner(System.in);

	/**
	 * Demana per consola una resposta S/N i retorna un valor boolean.
	 * 
	 * @return boolean
	 */
	public boolean nextBoolean() {
		String response;
		boolean passed = false;

		do {
			response = nextLine();
			if (!response.equalsIgnoreCase("S") && !response.equalsIgnoreCase("N")) {
				System.out.println("Has de posar S/N (si/no):");
			} else {
				passed = true;
			}
		} while (!passed);

		return response.equalsIgnoreCase("S") ? true : false;
	}

	/**
	 * Demana per consola la introducció d'una data en format dd/mm/aaaa i la
	 * retorna en format Date.
	 * 
	 * @return Date
	 */
	public Date nextDate() throws ParseException {
		String date;
		boolean passed = false;

		do {
			date = nextLine();

			if (!date.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d")) {
				System.out.println("Format incorrecte. Ha de ser dd/mm/aaaa. Torna a intentar-ho:");
			} else {
				passed = true;
			}
		} while (!passed);

		return new SimpleDateFormat("dd/MM/yyyy").parse(date);
	}

	public String nextLine() {
		String str = null;
		boolean passed = false;

		do {
			str = SCAN.nextLine();
			if (!str.isBlank())
				passed = true;
		} while (!passed);

		return str;
	}

	public int nextInt() {
		int result = 0;
		boolean passed = false;

		do {
			try {
				result = SCAN.nextInt();
				passed = true;
			} catch (InputMismatchException e) {
				System.out.println("No es un número enter.");
				SCAN.nextLine();
			}
		} while (!passed);

		return result;
	}

}
