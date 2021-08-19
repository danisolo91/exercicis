package controller;

import domain.Coet;
import domain.Propulsor;

public class PropulsorThread extends Thread {

	private Coet coet;
	private int propulsorId;
	private CoetController coetController;

	public PropulsorThread(Coet coet, int propulsorId, CoetController coetController) {
		this.coet = coet;
		this.propulsorId = propulsorId;
		this.coetController = coetController;
	}

	public void run() {
		Propulsor propulsor = coet.getPropulsors().get(propulsorId);
		int potenciaActual = propulsor.getPotenciaActual();
		int potenciaObjectiu = propulsor.getPotenciaObjectiu();

		// ACCELERAR
		if (potenciaActual < potenciaObjectiu) {
			while (potenciaActual < potenciaObjectiu) {
				try {
					coetController.accelerar(propulsor);
					System.out.println(coet.getId() + "-" + propulsorId + ":" + propulsor.toString());
					Thread.sleep(100); // Per poder veure en consola com va accelerant...
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				potenciaActual = propulsor.getPotenciaActual();
				potenciaObjectiu = propulsor.getPotenciaObjectiu();
			}
		}

		// FRENAR
		else {
			while (potenciaActual > potenciaObjectiu) {
				try {
					coetController.frenar(propulsor);
					System.out.println(coet.getId() + "-" + propulsorId + ":" + propulsor.toString());
					Thread.sleep(100); // Per poder veure en consola com va accelerant...
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				potenciaActual = propulsor.getPotenciaActual();
				potenciaObjectiu = propulsor.getPotenciaObjectiu();
			}
		}
	}
}
