package com.telstra.codechallenge.util;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RestApiHandler {

	private static Gson gson;

	/**
	 * This method will make a REST GET call for URL
	 * 
	 * Then parse response using GSON & return parsed Map.
	 */
	public static Map<?, ?> makeRESTApiCall(String restUrl, String acceptHeaderValue)
			throws ClientProtocolException, IOException {
		Request request = Request.Get(restUrl);

		if (acceptHeaderValue != null && !acceptHeaderValue.isBlank()) {
			request.addHeader("Accept", acceptHeaderValue);
		}

		Content content = request.execute().returnContent();
		String jsonString = content.asString();
		gson = new GsonBuilder().setPrettyPrinting().create();
		Map<?, ?> jsonMap = gson.fromJson(jsonString, Map.class);
		return jsonMap;
	}

}
