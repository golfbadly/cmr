package com.xplink.random_cm.managements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class UpdateMemberProfileManager {
	private DataSource dataSource;
	ResultSet rs;

	  private static final Logger logger = LogManager.getLogger( UpdateMemberProfileManager.class);
	
	public boolean updateMemberProfile(int id,String name,String surname,String email){
		boolean result = false;
		
		logger.debug("[IN UpdateMemberProfileManager]");
		logger.debug("[name :]"+name);
		logger.debug("[surname :]"+surname);
		logger.debug("[email :]"+email);

		try {
		
			Connection con = dataSource.getConnection();
			Statement stmt = con.createStatement();
			stmt = con.createStatement();

			/*String sql = "UPDATE cmr_member SET NAME='"+name+"'," +
					                                       "SURNAME='"+surname+"'," +
					                                       "EMAIL='"+email+"'," +
					                                       "USERNAME='"+username+"'," +
					                                       "PASSWORD='"+password+"'" +
					                                      " WHERE MEMBER_ID="+id;
			*/
			String sql = "UPDATE cmr_member SET NAME='"+name+"'," +
            "SURNAME='"+surname+"'," +
            "EMAIL='"+email+"'" +
            " WHERE MEMBER_ID="+id;

			int row = stmt.executeUpdate(sql);
			if (row > 0) {
				result = true;
			}

//			rs.close();
			stmt.close();
			con.close();
		} catch (Exception ex) {
			
			logger.debug("[Exception :]"+ex.getMessage());

			ex.printStackTrace();
		} 
		
		logger.debug("[OUT UpdateMemberProfileManager]");

		return result;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
}
