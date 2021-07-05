package exercicis;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Nivell2Punt2 {

	public static void main(String[] args) {
		
		int altura = Integer.parseInt(JOptionPane.showInputDialog(null, "Introdueix altura:"));
		List<String> arr = new ArrayList<String>();
		
		// Omplem l'array amb *
		for(int i = 0; i < altura * 2 - 1; i++) {
			arr.add("*");
		}
		
		for(int i = 0; i < altura; i++) {
			// Imprimim tots els * de l'array com a String
			System.out.println(String.join("", arr));
			
			// Substituïm els * dels extrems amb espais en blanc
			arr.set(i, " ");
			if(i > 0) {
				arr.set(arr.size() -1 - i, " ");
			} else {
				arr.set(arr.size() - 1, " ");
			}
		}
		
	}

}
