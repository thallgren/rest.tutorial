package se.tada.tutorial.jpa.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Application;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import se.tada.tutorial.jpa.EntityManagerProvider;

public abstract class AbstractJPATest extends JerseyTest {
	@Override
	protected Application configure() {
		ResourceConfig config = new ResourceConfig();
		config.register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(Persistence.createEntityManagerFactory("tutorial")).to(EntityManagerFactory.class);
				bindFactory(EntityManagerProvider.class).to(EntityManager.class);
			}
		});
		config.packages("se.tada.tutorial");
		return config;
	}
}