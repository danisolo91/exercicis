package view;

import domain.MediaPlayerStatus;
import utilities.NotEmptyScanner;

public class MediaPlayerView {

	private static final NotEmptyScanner SCAN = new NotEmptyScanner();

	public String showMediaPlayer(String title, String currentTime, String videoDuration, MediaPlayerStatus status) {
		String option;

		for (int i = 0; i < 50; ++i)
			System.out.println();
		System.out.println(" ======================================================================");
		System.out.println("                              MEDIA PLAYER                             ");
		System.out.println(" ----------------------------------------------------------------------");
		System.out.println("  Títol: " + title);
		System.out.println("  Duració: " + currentTime + " - " + videoDuration);
		System.out.println("  Estat: " + status);
		System.out.println(" ----------------------------------------------------------------------");
		System.out.println("  a) Reproduïr    b) Pausar    c) Parar    d) Actualitzar    e) Sortir");
		System.out.println(" ======================================================================");
		System.out.println("\nIntrodueix una opció: ");
		System.out.println("\n\n");
		option = SCAN.nextLine();

		return option;
	}

	public void showMessage(String message) {
		System.out.println(message);
	}
}
