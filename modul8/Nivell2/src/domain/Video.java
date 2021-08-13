package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Video {

	private int id;
	private String url;
	private String title;
	private List<Tag> tags = new ArrayList<Tag>();
	private int userId;
	private Date uploadDate;
	private VideoStatus status;

	private static int COUNTER = 1;

	public Video(String url, String title, List<Tag> tags, int userId) throws Exception {
		if (url.isBlank() || title.isBlank() || tags.size() == 0)
			throw new Exception("--> T'has deixat algún camp en blanc. <--");
		this.url = url;
		this.title = title;
		this.tags = tags;
		this.userId = userId;

		status = VideoStatus.UPLOADING;
		uploadDate = new Date();
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

	public Date getUploadDate() {
		return uploadDate;
	}

	public VideoStatus getStatus() {
		return status;
	}

	public void setStatus(VideoStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", status=" + status + ", url=" + url + ", title=" + title + ", tags=" + tags
				+ ", userId=" + userId + ", uploadDate=" + uploadDate + "]";
	}

}
