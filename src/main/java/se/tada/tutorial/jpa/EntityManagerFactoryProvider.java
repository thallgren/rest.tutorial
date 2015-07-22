package se.tada.tutorial.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.glassfish.hk2.api.Factory;

/**
 * Class that provides an EntityManagerFactory for a given persistence unit
 */
public class EntityManagerFactoryProvider implements Factory<EntityManagerFactory> {
	private final String persistenceUnit;

	/**
	 * Create a new provider for the EntityManagerFactory of the given persistenceUnit
	 *
	 * @param persistenceUnit
	 *            The name of the persistence unit (as listed in persistence.xml)
	 */
	public EntityManagerFactoryProvider(String persistenceUnit) {
		this.persistenceUnit = persistenceUnit;
	}

	@Override
	public void dispose(EntityManagerFactory instance) {
		instance.close();
	}

	/**
	 * Produce the EntityManagerFactory for the &quot;tutorial&quot; persistence unit.
	 * This will initialize the JPA when first called.
	 *
	 * @return The entity manager for &quot;tutorial&quot;
	 */
	@Override
	public EntityManagerFactory provide() {
		return Persistence.createEntityManagerFactory(persistenceUnit);
	}
}
