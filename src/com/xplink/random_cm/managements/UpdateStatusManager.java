package com.xplink.random_cm.managements;

import java.sql.Connection;
import java.sql.Statement;
import javax.sql.DataSource;


public class UpdateStatusManager {
	private DataSource dataSource;
	boolean result = false ;
	
	public boolean UpdateStatus(int memberid){
	
		try {
		
			Connection con = dataSource.getConnection();
			Statement stmt = con.createStatement();
			stmt = con.createStatement();
	
			stmt = con.createStatement();
			String sqlUpdate = "UPDATE cmr_member SET STATUS='1' WHERE 	MEMBER_ID="+memberid;
					
	
			int row = stmt.executeUpdate(sqlUpdate);
			if (row > 0) {
				result = true;
			}
			stmt.close();
			con.close();
	
		} catch (Exception ex) {
			System.out.println("ProfileManager>>editProfile()"
					+ ex.getMessage());
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
