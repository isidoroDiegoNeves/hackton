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

@Api(name = "participantebeanendpoint", namespace = @ApiNamespace(ownerDomain = "server.consorcio", ownerName = "server.consorcio", packagePath = ""))
public class ParticipanteBeanEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listParticipanteBean")
	public CollectionResponse<ParticipanteBean> listParticipanteBean(@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<ParticipanteBean> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from ParticipanteBean as ParticipanteBean");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<ParticipanteBean>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (ParticipanteBean obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<ParticipanteBean>builder().setItems(execute).setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getParticipanteBean")
	public ParticipanteBean getParticipanteBean(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		ParticipanteBean participantebean = null;
		try {
			participantebean = mgr.find(ParticipanteBean.class, id);
		} finally {
			mgr.close();
		}
		return participantebean;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param participantebean the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertParticipanteBean")
	public ParticipanteBean insertParticipanteBean(ParticipanteBean participantebean) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsParticipanteBean(participantebean)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(participantebean);
		} finally {
			mgr.close();
		}
		return participantebean;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param participantebean the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateParticipanteBean")
	public ParticipanteBean updateParticipanteBean(ParticipanteBean participantebean) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsParticipanteBean(participantebean)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(participantebean);
		} finally {
			mgr.close();
		}
		return participantebean;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeParticipanteBean")
	public void removeParticipanteBean(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			ParticipanteBean participantebean = mgr.find(ParticipanteBean.class, id);
			mgr.remove(participantebean);
		} finally {
			mgr.close();
		}
	}

	private boolean containsParticipanteBean(ParticipanteBean participantebean) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			ParticipanteBean item = mgr.find(ParticipanteBean.class, participantebean.getCod());
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
