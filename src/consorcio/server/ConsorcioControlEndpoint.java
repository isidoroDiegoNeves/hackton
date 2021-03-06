package consorcio.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
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
		ConsorcioControl consorciocontrol = new ConsorcioControl();
		consorciocontrol.setEmail("Sucesso");
		
		try {
			consorciocontrol = this.recuperaMaiorLance();
		} catch (Exception e) {
			e.printStackTrace();
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

		URL url = new URL("https://us16.api.mailchimp.com/3.0/campaigns/b3e44bbb9f/actions/test");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setConnectTimeout(5000);
		conn.setReadTimeout(5000);
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
	
	// HTTP POST request
	private ConsorcioControl recuperaMaiorLance() throws Exception {

		URL url = new URL("https://1-dot-hackton-consorcio.appspot.com/_ah/api/consorcioparticipantebeanendpoint/v1/consorcioparticipantebean");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		conn.setConnectTimeout(5000);
		conn.setReadTimeout(5000);
		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		JSONObject items = new JSONObject(response.toString());
		JSONArray users = items.getJSONArray("items");
		JSONObject vencedor = new JSONObject();
		int valorAnterior = 0;
		for (int i = 0; i < users.length(); i++) {
			JSONObject objects = users.getJSONObject(i);
			if (objects.has("ds_value")) {
				if (objects.getInt("ds_value") > valorAnterior) {
					vencedor = objects;
				}
				valorAnterior = objects.getInt("ds_value");
			}
			System.out.println(objects);
		}
		ConsorcioControl consorcio = new ConsorcioControl();
		if(vencedor.has("cod_participante")){
			JSONObject email = this.getEmail(vencedor);
			consorcio.setEmail("Parabens! "+email.getString("ds_name")+" voce foi sorteado, e um email com uma descricao do sorteio foi enviado para "+email.getString("email")+".");
			this.sendPost(email.getString("email"));
		}

		return consorcio;

	}
	
	private JSONObject getEmail(JSONObject vencedor) throws IOException, JSONException{
		
		URL url = new URL("https://1-dot-hackton-consorcio.appspot.com/_ah/api/participantebeanendpoint/v1/participantebean/"+vencedor.getString("cod_participante"));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		conn.setConnectTimeout(5000);
		conn.setReadTimeout(5000);
		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		return new JSONObject(response.toString());
	}

	private String getBasicAuthenticationEncoding() {
		String userPassword = "EES2017" + ":" + "3af8d9e3486a28a95da071eb52aac7c0-us16";
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
