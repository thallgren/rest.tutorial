package se.tada.tutorial.hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/bye")
public class GoodBye {

	@GET
	public String byeString() {
		return "bye";
	}
}
