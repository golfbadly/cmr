package com.xplink.random_cm.managements;

import java.sql.*;

import javax.sql.DataSource;


public class InputKeywordManager {
	private DataSource dataSource;

	public boolean inputKeyword(int eventDetailId,String keyword) {
		boolean result = false;

		try {

			Statement stmt;
			Connection con = dataSource.getConnection();
			stmt = con.createStatement();

			String SQL = "UPDATE cmr_keyword SET INKEYWORD='"+keyword+"' WHERE EVENT_DETAIL_ID="+eventDetailId;

			int row = stmt.executeUpdate(SQL);
			if (row > 0) {
				result = true;
			}

			stmt.close();
//			con.commit();
			con.close();

		} catch (SQLException ex) {
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
