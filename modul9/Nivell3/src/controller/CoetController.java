package controller;

import java.util.ArrayList;
import java.util.List;

import domain.Coet;
import domain.Propulsor;
import persistance.CoetRepository;
import view.graphic.Pantalla;

/**
 * Controlador del coet. Conté tots els mètodes necessaris per que l'usuari
 * pugui interactuar desde la vista amb un coet (veure el seu estat,
 * accelerarlo, frenarlo, etc.)
 * 
 * @author daniel
 *
 */
public class CoetController {

	private CoetRepository coetRepository;
	private List<PropulsorThread> propulsorThreads;

	public CoetController() {
		coetRepository = CoetRepository.getInstancia();
		propulsorThreads = new ArrayList<PropulsorThread>();
	}

	/**
	 * Mètode per treure una llista amb tots els coets
	 * 
	 * @return
	 */
	public List<Coet> getCoets() {
		return coetRepository.getAllCoets();
	}

	/**
	 * Calcula la potència total d'un coet
	 * 
	 * @param coet
	 * @return
	 */
	public int getPotenciaCoet(Coet coet) {
		int potencia = 0;
		for (Propulsor p : coet.getPropulsors()) {
			potencia += p.getPotenciaMaxima();
		}
		return potencia;
	}

	/**
	 * Llança un thread per cada propulsor que el va accelerant o frenant fins
	 * assolir la potència objectiu
	 */
	public void activarPropulsors(Coet c, Pantalla p, int pas) {
		for (int i = 0; i < c.getPropulsors().size(); i++) {
			PropulsorThread t = new PropulsorThread(c, i, p, pas);
			t.start();
			propulsorThreads.add(t);
		}
	}

	/**
	 * Mètode per interrumpir els PropulsorThread en cas de que hi hagin
	 */
	public void interrumpirPropulsorThreads() {
		if (this.propulsorThreads.size() > 0) {
			for (PropulsorThread pt : this.propulsorThreads) {
				while (!pt.isInterrupted())
					pt.interrupt();
			}
			this.propulsorThreads.clear();
		}
	}

	/**
	 * Mètode que calcula la potència necessaria per assolir una determinada
	 * velocitat i la reparteix entre els propulsors. Llança una excepció en cas que
	 * els propulsors no siguin capaços de produïr la potència necessaria
	 */
	public void assolirVelocitat(int velocitatInicial, int velocitatObjectiu, Coet coet, Pantalla pantalla, int pas)
			throws Exception {
		interrumpirPropulsorThreads();
		int potenciaTotalNecessaria = (int) Math.pow(((velocitatObjectiu - velocitatInicial) / 100), 2);
		repartirPotencia(potenciaTotalNecessaria, coet);
		activarPropulsors(coet, pantalla, pas);

		if (getPotenciaCoet(coet) < potenciaTotalNecessaria) {
			throw new Exception("--> El coet no té potència suficient. Es necessita una potència de "
					+ potenciaTotalNecessaria + " <--");
		}
	}

	/**
	 * Mètode que s'encarrega de repartir una potència objectiu a cada propulsor de
	 * manera equitativa. Quan un propulsor arriba a la seva potència màxima, la
	 * potència necessària restant es reparteix entre els altres propulsors de major
	 * potència
	 * 
	 * @param potencia
	 */
	public void repartirPotencia(int potencia, Coet c) {
		int potenciaRepartida = 0;
		int potenciaCoet = getPotenciaCoet(c);

		// posem la potencia objectiu a 0
		for (Propulsor p : c.getPropulsors()) {
			p.setPotenciaObjectiu(0);
		}

		// repartim la potència necessària
		while (potenciaRepartida < potencia) {
			if (potenciaCoet > potenciaRepartida) {
				for (Propulsor p : c.getPropulsors()) {
					if (p.getPotenciaObjectiu() < p.getPotenciaMaxima()) {
						p.setPotenciaObjectiu(p.getPotenciaObjectiu() + 1);
						potenciaRepartida += 1;
					}
				}
			} else {
				potenciaRepartida += 1;
			}
		}
	}

	/**
	 * Augmenta la potència actual d'un propulsor
	 * 
	 * @param propulsor
	 */
	public synchronized void accelerar(Propulsor propulsor, int pas) {
		if (propulsor.getPotenciaObjectiu() - propulsor.getPotenciaActual() >= pas) {
			propulsor.setPotenciaActual(propulsor.getPotenciaActual() + pas);
		} else {
			propulsor.setPotenciaActual(propulsor.getPotenciaActual() + 1);
		}
	}

	/**
	 * Disminueix la potència actual d'un propulsor
	 * 
	 * @param propulsor
	 */
	public synchronized void frenar(Propulsor propulsor, int pas) {
		if (propulsor.getPotenciaObjectiu() - propulsor.getPotenciaActual() >= pas) {
			propulsor.setPotenciaActual(propulsor.getPotenciaActual() - pas);
		} else {
			propulsor.setPotenciaActual(propulsor.getPotenciaActual() - 1);
		}
	}
}
