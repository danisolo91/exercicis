package view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import controller.CoetController;
import domain.Coet;

/**
 * Classe encarregada de mostrar a l'usuari vistes relacionades amb l'objecte
 * Coet
 * 
 * @author daniel
 *
 */
public class CoetView {

	private static final Scanner SCAN = new Scanner(System.in);
	private CoetController coetController;

	public CoetView() {
		this.coetController = new CoetController();
	}

	/**
	 * Mostra per pantalla l'estat actual dels coets (l'ID i els valors dels
	 * propulsors)
	 */
	public void estatActualDelsCoets() {
		List<Coet> coets = coetController.getCoets();

		System.out.println("\nESTAT ACTUAL DELS COETS:");
		for (Coet c : coets) {
			System.out.println(c.getId() + ": " + c.getPropulsors().toString());
		}
	}

	/**
	 * Demana a l'usuari que introdueixi una potència objectiu i pasa el valor al
	 * mètode corresponent del controlador per que els propulsors assoleixin aquella
	 * potència
	 */
	public void assolirPotenciaObjectiu() {
		try {
			System.out.println("\nIntrodueix potència objectiu:");
			int potenciaObjectiu = SCAN.nextInt();
			SCAN.nextLine(); // consumim token \n

			if (potenciaObjectiu >= 0) {
				coetController.assolirPotenciaObjectiu(potenciaObjectiu);
			} else {
				System.out.println("--> La potència no pot ser negativa <--");
			}
		} catch (InputMismatchException e) {
			System.out.println("--> El valor introduït no es un número enter <--");
			SCAN.nextLine();
		}
	}

	/**
	 * Demana a l'usuari que introduexi la velocitat actual del coet i la velocitat
	 * a la qual vol volar i executa el mètode corresponent del controlador que
	 * s'encarrega d'accelerar els propulsors per assolir aquella velocitat
	 */
	public void assolirDeterminadaVelocitat() {
		try {
			System.out.println("\nIntrodueix la velocitat actual del coet:");
			int velocitatInicial = SCAN.nextInt();
			System.out.println("Introdueix la velocitat a la qual vols volar:");
			int velocitatObjectiu = SCAN.nextInt();

			coetController.assolirVelocitat(velocitatInicial, velocitatObjectiu);

		} catch (InputMismatchException e) {
			System.out.println("--> El valor introduït no es un número enter <--");
			SCAN.nextLine();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
