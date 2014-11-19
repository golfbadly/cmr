package com.xplink.random_cm.managements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import com.xplink.random_cm.datamodel.MemberBean;

public class GetMemberDetailManager {
	ResultSet rs;
	private DataSource dataSource;
	
	public MemberBean getMember(String username) throws SQLException {

		Connection con = dataSource.getConnection();
		Statement stmt = con.createStatement();
		MemberBean memberbean = new MemberBean();
		try {

			String sql = "SELECT * FROM cmr_member WHERE USERNAME = '"+username+"'";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				memberbean = new MemberBean();
				memberbean.setMemberid(rs.getInt("MEMBER_ID"));
				memberbean.setName(rs.getString("NAME"));
				memberbean.setSurname(rs.getString("SURNAME"));
				memberbean.setEmail(rs.getString("EMAIL"));
				memberbean.setUsername(rs.getString("USERNAME"));
				memberbean.setPassword(rs.getString("PASSWORD"));
				
			}

			return memberbean;
		} catch (Exception ex) {

			ex.printStackTrace();
			return null;
		}finally{
			if(rs != null)
				try{rs.close();}catch(Exception ex){}
			if(stmt != null)
				try{stmt.close();}catch(Exception ex){}
			if(con != null)
				try{con.close();}catch(Exception ex){}
		}
	}
	
	public MemberBean getMemberByID(int id) throws SQLException {
		
		Connection con = dataSource.getConnection();
		Statement stmt = con.createStatement();
		MemberBean memberbean = new MemberBean();
		try {

			String sql = "SELECT * FROM cmr_member WHERE MEMBER_ID ="+id;
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				memberbean = new MemberBean();
				memberbean.setMemberid(rs.getInt("MEMBER_ID"));
				memberbean.setName(rs.getString("NAME"));
				memberbean.setSurname(rs.getString("SURNAME"));
				memberbean.setEmail(rs.getString("EMAIL"));
				memberbean.setUsername(rs.getString("USERNAME"));
				memberbean.setPassword(rs.getString("PASSWORD"));
				
			}

			return memberbean;
		} catch (Exception ex) {

			ex.printStackTrace();
			return null;
		}finally{
			if(rs != null)
				try{rs.close();}catch(Exception ex){}
			if(stmt != null)
				try{stmt.close();}catch(Exception ex){}
			if(con != null)
				try{con.close();}catch(Exception ex){}
		}
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
}
