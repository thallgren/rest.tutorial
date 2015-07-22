package se.tada.tutorial.jpa.rest;

import java.net.URI;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import se.tada.tutorial.jpa.model.Track;

@ManagedBean
@Path("/jpa")
public class TrackBean extends AbstractJPABean {
	@GET
	@Path("/tracks")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Track> getTracks() {
		return em.createQuery("SELECT t FROM tracks t", Track.class).getResultList();
	}

	@GET
	@Path("/tracks/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Track getTrack(@PathParam("id") Long id) {
		return em.find(Track.class, id);
	}

	@POST
	@Path("/tracks")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTrack(Track newTrack) {
		Track created = transaction(em -> {
			Track track = new Track();
			track.setTitle(newTrack.getTitle());
			track.setSinger(newTrack.getSinger());
			em.persist(track);
			return track;
		});
		return Response.created(URI.create("jpa/tracks/" + created.getId())).build();
	}
}
