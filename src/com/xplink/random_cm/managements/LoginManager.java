package com.xplink.random_cm.managements;

import java.sql.*;

import javax.sql.DataSource;

public class LoginManager {
		private DataSource dataSource;
		ResultSet rs;
public boolean validate(String u, String p) throws SQLException{
	
		Connection con = dataSource.getConnection();
		Statement stmt = con.createStatement();
	
		try{
			
			String sql = "SELECT USERNAME,PASSWORD FROM cmr_member";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				if((rs.getString("USERNAME").equals(u)) && (rs.getString("PASSWORD").equals(p))){
					return true;
				}
			}
			
		}catch(Exception ex){
			
			ex.printStackTrace();
		}
		finally{
			if(rs != null)
				try{rs.close();}catch(Exception ex){}
			if(stmt != null)
				try{stmt.close();}catch(Exception ex){}
			if(con != null)
				try{con.close();}catch(Exception ex){}
		}
		
		return false;
	}

public DataSource getDataSource() {
	return dataSource;
}
public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
}
	

}
