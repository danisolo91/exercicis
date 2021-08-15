package persistance;

import java.util.ArrayList;
import java.util.List;

import domain.Video;

public class VideoRepository {

	private static List<Video> videos;

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

	public Video getVideoById(int id) throws Exception {
		Video video = null;

		try {
			video = videos.get(id - 1);
		} catch (IndexOutOfBoundsException e) {
			throw new Exception("--> No existeix cap video amb l'ID introduït. <--");
		}

		return video;
	}

	public void addVideo(Video video) {
		videos.add(video);
	}

	public void updateVideo(Video video) {
		videos.set(video.getId() - 1, video);
	}
}
