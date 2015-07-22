package se.tada;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;

import se.tada.tutorial.jpa.EntityManagerFactoryProvider;
import se.tada.tutorial.jpa.EntityManagerProvider;

/**
 * Jersey Specific resource config
 */
@ApplicationPath("/*")
public class RestApp extends ResourceConfig {
	public RestApp() {
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bindFactory(new EntityManagerFactoryProvider("tutorial")).to(EntityManagerFactory.class).in(Singleton.class);
				bindFactory(EntityManagerProvider.class).to(EntityManager.class).in(RequestScoped.class);
			}
		});
		packages("se.tada");
	}
}
