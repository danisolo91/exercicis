package application;

import domain.MediaPlayerState;
import domain.MediaPlayerStatus;
import domain.Video;
import utilities.Utils;
import view.MediaPlayerView;

public class MediaPlayerController {

	private MediaPlayerState state;
	private MediaPlayerView mediaPlayerView;

	public MediaPlayerController() {
		state = new MediaPlayerState();
		mediaPlayerView = new MediaPlayerView();
	}

	public void playVideo(Video video) {
		String option;
		String currentTime;
		String videoDuration = Utils.timeParser(video.getDuration());

		// Es mostra el reproductor fins que l'usuari surt
		while (state.isRunning()) {

			// Parem el reproductor si arriba a la duració máxima del video
			if (state.getCounter() >= video.getDuration()) {
				state.setStatus(MediaPlayerStatus.STOP);
				state.setCounter(0);
			}

			currentTime = Utils.timeParser(state.getCounter());

			// Mostrem el reproductor i guardem l'opció escollida per l'usuari
			option = mediaPlayerView.showMediaPlayer(video.getTitle(), currentTime, videoDuration, state.getStatus());

			switch (option.toLowerCase()) {
			case "a":
				if (!state.getStatus().equals(MediaPlayerStatus.PLAYING)) {
					state.setStatus(MediaPlayerStatus.PLAYING);

					// Llancem un thread per anar incrementant els segons
					MediaPlayerCounter mediaPlayerTimeCounter = new MediaPlayerCounter(state);
					mediaPlayerTimeCounter.start();
				}
				break;
			case "b":
				state.setStatus(MediaPlayerStatus.PAUSE);
				break;
			case "c":
				state.setStatus(MediaPlayerStatus.STOP);
				state.setCounter(0);
				break;
			case "d":
				break;
			case "e":
				state.setRunning(false);
				break;
			default:
				mediaPlayerView.showMessage("--> L'opció escollida no existeix. <--");
			}
		}
		mediaPlayerView.showMessage("--> Has sortit del reproductor multimèdia. <--");
	}
}
