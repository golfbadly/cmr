package com.xplink.random_cm.managements;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import com.xplink.random_cm.datamodel.MemberBean;

public class RegisterManager {
	private DataSource dataSource;
	
	public boolean register(MemberBean mb) throws SQLException {
		boolean result = false;

		try {
			
			Connection con = dataSource.getConnection();
			Statement stmt = con.createStatement();
			stmt = con.createStatement();

			String SQL = "INSERT INTO cmr_member VALUES ('','"
					+ mb.getName() + "','"+ mb.getSurname() + "','" + mb.getEmail() + "','"
					+ mb.getUsername() + "','" + mb.getPassword() + "','"+mb.getStatus()+"')";

			int row = stmt.executeUpdate(SQL);
			if (row > 0) {
				result = true;
			}
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
