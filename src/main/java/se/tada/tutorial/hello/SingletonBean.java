package se.tada.tutorial.hello;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Singleton
@Path("/singleton")
public class SingletonBean {

	@Inject
	UriInfo uriInfo;

	@Inject
	Request request;

	int counter = 42;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String get() {
		return String.format("OK %s uri=%s", request.getMethod(), uriInfo.getRequestUri());
	}

	@Path("counter")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public synchronized String getCount() {
		return String.format("%d", counter++);
	}

	@Path("counter")
	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	public synchronized void setCount(String c) {
		counter = Integer.decode(c);
	}
}
