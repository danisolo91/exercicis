package exercicis;

public class Nivell3 {

	public static void main(String[] args) {

		int hour = 0;
		int minutes = 0;
		int seconds = 0;

		while (true) {
			try {
				System.out.println(
					(hour < 10 ? "0" + hour : hour) + ":" + 
					(minutes < 10 ? "0" + minutes : minutes) + ":" + 
					(seconds < 10 ? "0" + seconds : seconds)
				);

				if (seconds < 59) {
					seconds += 1;
				} else {
					seconds = 0;
					minutes += 1;
				}

				if (minutes == 60) {
					minutes = 0;
					hour += 1;
				}

				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
