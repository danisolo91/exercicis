package exercicis;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Nivell3 {

	public static void main(String[] args) {

		List<Alumne> alumnes = new ArrayList<Alumne>();
		alumnes.add(new Alumne("Daniel", 29, "Java", 10));
		alumnes.add(new Alumne("Alex", 18, "PHP", 8));
		alumnes.add(new Alumne("Maria", 24, "Javascript", 9));
		alumnes.add(new Alumne("Ruben", 31, "Java", 4));
		alumnes.add(new Alumne("Cristina", 29, "HTML", 6));
		alumnes.add(new Alumne("Laura", 22, "PHP", 3));
		alumnes.add(new Alumne("David", 19, "Java", 5));
		alumnes.add(new Alumne("Adrea", 25, "PHP", 8));
		alumnes.add(new Alumne("Oliver", 23, "CSS", 4));
		alumnes.add(new Alumne("Javier", 29, "Java", 7));

		// Punt 1
		// alumnes.stream().forEach(a -> System.out.println(a.getNom() + " - " + a.getEdat()));

		// Punt 2
		// List<Alumne> result = alumnes.stream().filter(a -> a.getNom().startsWith("A")).collect(Collectors.toList());
		// result.forEach(System.out::println);

		// Punt 3
		// alumnes.stream().filter(a -> a.getNota() >= 5).forEach(System.out::println);
		
		// Punt 4
		// alumnes.stream().filter(a -> a.getNota() >= 5 && a.getCurs() != "PHP").forEach(System.out::println);
		
		// Punt 5
		// alumnes.stream().filter(a -> a.getCurs().equals("Java") && a.getEdat() >= 18).forEach(System.out::println);
	}

}
