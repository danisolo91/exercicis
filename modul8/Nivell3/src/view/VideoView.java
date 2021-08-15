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
			System.out.println(e.getMessage());
		}

		// Aquestes variables no les ha d'introduïr l'usuari perquè no depenen d'ell
		String url = Utils.makeUrl(title);
		int userId = loggedUser.getId();
		int duration = 72; // simulem una durada a cada video

		try {
			Video video = new Video(url, title, tags, userId, duration);
			videoController.uploadVideo(video);
			System.out.println("--> Video pujat amb èxit. <--");
		} catch (Exception e) {
			System.out.println(e.getMessage());
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

	public void playVideo() {
		System.out.println("\nIntrodueix l'ID del video que vols reproduïr:");
		int videoId = SCAN.nextInt();
		try {
			videoController.playVideo(videoId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
