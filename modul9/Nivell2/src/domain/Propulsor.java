package domain;

public class Propulsor {

	private int id;
	private int potenciaActual;
	private int potenciaObjectiu;
	private int potenciaMaxima;

	private static int counter = 0;

	public Propulsor(int potenciaActual, int potenciaObjectiu, int potenciaMaxima) {
		this.potenciaActual = potenciaActual;
		this.potenciaObjectiu = potenciaObjectiu;
		this.potenciaMaxima = potenciaMaxima;

		id = counter;
		counter++;
	}

	public int getId() {
		return id;
	}

	public int getPotenciaActual() {
		return potenciaActual;
	}

	public void setPotenciaActual(int potenciaActual) {
		this.potenciaActual = potenciaActual;
	}

	public int getPotenciaObjectiu() {
		return potenciaObjectiu;
	}

	public void setPotenciaObjectiu(int potenciaObjectiu) {
		if (potenciaObjectiu > this.potenciaMaxima) {
			this.potenciaObjectiu = this.potenciaMaxima;
		} else {
			this.potenciaObjectiu = potenciaObjectiu;
		}
	}

	public int getPotenciaMaxima() {
		return potenciaMaxima;
	}

	public void setPotenciaMaxima(int potenciaMaxima) {
		this.potenciaMaxima = potenciaMaxima;
	}

	@Override
	public String toString() {
		return " [" + potenciaActual + " " + potenciaObjectiu + " " + potenciaMaxima + "] ";
	}
}
