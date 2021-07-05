package exercicis;

import java.util.ArrayList;
import java.util.List;

public class Nivell1Fase2 {

	public static void main(String[] args) {
		
		List<Character> nom = new ArrayList<Character>();
		nom.add('L');
		nom.add('E');
		nom.add('O');
		nom.add(' ');
		nom.add('M');
		nom.add('E');
		nom.add('S');
		nom.add('S');
		nom.add('I');
		
		String vocals = "aeiouAEIOU";
		String numeros = "0123456789";
		
		for(char c: nom) {
			if(vocals.contains(Character.toString(c))) {
				System.out.println(c + " VOCAL");
			} else if(numeros.contains(Character.toString(c))) {
				System.out.println(c + " Els noms de les persones no contenen números!");
			} else if(c == ' ' ) {
				System.out.println(" ");
			} else {
				System.out.println(c + " CONSONANT");
			}
		}
		
	}
	
}

