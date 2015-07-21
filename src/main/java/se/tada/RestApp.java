package se.tada;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Jersey Specific resource config
 */
@ApplicationPath("/*")
public class RestApp extends ResourceConfig {
	public RestApp() {
		packages("se.tada");
	}
}
