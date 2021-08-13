package persistance;

import java.util.ArrayList;
import java.util.List;

import domain.Video;

public class VideoRepository {

	private List<Video> videos;

	public VideoRepository() {
		videos = new ArrayList<Video>();
	}

	public List<Video> getAllVideos() {
		return videos;
	}

	public List<Video> getVideosByUserId(int id) {
		List<Video> userVideos = new ArrayList<Video>();

		for (Video v : videos) {
			if (v.getUserId() == id) {
				userVideos.add(v);
			}
		}

		return userVideos;
	}

	public Video getVideoById(int id) {
		return videos.get(id);
	}

	public void addVideo(Video video) {
		videos.add(video);
	}

	public void updateVideo(Video video) {
		videos.set(video.getId() - 1, video);
	}
}
