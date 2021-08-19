package controller;

import java.util.List;

import domain.Coet;
import domain.Propulsor;
import persistance.CoetRepository;

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

	public CoetController() {
		coetRepository = CoetRepository.getInstancia();
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
	 * Mètode que s'encarrega de que tots els propulsors assoleixin una potència
	 * objectiu i es queda a la espera fins llavors
	 * 
	 * @param potenciaObjectiu
	 */
	public void assolirPotenciaObjectiu(int potenciaObjectiu) {
		asignarPotenciaObjectiu(potenciaObjectiu);
		activarPropulsors();

		// Esperem fins que tots els propulsors arribin a la seva potència objectiu
		while (propulsorsActius()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Asigna la mateixa potència objectiu a tots els propulsors.
	 * 
	 * @param potenciaObjectiu
	 */
	public void asignarPotenciaObjectiu(int potenciaObjectiu) {
		for (Coet c : coetRepository.getAllCoets()) {
			for (Propulsor p : c.getPropulsors()) {
				p.setPotenciaObjectiu(potenciaObjectiu);
			}
		}
	}

	/**
	 * Llança un thread per cada propulsor que el va accelerant o frenant fins
	 * assolir la potència objectiu
	 */
	public void activarPropulsors() {
		for (Coet c : coetRepository.getAllCoets()) {
			for (int i = 0; i < c.getPropulsors().size(); i++) {
				PropulsorThread t = new PropulsorThread(c, i, this);
				t.start();
			}
		}
	}

	/**
	 * Asigna valor 0 als atributs potenciaActual i potenciaObjectiu de tots els
	 * propulsors
	 */
	public void aturarPropulsors() {
		for (Coet c : coetRepository.getAllCoets()) {
			for (Propulsor p : c.getPropulsors()) {
				p.setPotenciaActual(0);
				p.setPotenciaObjectiu(0);
			}
		}
	}

	/**
	 * Mètode que calcula la potència necessaria per assolir una determinada
	 * velocitat i la reparteix entre els propulsors. Llança una excepció en cas que
	 * els propulsors no siguin capaços de produïr la potència necessaria
	 * 
	 * @param velocitatInicial
	 * @param velocitatObjectiu
	 * @throws Exception
	 */
	public void assolirVelocitat(int velocitatInicial, int velocitatObjectiu) throws Exception {
		aturarPropulsors();

		int potenciaTotalNecessaria = (int) Math.pow(((velocitatObjectiu - velocitatInicial) / 100), 2);
		repartirPotencia(potenciaTotalNecessaria);
		activarPropulsors();

		// Esperem fins que tots els propulsors arribin a la seva potència objectiu
		while (propulsorsActius()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Comprovem si els coets han pogut arribar a la potència necessària

		String coetsAmbPotenciaInsuficient = "";
		int potenciaTotalActual = 0;

		for (Coet c : coetRepository.getAllCoets()) {
			// Sumem la potència actual dels propulsors del coet
			for (Propulsor p : c.getPropulsors()) {
				potenciaTotalActual += p.getPotenciaActual();
			}

			// Guardem els IDs dels coets que no han produït potència suficient
			if (potenciaTotalActual < potenciaTotalNecessaria)
				coetsAmbPotenciaInsuficient += c.getId() + " ";
			potenciaTotalActual = 0;
		}

		if (coetsAmbPotenciaInsuficient.length() > 0) {
			throw new Exception("\n--> Coets amb potència insuficient: " + coetsAmbPotenciaInsuficient
					+ "<--\n--> La potència necessària és " + potenciaTotalNecessaria + " <--");
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
	public void repartirPotencia(int potencia) {
		int potenciaRepartida = 0;
		int potenciaCoet;

		for (Coet c : coetRepository.getAllCoets()) {
			potenciaCoet = getPotenciaCoet(c);

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
			potenciaRepartida = 0;
		}
	}

	/**
	 * Augmenta la potència actual d'un propulsor
	 * 
	 * @param propulsor
	 */
	public void accelerar(Propulsor propulsor) {
		propulsor.setPotenciaActual(propulsor.getPotenciaActual() + 1);
	}

	/**
	 * Disminueix la potència actual d'un propulsor
	 * 
	 * @param propulsor
	 */
	public void frenar(Propulsor propulsor) {
		propulsor.setPotenciaActual(propulsor.getPotenciaActual() - 1);
	}

	/*
	 * Comprova si queda algún propulsor que encara li falta assolir la potència
	 * objectiu
	 */
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
