package application;

import java.util.Date;

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
		long timeElapsed;
		Date now;
		boolean run = true;
		
		while(run) {
			now = new Date();
			timeElapsed = now.getTime() - video.getUploadDate().getTime();
			
			if(timeElapsed <= 10000) {
				if(video.getStatus() != VideoStatus.UPLOADING) {
					video.setStatus(VideoStatus.UPLOADING);
					videoController.updateVideo(video);
				}
			} else if(timeElapsed > 10000 && timeElapsed <= 20000) {
				if(video.getStatus() != VideoStatus.VERIFYING) {
					video.setStatus(VideoStatus.VERIFYING);
					videoController.updateVideo(video);
				}
			} else if(timeElapsed > 20000) {
				if(video.getStatus() != VideoStatus.PUBLIC) {
					video.setStatus(VideoStatus.PUBLIC);
					videoController.updateVideo(video);
					run = false;
				}
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
