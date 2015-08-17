package se.tada.tutorial.jpa.test;

import javax.ws.rs.core.Application;
import org.glassfish.jersey.test.JerseyTest;

import se.tada.RestApp;

public abstract class AbstractJPATest extends JerseyTest {
	@Override
	protected Application configure() {
		return new RestApp();
	}
}
