package modul3;

public class Nivell3 {

	public static void main(String[] args) {

		int numero = 7;
		int actual = 0;
		int anterior = 0;
		int aux;

		for (int i = 1; i <= numero; i++) {
			System.out.print(actual + " ");
			if (i == 1) {
				actual = 1;
			} else {
				aux = actual;
				actual += anterior;
				anterior = aux;
			}
		}

	}

}
