package exercicis;

public class Nivell1Fase4 {

	public static void main(String[] args) {
		
		String nom = "Lionel";
		String cognom1 = "Messi";
		String cognom2 = "Cuccittini";
		int dia = 24;
		int mes = 6;
		int anyNaixement = 1987;
		final int anyTraspas = 1948;
		int traspas = 4;
		boolean esAnyTraspas = false;
		String fraseTraspas = "L'any " + anyNaixement + " és de traspàs.";
		String fraseNoTraspas = "L'any " + anyNaixement + " no és de traspàs.";
		String nomComplet = nom + " " + cognom1 + " " + cognom2;
		String data = dia + "/" + mes + "/" + anyNaixement;
		
		for(int i = anyTraspas; i <= anyNaixement; i += traspas) {
			if(i == anyNaixement) {
				esAnyTraspas = true;
			}
		}
		
		System.out.println("El meu nom és " + nomComplet);
		System.out.println("Vaig nèixer el " + data);
		System.out.println(esAnyTraspas ? fraseTraspas : fraseNoTraspas);
	}

}
