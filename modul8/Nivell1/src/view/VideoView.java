package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Utilities.Utils;
import application.UserController;
import application.VideoController;
import domain.Tag;
import domain.User;
import domain.Video;

public class VideoView {

	private static final Scanner SCAN = new Scanner(System.in);
	private VideoController videoController = new VideoController();
	private UserController userController = new UserController();

	public void uploadVideo() {
		User loggedUser = userController.getLoggedUser();

		System.out.println("\nIntrodueix el títol del video:");
		String title = SCAN.nextLine();
		List<Tag> tags = getTags();
		String url = Utils.makeUrl(title);
		int userId = loggedUser.getId();

		Video video = new Video(url, title, tags, userId);

		videoController.uploadVideo(video);

		System.out.println("--> Video pujat amb èxit. <--");
	}

	public List<Tag> getTags() {
		List<Tag> tags = new ArrayList<Tag>();
		boolean keepAdding = true;
		String tag;

		do {
			System.out.println("Introdueix tag:");
			tag = SCAN.nextLine();
			tags.add(new Tag(tag));

			System.out.println("Vols seguir introduint tags? [S/N]: ");
			keepAdding = Utils.scanBoolean();
		} while (keepAdding);

		return tags;
	}

	public void showVideos() {
		List<Video> videos = videoController.getVideos();

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
