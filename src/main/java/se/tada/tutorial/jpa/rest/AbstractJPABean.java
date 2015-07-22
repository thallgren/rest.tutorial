package se.tada.tutorial.jpa.rest;

import java.util.function.Function;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public abstract class AbstractJPABean {
	@Inject
	protected EntityManager em;

	public <T> T transaction(Function<EntityManager, T> ts) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			T result = ts.apply(em);
			transaction.commit();
			return result;
		}
		catch(Exception e) {
			if(transaction.isActive())
				transaction.rollback();
			throw e;
		}
	}
}
