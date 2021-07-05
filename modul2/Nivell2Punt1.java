package exercicis;

import javax.swing.JOptionPane;

public class Nivell2Punt1 {

	public static void main(String[] args) {
		
		int altura = Integer.parseInt(JOptionPane.showInputDialog(null, "Introdueix altura:"));
		String linia = "";
		
		for(int i = 1; i <= altura; i++) {
			linia += i;
			System.out.println(linia);
		}
		
	}

}
