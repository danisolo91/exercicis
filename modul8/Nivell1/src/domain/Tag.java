package domain;

public class Tag {

	private String text;

	public Tag(String text) throws Exception {
		if (text.isBlank())
			throw new Exception("--> El tag no pot estar buit. <--");
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Tag [text=" + text + "]";
	}
}
