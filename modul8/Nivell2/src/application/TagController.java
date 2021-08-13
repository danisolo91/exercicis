package application;

import java.util.ArrayList;
import java.util.List;

import domain.Tag;

public class TagController {

	public List<Tag> parseTags(String str) throws Exception {
		List<Tag> tags = new ArrayList<Tag>();

		for (String s : str.split("\\s")) {
			tags.add(new Tag(s));
		}

		return tags;
	}
}
