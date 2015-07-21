package se.tada.tutorial.cdi;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Class that provides request scoped CDI bindings
 */
@ManagedBean
@RequestScoped
public class RequestScopedCDIBinder {
	@Inject
	private EntityManagerFactory emFactory;

	@Produces
	public EntityManager createEntityManager() {
		return emFactory.createEntityManager();
	}

	public void closeEM(@Disposes EntityManager em) {
		em.close();
	}
}
