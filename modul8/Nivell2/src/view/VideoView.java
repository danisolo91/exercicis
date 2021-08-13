package view;

import java.util.ArrayList;
import java.util.List;

import application.TagController;
import application.UserController;
import application.VideoController;
import domain.Tag;
import domain.User;
import domain.Video;
import utilities.NotEmptyScanner;
import utilities.Utils;

public class VideoView {

	private static final NotEmptyScanner SCAN = new NotEmptyScanner();
	private VideoController videoController;
	private TagController tagController;
	private UserController userController;

	public VideoView() {
		videoController = new VideoController();
		tagController = new TagController();
		userController = new UserController();
	}

	public void uploadVideo() {
		User loggedUser = userController.getLoggedUser();

		System.out.println("\nIntrodueix el títol del video:");
		String title = SCAN.nextLine();
		System.out.println("Introdueix tags separats amb espais en blanc:");
		List<Tag> tags = new ArrayList<Tag>();

		try {
			tags = tagController.parseTags(SCAN.nextLine());
		} catch (Exception e) {
			e.getMessage();
		}

		String url = Utils.makeUrl(title);
		int userId = loggedUser.getId();

		try {
			Video video = new Video(url, title, tags, userId);
			videoController.uploadVideo(video);
			System.out.println("--> Video pujat amb èxit. <--");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void listVideos() {
		User loggedUser = userController.getLoggedUser();
		List<Video> videos = videoController.getUserVideos(loggedUser);

		System.out.println("\nELS MEUS VIDEOS:");
		if (videos.size() > 0) {
			for (Video v : videos) {
				System.out.println(v.toString());
			}
		} else {
			System.out.println("--> No tens cap video! <--");
		}
	}
}
