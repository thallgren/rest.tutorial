package se.tada.tutorial.cdi.rest;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@ManagedBean
@ApplicationScoped
@Path("/singleton")
public class Singleton {

	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	/**
	 * This resource is defined in web.xml
	 */
	@Resource(name = "injectedResource")
	int counter = 0;

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
