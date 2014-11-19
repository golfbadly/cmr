package com.xplink.random_cm.managements;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.log4j.Logger;


public class UpdateKeywordManager {
boolean result = false ;
private DataSource dataSource;


private static final Logger logger = Logger.getLogger(UpdateKeywordManager.class);

	public boolean UpdateKW(int eventDetailId,String kw){
	
		  logger.debug("[IN updateKW]");
		
		try {
	
			Connection con = dataSource.getConnection();
			Statement stmt = con.createStatement();
			stmt = con.createStatement();
	
			stmt = con.createStatement();
			String sqlUpdate = "UPDATE cmr_keyword SET OUTKEYWORD='"+kw+"'"+
			"WHERE cmr_keyword.EVENT_DETAIL_ID="+eventDetailId;	
			
			int row = stmt.executeUpdate(sqlUpdate);
			if (row > 0) {
				result = true;
			}
			stmt.close();
			con.close();
	
		} catch (Exception ex) {
			 logger.debug("[Exception :]"+ex.getMessage());
		}
		
		  logger.debug("[OUT updateKW]");
		return result;
	}
	
	public boolean updateKeywordAfterRandom(int eventDetailId,String kw){
		
		  logger.debug("[IN updateKeywordAfterRandom]");
		
		try {
			Connection con = dataSource.getConnection();
			Statement stmt = con.createStatement();
			stmt = con.createStatement();
	
			stmt = con.createStatement();
			//String sqlUpdate = "UPDATE cmr_keyword SET OUTKEYWORD='"+kw+"' WHERE EVENT_DETAIL_ID="+id;
			String sqlUpdate = "UPDATE cmr_keyword SET OUTKEYWORD='"+kw+"'"+
								"WHERE cmr_keyword.EVENT_DETAIL_ID="+eventDetailId;
						
			int row = stmt.executeUpdate(sqlUpdate);
			if (row > 0) {
				result = true;
			}
			stmt.close();
			con.close();
	
		} catch (Exception ex) {
	        logger.debug("[Exception :]"+ex.getMessage());
		}
		
		 logger.debug("[OUT updateKeywordAfterRandom]");
		
		return result;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
}
