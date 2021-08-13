package application;

import domain.Video;
import domain.VideoStatus;

/**
 * Thread que s'encarrega de simular el temps que es triga en passar d'un estat
 * del video a un altre. Rep per paràmetre el video del qual s'ha d'anar
 * actualitzant l'estat i també el controlador que s'encarregarà de persistir a
 * la BBDD les dades actualizades del video.
 * 
 * @author daniel
 *
 */
public class VideoStatusUpdater extends Thread {

	private VideoController videoController;
	private Video video;

	public VideoStatusUpdater(Video video, VideoController videoController) {
		this.video = video;
		this.videoController = videoController;
	}

	public void run() {
		VideoStatus[] status = VideoStatus.values();

		for (VideoStatus s : status) {
			if (s.equals(VideoStatus.UPLOADING)) {
				try {
					VideoStatusUpdater.sleep(10000);
					video.setStatus(VideoStatus.VERIFYING);
					videoController.updateVideo(video);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (s.equals(VideoStatus.VERIFYING)) {
				try {
					VideoStatusUpdater.sleep(10000);
					video.setStatus(VideoStatus.PUBLIC);
					videoController.updateVideo(video);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
