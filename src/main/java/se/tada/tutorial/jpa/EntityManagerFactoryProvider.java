package se.tada.tutorial.jpa;

import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.glassfish.hk2.api.Factory;

/**
 * Class that provides an EntityManagerFactory for a given persistence unit
 */
@Singleton
public class EntityManagerFactoryProvider implements Factory<EntityManagerFactory> {
	private final String persistenceUnit;

	public EntityManagerFactoryProvider(String persistenceUnit) {
		this.persistenceUnit = persistenceUnit;
	}

	@Override
	public void dispose(EntityManagerFactory instance) {
		instance.close();
	}

	/**
	 * Produce the EntityManagerFactory for the persistence unit that this factory
	 * represents.
	 * This will initialize the JPA when first called.
	 *
	 * @return The entity manager for &quot;tutorial&quot;
	 */
	@Override
	public EntityManagerFactory provide() {
		return Persistence.createEntityManagerFactory(persistenceUnit);
	}
}
