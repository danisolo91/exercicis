package persistance;

import java.util.ArrayList;
import java.util.List;

import domain.Coet;
import domain.Propulsor;

public class CoetRepository {

	private static CoetRepository instancia;
	private List<Coet> coets = new ArrayList<Coet>();

	private CoetRepository() {
		List<Propulsor> propulsors1 = new ArrayList<Propulsor>();
		propulsors1.add(new Propulsor(0, 0, 10));
		propulsors1.add(new Propulsor(0, 0, 30));
		propulsors1.add(new Propulsor(0, 0, 80));

		List<Propulsor> propulsors2 = new ArrayList<Propulsor>();
		propulsors2.add(new Propulsor(0, 0, 30));
		propulsors2.add(new Propulsor(0, 0, 40));
		propulsors2.add(new Propulsor(0, 0, 50));
		propulsors2.add(new Propulsor(0, 0, 50));
		propulsors2.add(new Propulsor(0, 0, 30));
		propulsors2.add(new Propulsor(0, 0, 10));

		coets.add(new Coet("32WESSDS", propulsors1));
		coets.add(new Coet("LDSFJA32", propulsors2));
	}

	/*
	 * Mètode per demanar la instància del repository
	 */
	public static CoetRepository getInstancia() {
		if (instancia == null) {
			instancia = new CoetRepository();
		}
		return instancia;
	}

	public List<Coet> getAllCoets() {
		return coets;
	}
}
