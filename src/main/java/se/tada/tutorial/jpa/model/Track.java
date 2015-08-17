package se.tada.tutorial.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Simple JPA Entity
 */
@Entity(name = "tracks")
public class Track extends EntityWithId {
	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String singer;

	public String getSinger() {
		return singer;
	}

	public String getTitle() {
		return title;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
