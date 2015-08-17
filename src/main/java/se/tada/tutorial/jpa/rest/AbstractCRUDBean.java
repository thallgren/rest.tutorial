package se.tada.tutorial.jpa.rest;

import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import se.tada.tutorial.jpa.model.EntityWithId;

public abstract class AbstractCRUDBean<T extends EntityWithId> extends AbstractJPABean {
	private final Class<T> entityClass;

	private final String endPoint;

	protected AbstractCRUDBean(Class<T> entityClass, String endPoint) {
		this.entityClass = entityClass;
		this.endPoint = endPoint;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<T> getTracks() {
		return em.createQuery("SELECT t FROM " + endPoint + " t", entityClass).getResultList();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public T getEntity(@PathParam("id") Long id) {
		return em.find(entityClass, id);
	}

	@DELETE
	@Path("/{id}")
	public void deleteEntity(@PathParam("id") Long id) {
		transaction(em -> {
			em.remove(getEntity(id));
			return null;
		});
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEntity(T newEntity) {
		T created = transaction(em -> {
			T entity;
			try {
				entity = entityClass.newInstance();
			}
			catch(Exception e) {
				throw new WebApplicationException(e, Status.INTERNAL_SERVER_ERROR);
			}
			copy(newEntity, entity);
			em.persist(entity);
			return entity;
		});
		return Response.created(URI.create("tracks/" + created.getId())).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateTrack(@PathParam("id") Long id, T updatedEntity) {
		transaction(em -> {
			T entity = getEntity(id);
			copy(updatedEntity, entity);
			em.merge(entity);
			return null;
		});
	}

	protected abstract void copy(T source, T dest);
}
