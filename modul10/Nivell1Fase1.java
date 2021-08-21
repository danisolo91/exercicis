package exercicis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Nivell1Fase1 {

	public static void main(String args[]) {

		// Punt 1
		System.out.println(punt1(Arrays.asList("Alex", "Amy", "Daniel", "AWS", "Rubén")));

		// Punt 2
		System.out.println(punt2(Arrays.asList(1, 2, 3, 4, 5)));
		
		// Punt 3
		System.out.println(punt3(Arrays.asList("ordinador", "niu", "porta", "cadira")));
		
		// Punt 4
		System.out.println(punt4(Arrays.asList("ordinador", "niu", "porta", "cadira")));
		
		List<String> mesos = Arrays.asList("Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol", "Agost", "Setembre", "Octubre", "Novembre", "Desembre");
		
		// Punt 5
		punt5(mesos);
		
		// Punt 6
		punt6(mesos);		
		
	}

	public static List<String> punt1(List<String> nomsPropis) {
		return nomsPropis.stream()
				.filter(n -> n.startsWith("A") && n.length() == 3)
				.collect(Collectors.toList());
	}

	public static String punt2(List<Integer> nombres) {
		return nombres.stream()
				.map(n -> n % 2 == 0 ? "e" + n : "o" + n)
				.collect(Collectors.joining(", "));
	}
	
	public static List<String> punt3(List<String> cadenes) {
		return cadenes.stream()
				.filter(c -> c.contains("o"))
				.collect(Collectors.toList());
	}
	
	public static List<String> punt4(List<String> cadenes) {
		return cadenes.stream()
				.filter(c -> c.contains("o") || c.length() > 5)
				.collect(Collectors.toList());
	}
	
	public static void punt5(List<String> mesos) {
		mesos.forEach(m -> System.out.println(m));
	}
	
	public static void punt6(List<String> mesos) {
		mesos.forEach(System.out::println);
	}
}
