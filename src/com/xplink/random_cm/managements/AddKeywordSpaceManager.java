package com.xplink.random_cm.managements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.sql.DataSource;

public class AddKeywordSpaceManager {
	ResultSet rs;
	private DataSource dataSource;
	
	public boolean addSpace(int memberid) throws SQLException {
		boolean result = false;
         
		try {
			
			Connection con = dataSource.getConnection();
			Statement stmt = con.createStatement();
			stmt = con.createStatement();
			
			Calendar toDay = Calendar.getInstance();
			int year = toDay.get(Calendar.YEAR);	

			String SQL = "INSERT INTO cmr_keyword VALUES ('','','',"+year+","+memberid+")";

			int row = stmt.executeUpdate(SQL);
			if (row > 0) {
				result = true;
			}

			//rs.close();
			stmt.close();
			con.close();
		} catch (Exception ex) {

			ex.printStackTrace();
		} 

		return result;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	
	
}
