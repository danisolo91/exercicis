package view;

import controller.CoetController;
import domain.Coet;
import view.graphic.CoetView;

public class AppView {

	private static CoetController coetController = new CoetController();

	public static void main(String[] args) {

		// Obrim un panell de control gràfic a cada coet
		for (Coet coet : coetController.getCoets()) {
			new CoetView(coet);
		}

	}

}
