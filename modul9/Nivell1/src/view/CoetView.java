package view;

import java.util.List;

import controller.CoetController;
import domain.Coet;

public class CoetView {

	private CoetController coetController;

	public CoetView(CoetController coetController) {
		this.coetController = coetController;
	}

	public void estatActualDelsCoets() {
		List<Coet> coets = coetController.getCoets();

		System.out.println("\nESTAT ACTUAL DELS COETS:");
		for (Coet c : coets) {
			System.out.println(c.getId() + ": " + c.getPropulsors().toString());
		}
	}
}
