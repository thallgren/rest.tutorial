package se.tada.tutorial.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Simple JPA Entity
 */
@Entity(name = "tracks")
public class Track {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String singer;

	public long getId() {
		return id;
	}

	public String getSinger() {
		return singer;
	}

	public String getTitle() {
		return title;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
