package se.tada.tutorial.json;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/json")
public class JSONExample {
	@GET
	@Path("/track")
	@Produces(MediaType.APPLICATION_JSON)
	public Album getTrack() {
		return new Album("Smoke on the water", "Deep Purple");
	}
}
