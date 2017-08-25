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
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import consorcio.EMF;

@Api(name = "consorciocontrolendpoint", namespace = @ApiNamespace(ownerDomain = "server.consorcio", ownerName = "server.consorcio", packagePath = ""))
public class ConsorcioControlEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listConsorcioControl")
	public CollectionResponse<ConsorcioControl> listConsorcioControl(@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<ConsorcioControl> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from ConsorcioControl as ConsorcioControl");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<ConsorcioControl>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (ConsorcioControl obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<ConsorcioControl>builder().setItems(execute).setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getConsorcioControl")
	public ConsorcioControl getConsorcioControl(@Named("id") String id) {
		EntityManager mgr = getEntityManager();
		ConsorcioControl consorciocontrol = null;
		try {
			consorciocontrol = mgr.find(ConsorcioControl.class, id);
		} finally {
			mgr.close();
		}
		return consorciocontrol;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param consorciocontrol the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertConsorcioControl")
	public ConsorcioControl insertConsorcioControl(ConsorcioControl consorciocontrol) {
		try {
			this.sendPost(consorciocontrol.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
		consorciocontrol.setEmail("Sucesso");

		return consorciocontrol;
	}


	// HTTP POST request
	private void sendPost(String email) throws Exception {

		URL url = new URL("https://us16.api.mailchimp.com/3.0/campaigns/b608683e4c/actions/test");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestProperty("Authorization", "Basic " + getBasicAuthenticationEncoding());
		// add reuqest header
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		JSONObject sendEmail = new JSONObject();
		List<String> emails = new ArrayList<String>();
		emails.add(email);
		sendEmail.put("test_emails", emails);
		sendEmail.put("send_type", "html");

		// Send post request
		conn.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(sendEmail.toString());
		wr.flush();
		wr.close();

		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + sendEmail.toString());
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}

	private String getBasicAuthenticationEncoding() {
		String userPassword = "isidoroDiegoNeves@gmail.com" + ":" + "854eeca59871686ac9fd902b151ab4ab-us16";
		byte[] result = userPassword.getBytes();
		result = Base64.encodeBase64(result);
		return new String(result);
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param consorciocontrol the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateConsorcioControl")
	public ConsorcioControl updateConsorcioControl(ConsorcioControl consorciocontrol) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsConsorcioControl(consorciocontrol)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(consorciocontrol);
		} finally {
			mgr.close();
		}
		return consorciocontrol;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeConsorcioControl")
	public void removeConsorcioControl(@Named("id") String id) {
		EntityManager mgr = getEntityManager();
		try {
			ConsorcioControl consorciocontrol = mgr.find(ConsorcioControl.class, id);
			mgr.remove(consorciocontrol);
		} finally {
			mgr.close();
		}
	}
	

	private boolean containsConsorcioControl(ConsorcioControl consorciocontrol) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			ConsorcioControl item = mgr.find(ConsorcioControl.class, consorciocontrol.getEmail());
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
