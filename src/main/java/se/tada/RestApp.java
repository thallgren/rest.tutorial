package se.tada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;

import se.tada.tutorial.jpa.EntityManagerFactoryProvider;
import se.tada.tutorial.jpa.EntityManagerProvider;

/**
 * Jersey Specific resource config
 */
@ApplicationPath("/api")
public class RestApp extends ResourceConfig {
	private static final Logger log = Logger.getLogger(RestApp.class.getName());

	private final List<Object> injectNeeded = new ArrayList<>();

	private final String[] persistenceUnits = { "tutorial" };

	public RestApp() {
		register(new ContainerLifecycleListener() {
			@Override
			public void onStartup(Container container) {
				// Since we did the binding via instances that we created, we need to ensure
				// that their members are injected properly
				ServiceLocator locator = container.getApplicationHandler().getServiceLocator();
				for(Object o : injectNeeded)
					locator.inject(o);
				injectNeeded.clear();
			}

			@Override
			public void onReload(Container container) {
			}

			@Override
			public void onShutdown(Container container) {
				// Shut down embedded derby, if present
				try (Connection cn = DriverManager.getConnection("jdbc:derby:;shutdown=true")) {
					log.log(Level.WARNING, "Derby shutdown failed (no exception occurred).");
				}
				catch(SQLException e) {
					if("XJ015".equals(e.getSQLState()))
						log.log(Level.INFO, "Derby shutdown succeeded.");
				}
			}
		});

		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind("tutorial").to(String.class).named("default.persistence.unit");
				for(String pu : persistenceUnits) {
					EntityManagerFactoryProvider emFactoryProvider = new EntityManagerFactoryProvider(pu);
					injectNeeded.add(emFactoryProvider);
					bindFactory(emFactoryProvider).to(EntityManagerFactory.class).named(pu).in(Singleton.class);

					EntityManagerProvider emProvider = new EntityManagerProvider(pu);
					injectNeeded.add(emProvider);
					bindFactory(emProvider).to(EntityManager.class).named(pu).in(RequestScoped.class);
				}
			}
		});
		packages("se.tada");

		// Log all requests.
		registerInstances(new LoggingFilter(Logger.getLogger(RestApp.class.getName()), true));
	}
}
