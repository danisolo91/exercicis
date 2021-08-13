package application;

import java.util.List;

import domain.User;
import domain.Video;
import persistance.VideoRepository;

public class VideoController {

	private VideoRepository videoRepository;

	public VideoController() {
		videoRepository = new VideoRepository();
	}

	public List<Video> getUserVideos(User user) {
		return videoRepository.getVideosByUserId(user.getId());
	}

	public Video getVideo(int id) {
		return videoRepository.getVideoById(id);
	}

	public void uploadVideo(Video video) {
		videoRepository.addVideo(video);

		// Llança un thread que va actualitzant l'estat
		VideoStatusUpdater updater = new VideoStatusUpdater(video, this);
		updater.start();
	}

	public void updateVideo(Video video) {
		videoRepository.updateVideo(video);
	}

}
