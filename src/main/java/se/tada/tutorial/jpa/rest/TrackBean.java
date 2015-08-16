package se.tada.tutorial.jpa.rest;

import java.net.URI;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import se.tada.tutorial.jpa.model.Track;

@ManagedBean
@Path("/tracks")
public class TrackBean extends AbstractJPABean {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Track> getTracks() {
		return em.createQuery("SELECT t FROM tracks t", Track.class).getResultList();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Track getTrack(@PathParam("id") Long id) {
		return em.find(Track.class, id);
	}

	@DELETE
	@Path("/{id}")
	public void deleteTrack(@PathParam("id") Long id) {
		transaction(em -> {
			em.remove(getTrack(id));
			return null;
		});
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
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

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateTrack(@PathParam("id") Long id, Track track) {
		transaction(em -> {
			Track trackToUpdate = getTrack(id);
			trackToUpdate.setTitle(track.getTitle());
			trackToUpdate.setSinger(track.getSinger());
			em.merge(trackToUpdate);
			return null;
		});
	}
}
