package se.tada.tutorial.hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorld {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "JAX-RS säger \"Hej Världen\"";
	}

	@GET
	@Path("/en")
	@Produces(MediaType.TEXT_PLAIN)
	public String helloInEnglish() {
		return "JAX-RS says \"Hello World\"";
	}

	@GET
	@Path("/formatted")
	@Produces(MediaType.TEXT_HTML)
	public String helloHTML() {
		return "<html><body><b>JAX-RS</b> says <i>&quot;Hello World&quot;</i></body></html>";
	}
}
