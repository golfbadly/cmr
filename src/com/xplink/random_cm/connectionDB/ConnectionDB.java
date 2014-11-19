package com.xplink.random_cm.connectionDB;

import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.mysql.jdbc.Connection;

public class ConnectionDB {
	
	private static final Logger logger = LogManager.getLogger(ConnectionDB.class);

	Connection conn = null;

	private String driver;

	private String username;

	private String password;

	private String url;

	public ConnectionDB() {
	}

	public ConnectionDB(String driver, String username, String password, String url) {
		this.driver = driver;
		this.username = username;
		this.password = password;
		this.url = url;
	}

	public Connection connectDB() throws Exception {

		try {
			Class.forName(driver);
		}
		catch (ClassNotFoundException e) {
			logger.error("Error:" + e.getMessage(),e);
			
			throw e;
		}

		conn = (Connection) DriverManager.getConnection(url, username, password);

		return conn;
	}

	public void closeConnection() throws Exception {
		try {
			conn.close();
		}
		catch (SQLException ex) {
			logger.error("Error:" + ex.getMessage());
			
			throw ex;
		}
	}
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}

