package se.tada.tutorial.jpa.rest;

import javax.annotation.ManagedBean;
import javax.ws.rs.Path;
import se.tada.tutorial.jpa.model.Track;

@ManagedBean
@Path("/tracks")
public class TrackBean extends AbstractCRUDBean<Track> {

	public TrackBean() {
		super(Track.class, "tracks");
	}

	@Override
	protected void copy(Track source, Track dest) {
		dest.setTitle(source.getTitle());
		dest.setSinger(source.getSinger());
	}
}
