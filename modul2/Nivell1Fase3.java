package exercicis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nivell1Fase3 {

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
		
		Map<Character, Integer> mapa = new HashMap<Character, Integer>();
		
		for(char c: nom) {
			if(mapa.containsKey(c)) {
				mapa.put(c, mapa.get(c) + 1);
			} else {
				mapa.put(c, 1);
			}
		}
		
		for(Map.Entry<Character, Integer> entry: mapa.entrySet()) {
			System.out.println("clau='" + entry.getKey() + "', valor=" + entry.getValue());
		}
		
	}
	
}
