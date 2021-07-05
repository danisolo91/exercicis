package exercicis;

public class Nivell1Fase3 {

	public static void main(String[] args) {

		int anyNaixement = 1987;
		final int anyTraspas = 1948;
		int traspas = 4;
		boolean esAnyTraspas = false;
		String fraseTraspas = "L'any " + anyNaixement + " és de traspàs.";
		String fraseNoTraspas = "L'any " + anyNaixement + " no és de traspàs.";
		
		for(int i = anyTraspas; i <= anyNaixement; i += traspas) {
			if(i == anyNaixement) {
				esAnyTraspas = true;
			}
		}
		
		if(esAnyTraspas) {
			System.out.println(fraseTraspas);
		} else {
			System.out.println(fraseNoTraspas);
		}
		
	}

}
