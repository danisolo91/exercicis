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
		
		for(char c: nom) {
			if(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
				System.out.println(c + " VOCAL");
			} else if(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || 
					c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
				System.out.println(c + " Els noms de les persones no contenen números!");
			} else if(c == ' ' ) {
				System.out.println(" ");
			} else {
				System.out.println(c + " CONSONANT");
			}
		}
		
	}
	
}
