package se.tada.tutorial.jpa.test;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import se.tada.tutorial.jpa.model.Track;

public class TrackBeanTest extends AbstractJPATest {
	private static final GenericType<List<Track>> LIST_OF_TRACK = new GenericType<List<Track>>() {
	};

	@Test
	public void testAll() {
		List<Track> tracks = target("tracks").request().get(LIST_OF_TRACK);
		assertFalse(tracks.isEmpty());
		Track first = tracks.get(0);
		assertFalse(first.getTitle().isEmpty());
		assertFalse(first.getSinger().isEmpty());
	}

	@Test
	public void testById() {
		Track track = target("tracks/1").request().get(Track.class);
		assertNotNull(track);
		assertFalse(track.getTitle().isEmpty());
		assertFalse(track.getSinger().isEmpty());
	}

	@Test
	public void testCreate() {
		Track track = new Track();
		track.setSinger("Melody Gardot");
		track.setTitle("Ain't no sunshine when you're gone");

		Response response = target("tracks").request().post(Entity.<Track> entity(track, MediaType.APPLICATION_JSON));
		assertEquals(201, response.getStatus());
		URI location = response.getLocation();
		assertNotNull(location);
		Track created = this.client().target(location).request().get(Track.class);
		assertNotNull(created);
		assertEquals(track.getTitle(), created.getTitle());
		assertEquals(track.getSinger(), created.getSinger());
		assertNotNull(created.getId());
	}
}
