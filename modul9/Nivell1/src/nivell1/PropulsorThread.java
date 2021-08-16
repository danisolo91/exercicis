package nivell1;

public class PropulsorThread extends Thread {

	private Coet coet;
	private int propulsor;

	public PropulsorThread(Coet coet, int propulsor) {
		this.coet = coet;
		this.propulsor = propulsor;
	}

	public void run() {
		int potenciaActual = coet.getPropulsors()[propulsor][0];
		int potenciaObjectiu = coet.getPropulsors()[propulsor][1];

		// ACCELERAR
		if (potenciaActual < potenciaObjectiu) {
			while (potenciaActual < potenciaObjectiu) {
				try {
					coet.accelerar(propulsor);
					System.out.println(coet.getId() + "-" + propulsor + ":" + coet.getPropulsorToString(propulsor));
					Thread.sleep(200); // Per veure en consola com va accelerant...
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				potenciaActual = coet.getPropulsors()[propulsor][0];
				potenciaObjectiu = coet.getPropulsors()[propulsor][1];
			}
		}

		// FRENAR
		else {
			while (potenciaActual > potenciaObjectiu) {
				try {
					coet.frenar(propulsor);
					System.out.println(coet.getId() + "-" + propulsor + ":" + coet.getPropulsorToString(propulsor));
					Thread.sleep(200); // Per veure en consola com va frenant...
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				potenciaActual = coet.getPropulsors()[propulsor][0];
				potenciaObjectiu = coet.getPropulsors()[propulsor][1];
			}
		}
	}
}
