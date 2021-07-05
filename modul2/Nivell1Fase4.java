package exercicis;

import java.util.ArrayList;
import java.util.List;

public class Nivell1Fase4 {

	public static void main(String[] args) {
		
		List<Character> name = new ArrayList<Character>();
		name.add('D');
		name.add('A');
		name.add('N');
		name.add('I');
		name.add('E');
		name.add('L');
		
		List<Character> surname = new ArrayList<Character>();
		surname.add('S');
		surname.add('O');
		surname.add('L');
		surname.add('O');
		surname.add('M');
		surname.add('O');
		surname.add('N');
		
		List<Character> fullName = new ArrayList<Character>(name);
		fullName.add(' ');
		fullName.addAll(surname);
		
		System.out.println(fullName);
		
	}

}
