package exercicis;

public class Nivell3 {

	public static void main(String[] args) {
		
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int aux;
		
		for(int i = 1; i <= arr.length / 2; i++) {
			aux = arr[i-1];
			arr[i-1] = arr[arr.length-i];
			arr[arr.length-i] = aux;
		}
		
		for(int num: arr) {
			System.out.print(num + " ");
		}
		
	}

}
