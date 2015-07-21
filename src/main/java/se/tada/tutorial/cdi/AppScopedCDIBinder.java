package se.tada.tutorial.cdi;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Class that provides application scoped CDI bindings
 */
@ManagedBean
@ApplicationScoped
public class AppScopedCDIBinder {
	/**
	 * Produce the EntityManagerFactory for the &quot;tutorial&quot; persistence unit.
	 * This will initialize the JPA when first called.
	 *
	 * @return The entity manager for &quot;tutorial&quot;
	 */
	@Produces
	public EntityManagerFactory createEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("tutorial");
	}
}
