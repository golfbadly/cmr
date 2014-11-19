package com.xplink.random_cm.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.log4j.Logger;

public class PostFBWallService {

	private static final Logger logger = Logger
			.getLogger(EventAcceptService.class);

	public boolean post(String dataPost) throws MalformedURLException,
			IOException {

		logger.debug("[In PostFBWallService]");
		String feedUrl = "https://graph.facebook.com/me/feed";

		// Send data
		URL urlAddress = new URL(feedUrl);
		java.net.URLConnection connection = urlAddress.openConnection();

		try {
			connection.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
			wr.write(dataPost);
			wr.flush();
			// Get the response
			logger.debug("BufferedReader");
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				// Process line...
				logger.debug(line);
			}
			wr.close();
			rd.close();
		} catch (Exception e) {
			logger.debug("[post Exception]" + e.getMessage());
			e.getStackTrace();
			return false;
		}
		logger.debug("[Out PostFBWallService]");

		return true;

	}

}
