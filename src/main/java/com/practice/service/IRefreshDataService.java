package com.practice.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface IRefreshDataService {
	static final Logger LOGGER = LoggerFactory.getLogger(IRefreshDataService.class);

	/**
	 * Returns a String Optional if response is ok or else empty optional
	 * {@code URL}.
	 *
	 * <P>
	 * This method is used to calling get method url.
	 * </P>
	 */
	default Optional<String> sendGet(String urlEndpoint) {
		try {
			URL url = new URL(urlEndpoint);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.setRequestMethod("GET");
			if (request.getResponseCode() != HttpURLConnection.HTTP_OK) {
				LOGGER.error("Response status: " + request.getResponseCode() + " for url: " + urlEndpoint);
			}

			StringBuilder response = new StringBuilder();
			try (BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
				String output;
				while ((output = in.readLine()) != null) {
					response.append(output);
				}
			}
			LOGGER.info("Response status: " + request.getResponseCode() + " for url: " + urlEndpoint
					+ " for GET request to endpoint " + urlEndpoint);
			return Optional.of(response.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			return Optional.empty();
		}
	}

	void refresh(String from, String to);
}
