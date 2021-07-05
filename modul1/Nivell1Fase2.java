package exercicis;

public class Nivell1Fase2 {

public static void main(String[] args) {
		
		int anyNaixement = 1987;
		final int anyTraspas = 1948;
		int traspas = 4;
		int anysDeTraspas = ((anyNaixement - anyTraspas) / traspas) + 1;
		
		
		System.out.println("Nombre d'anys de traspàs des de " + anyTraspas + " fins " + anyNaixement + ": " + anysDeTraspas);
		System.out.println("Anys de traspàs des de " + anyTraspas + " fins " + anyNaixement + ":");
		
		for(int i = anyTraspas; i <= anyNaixement; i += traspas) {
			System.out.print(i + " ");
		}
		
		
	}
	
}
