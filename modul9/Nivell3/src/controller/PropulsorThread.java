package controller;

import domain.Coet;
import domain.Propulsor;
import view.graphic.Pantalla;

public class PropulsorThread extends Thread {

	private Coet coet;
	private int propulsorId;
	private CoetController coetController;
	private Pantalla pantalla;
	private int pas;

	public PropulsorThread(Coet coet, int propulsorId, Pantalla pantalla, int pas) {
		this.coet = coet;
		this.propulsorId = propulsorId;
		this.coetController = new CoetController();
		this.pantalla = pantalla;
		this.pas = pas;
	}

	public void run() {
		Propulsor propulsor = coet.getPropulsors().get(propulsorId);
		int potenciaActual = propulsor.getPotenciaActual();
		int potenciaObjectiu = propulsor.getPotenciaObjectiu();

		// ACCELERAR
		if (potenciaActual < potenciaObjectiu) {
			while (potenciaActual < potenciaObjectiu) {
				try {
					coetController.accelerar(propulsor, pas);
					pantalla.updatePropulsors(coet);
					Thread.sleep(500); // Per poder veure en consola com va accelerant...
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
					coetController.frenar(propulsor, pas);
					pantalla.updatePropulsors(coet);
					Thread.sleep(500); // Per poder veure en consola com va frenant...
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				potenciaActual = propulsor.getPotenciaActual();
				potenciaObjectiu = propulsor.getPotenciaObjectiu();
			}
		}
	}

}
