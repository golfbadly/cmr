package com.xplink.random_cm.service;

import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

public class FTPConnection {
	private static final Logger logger = Logger.getLogger(FTPConnection.class);
	private String host;
	private String user;
	private String password;

	public FTPClient connectFTP() {
		FTPClient ftp = new FTPClient();
		try {

			ftp.connect(host);
			ftp.login(user, password);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			int reply = ftp.getReplyCode();

			if (FTPReply.isPositiveCompletion(reply)) {
				logger.debug("Connected Success");
			} else {
				logger.debug("Connection Failed");
				ftp.disconnect();
			}
		} catch (Exception e) {
			logger.error("Connection Failed  " + e.getMessage());
		}
		return ftp;
	}


	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
