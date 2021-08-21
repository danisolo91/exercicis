package exercicis;

public class Nivell1Fase3 {

	public static void main(String[] args) {

		Nivell1Fase3Interface obj = s -> new StringBuilder().append(s).reverse().toString();

		System.out.println(obj.reverse("Crea una Functional Interface que continga..."));
	}

}
