package application;

import java.util.List;

import domain.Video;
import persistance.VideoRepository;

public class VideoController {

	private VideoRepository videoRepository = new VideoRepository();

	public List<Video> getVideos() {
		return videoRepository.getAllVideos();
	}

	public Video getVideo(int id) {
		return videoRepository.getVideoById(id);
	}

	public void uploadVideo(Video video) {
		videoRepository.addVideo(video);
	}

}
