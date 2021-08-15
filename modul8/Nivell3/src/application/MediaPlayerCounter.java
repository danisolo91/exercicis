package application;

import domain.MediaPlayerState;
import domain.MediaPlayerStatus;

public class MediaPlayerCounter extends Thread {

	private MediaPlayerState state;

	public MediaPlayerCounter(MediaPlayerState state) {
		this.state = state;
	}

	public void run() {
		while (state.getStatus().equals(MediaPlayerStatus.PLAYING)) {
			try {
				Thread.sleep(1000);

				// Tornem a comprovar s'estat per si ha vanviat durant l'espera...
				if (state.getStatus().equals(MediaPlayerStatus.PLAYING)) {
					state.incrementCounter();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
