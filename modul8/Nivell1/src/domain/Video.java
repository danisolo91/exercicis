package domain;

import java.util.ArrayList;
import java.util.List;

public class Video {

	private int id;
	private String url;
	private String title;
	private List<Tag> tags = new ArrayList<Tag>();
	private int userId;

	private static int COUNTER = 1;

	public Video() {
	}

	public Video(String url, String title, List<Tag> tags, int userId) {
		this.url = url;
		this.title = title;
		this.tags = tags;
		this.userId = userId;

		id = COUNTER;
		COUNTER++;
	}

	public int getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", url=" + url + ", title=" + title + ", tags=" + tags + ", userId=" + userId + "]";
	}

}
