package nivell1;

public class Coet {

	private String id;
	private int[][] propulsors;

	public Coet(String id, int[][] propulsors) {
		this.id = id;
		this.propulsors = propulsors;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int[][] getPropulsors() {
		return propulsors;
	}

	public void setPropulsors(int[][] propulsors) {
		this.propulsors = propulsors;
	}

	public void setPotenciaObjectiu(int potenciaObjectiu) {
		for (int[] propulsor : propulsors) {
			if (potenciaObjectiu > propulsor[2]) {
				propulsor[1] = propulsor[2];
			} else {
				propulsor[1] = potenciaObjectiu;
			}
		}
	}

	public void accelerar(int propulsor) {
		propulsors[propulsor][0] += 1;
	}

	public void frenar(int propulsor) {
		propulsors[propulsor][0] -= 1;
	}

	/**
	 * Comprova si tots els propulsors han arribat al seu ojectiu de propulsió
	 * 
	 * @return boolean
	 */
	public boolean haTerminat() {
		boolean result = true;
		for (int[] propulsor : propulsors) {
			if (propulsor[0] != propulsor[1]) {
				result = false;
			}
		}
		return result;
	}

	public String getPropulsorsToString() {
		String result = "[";

		for (int[] p : propulsors) {
			result += " [ ";
			for (int value : p) {
				result += " " + value + " ";
			}
			result += " ] ";
		}

		result += "]";

		return result;
	}

	public String getPropulsorToString(int propulsor) {
		return propulsors[propulsor][0] + " " + propulsors[propulsor][1] + " " + propulsors[propulsor][2];
	}
}
