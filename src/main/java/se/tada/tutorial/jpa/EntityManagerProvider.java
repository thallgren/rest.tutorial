package se.tada.tutorial.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.api.ServiceLocator;

/**
 * Class that provides request scoped CDI bindings
 */
public class EntityManagerProvider implements Factory<EntityManager> {
	private final String persistenceUnit;

	private ServiceHandle<EntityManagerFactory> emFactoryHandle;

	public EntityManagerProvider(String persistenceUnit) {
		this.persistenceUnit = persistenceUnit;
	}

	@Inject
	public void injectEntityManagerFactory(ServiceLocator serviceLocator) {
		emFactoryHandle = serviceLocator.getServiceHandle(EntityManagerFactory.class, persistenceUnit);
	}

	@Override
	public EntityManager provide() {
		return emFactoryHandle.getService().createEntityManager();
	}

	@Override
	public void dispose(EntityManager instance) {
		instance.close();
	}
}
