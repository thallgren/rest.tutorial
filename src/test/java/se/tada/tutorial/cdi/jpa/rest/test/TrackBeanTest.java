package se.tada.tutorial.cdi.jpa.rest.test;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.jboss.weld.environment.se.Weld;

import se.tada.tutorial.cdi.jpa.rest.TrackBean;

public class TrackBeanTest extends JerseyTest {
	@Override
	protected Application configure() {
		final Weld weld = new Weld();
		weld.initialize();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> weld.shutdown()));
		return new ResourceConfig(TrackBean.class);
	}
}
