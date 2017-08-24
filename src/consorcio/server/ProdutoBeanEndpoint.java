package consorcio.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.HttpPost;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import consorcio.EMF;

@Api(name = "produtobeanendpoint", namespace = @ApiNamespace(ownerDomain = "server.consorcio", ownerName = "server.consorcio", packagePath = ""))
public class ProdutoBeanEndpoint {
	

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listProdutoBean")
	public CollectionResponse<ProdutoBean> listProdutoBean(@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<ProdutoBean> execute = null;
		
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from ProdutoBean as ProdutoBean");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<ProdutoBean>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (ProdutoBean obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<ProdutoBean>builder().setItems(execute).setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getProdutoBean")
	public ProdutoBean getProdutoBean(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		ProdutoBean produtobean = null;
		try {
			produtobean = mgr.find(ProdutoBean.class, id);
		} finally {
			mgr.close();
		}
		return produtobean;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param produtobean the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertProdutoBean")
	public ProdutoBean insertProdutoBean(ProdutoBean produtobean) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsProdutoBean(produtobean)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(produtobean);
		} finally {
			mgr.close();
		}
		return produtobean;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param produtobean the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateProdutoBean")
	public ProdutoBean updateProdutoBean(ProdutoBean produtobean) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsProdutoBean(produtobean)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(produtobean);
		} finally {
			mgr.close();
		}
		return produtobean;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeProdutoBean")
	public void removeProdutoBean(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			ProdutoBean produtobean = mgr.find(ProdutoBean.class, id);
			mgr.remove(produtobean);
		} finally {
			mgr.close();
		}
	}

	private boolean containsProdutoBean(ProdutoBean produtobean) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			ProdutoBean item = mgr.find(ProdutoBean.class, produtobean.getCod());
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
