package domain;

import java.util.ArrayList;
import java.util.List;

public class Coet {

	private String id;
	private List<Propulsor> propulsors = new ArrayList<Propulsor>();

	public Coet(String id, List<Propulsor> propulsors) {
		this.id = id;
		this.propulsors = propulsors;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Propulsor> getPropulsors() {
		return propulsors;
	}

	public void setPropulsors(List<Propulsor> propulsors) {
		this.propulsors = propulsors;
	}
}
