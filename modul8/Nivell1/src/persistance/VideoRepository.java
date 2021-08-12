package persistance;

import java.util.ArrayList;
import java.util.List;

import domain.Video;

public class VideoRepository {

	private List<Video> videos = new ArrayList<Video>();
	
	public List<Video> getAllVideos() {
		return videos;
	}
	
	public Video getVideoById(int id) {
		return videos.get(id);
	}
	
	public void addVideo(Video video) {
		videos.add(video);
	}
}
