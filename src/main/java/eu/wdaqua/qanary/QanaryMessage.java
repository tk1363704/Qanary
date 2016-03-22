package eu.wdaqua.qanary;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;

public class QanaryMessage extends HashMap<URL, URL> {

	/**
	 * The serialization runtime associates with each serializable class a
	 * version number, called a serialVersionUID, which is used during
	 * deserialization to verify that the sender and receiver of a serialized
	 * object have loaded classes for that object that are compatible with
	 * respect to serialization
	 */
	private static final long serialVersionUID = 42L;

	private static final Logger logger = LoggerFactory.getLogger(QanaryMessage.class);

	// the property URI (key) for accessing the endpoint
	public static final String endpointKey = "http://qanary/#endpoint";
	// the property URI (key) for accessing the input data at the endpoint
	public static final String inGraph = "http://qanary/#endpoint";
	// the property URI (key) for inserting the output into the endpoint
	public static final String outGraph = "http://qanary/#endpoint";

	/**
	 * default constructor needed for post communication
	 */
	public QanaryMessage() {
	}

	public QanaryMessage(String jsonString) throws MalformedURLException {
		logger.info("construct QanaryMessage: {}", jsonString);
		JSONObject json = JSONObject.fromObject(jsonString);

		URL endpointKeyURL = new URL(endpointKey);

		// just for debugging
		URL endpointvalue = new URL((String) json.get(endpointKeyURL.toString()));
		logger.info("construct endpoint: {}={}", endpointKeyURL, endpointvalue);

		// assign value to internal map
		this.put(endpointKeyURL, new URL((String) json.get(endpointKeyURL.toString())));
		logger.info("construct put endpoint value: {}", new URL((String) json.get(endpointKeyURL.toString())));

		// TODO: add input graph
		// TODO: add output graph
	}

	public String asJsonString() {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(this);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
