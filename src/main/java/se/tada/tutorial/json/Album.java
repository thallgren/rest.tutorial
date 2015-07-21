package se.tada.tutorial.json;

public class Album {
	private final String band;

	private final String title;

	public Album(String title, String band) {
		this.title = title;
		this.band = band;
	}

	public String getBand() {
		return band;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return "Track [title=" + title + ", band=" + band + "]";
	}
}
