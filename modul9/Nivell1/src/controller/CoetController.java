package controller;

import java.util.List;

import domain.Coet;
import domain.Propulsor;
import persistance.CoetRepository;

public class CoetController {

	private CoetRepository coetRepository;

	public CoetController() {
		coetRepository = new CoetRepository();
	}

	public List<Coet> getCoets() {
		return coetRepository.getAllCoets();
	}
	
	public void assolirPotenciaObjectiu(int potenciaObjectiu) {
		asignarPotenciaObjectiu(potenciaObjectiu);
		activarPropulsors();
	}

	public void asignarPotenciaObjectiu(int potenciaObjectiu) {
		for (Coet c : coetRepository.getAllCoets()) {
			for (Propulsor p : c.getPropulsors()) {
				p.setPotenciaObjectiu(potenciaObjectiu);
			}
		}
	}

	public void activarPropulsors() {
		for (Coet c : coetRepository.getAllCoets()) {
			for (int i = 0; i < c.getPropulsors().size(); i++) {
				PropulsorThread t = new PropulsorThread(c, i, this);
				t.start();
			}
		}
	}

	public void accelerar(Propulsor propulsor) {
		propulsor.setPotenciaActual(propulsor.getPotenciaActual() + 1);
	}

	public void frenar(Propulsor propulsor) {
		propulsor.setPotenciaActual(propulsor.getPotenciaActual() - 1);
	}

	public boolean propulsorsActius() {
		boolean result = false;

		done: for (Coet c : coetRepository.getAllCoets()) {
			for (Propulsor p : c.getPropulsors()) {
				if (p.getPotenciaActual() != p.getPotenciaObjectiu()) {
					result = true;
					break done;
				}
			}
		}

		return result;
	}
}
