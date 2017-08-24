package consorcio.server;

import consorcio.EMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "consorciobeanendpoint", namespace = @ApiNamespace(ownerDomain = "server.consorcio", ownerName = "server.consorcio", packagePath = ""))
public class ConsorcioBeanEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listConsorcioBean")
	public CollectionResponse<ConsorcioBean> listConsorcioBean(@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<ConsorcioBean> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from ConsorcioBean as ConsorcioBean");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<ConsorcioBean>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (ConsorcioBean obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<ConsorcioBean>builder().setItems(execute).setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getConsorcioBean")
	public ConsorcioBean getConsorcioBean(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		ConsorcioBean consorciobean = null;
		try {
			consorciobean = mgr.find(ConsorcioBean.class, id);
		} finally {
			mgr.close();
		}
		return consorciobean;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param consorciobean the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertConsorcioBean")
	public ConsorcioBean insertConsorcioBean(ConsorcioBean consorciobean) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsConsorcioBean(consorciobean)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(consorciobean);
		} finally {
			mgr.close();
		}
		return consorciobean;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param consorciobean the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateConsorcioBean")
	public ConsorcioBean updateConsorcioBean(ConsorcioBean consorciobean) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsConsorcioBean(consorciobean)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(consorciobean);
		} finally {
			mgr.close();
		}
		return consorciobean;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeConsorcioBean")
	public void removeConsorcioBean(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			ConsorcioBean consorciobean = mgr.find(ConsorcioBean.class, id);
			mgr.remove(consorciobean);
		} finally {
			mgr.close();
		}
	}

	private boolean containsConsorcioBean(ConsorcioBean consorciobean) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			ConsorcioBean item = mgr.find(ConsorcioBean.class, consorciobean.getCod());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
