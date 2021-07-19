package com.vehicles.project;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Contesta sí o no:");
		boolean resposta = scan.nextBoolean();
		
		System.out.println(resposta);
		
		scan.close();
	}

}
