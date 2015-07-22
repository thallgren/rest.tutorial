package se.tada.tutorial.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.glassfish.hk2.api.Factory;

/**
 * Class that provides request scoped CDI bindings
 */
public class EntityManagerProvider implements Factory<EntityManager> {
	@Inject
	private EntityManagerFactory emFactory;

	@Override
	public EntityManager provide() {
		return emFactory.createEntityManager();
	}

	@Override
	public void dispose(EntityManager instance) {
		instance.close();
	}
}
