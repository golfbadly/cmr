package com.xplink.random_cm.managements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import com.xplink.random_cm.datamodel.MemberBean;

public class ListManagement {
	ResultSet rs;
	private DataSource dataSource;
	
	public MemberBean getlist() {
		MemberBean mb = new MemberBean();
		
		try {
			Connection con = dataSource.getConnection();
			Statement stmt = con.createStatement();
			String SQL = "select * from cmr_member";

			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				mb.setMemberid(rs.getInt(1));
				mb.setName(rs.getString(2));
				mb.setSurname(rs.getString(3));
				mb.setEmail(rs.getString(4));
				mb.setUsername(rs.getString(5));
				mb.setPassword(rs.getString(6));
				mb.setStatus(rs.getString(7));
				
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (Exception ex) {
			System.out.println("ProfileManager>>getlist()>>>"
					+ ex.getMessage());
			return null;
		}
		return mb;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	
}
