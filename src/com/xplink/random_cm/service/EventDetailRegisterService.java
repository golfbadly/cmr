package com.xplink.random_cm.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class EventDetailRegisterService {

	private DataSource dataSource;
	Connection con ;
	
private static final Logger logger = Logger.getLogger(EventDetailRegisterService.class);
		
	public boolean addEventDetail(String dataEventId,int memberId) throws SQLException{
		 logger.debug("[IN :EvenDetailRegisterService]");
		 
		 con = dataSource.getConnection();
		 Statement stmtAddEventDetail= con.createStatement();
		 
		 boolean result = false;
		 
		 try {
				String sqlEventDetail = "INSERT INTO cmr_event_detail VALUES ('',"+dataEventId+","+memberId+")";

				int row_event = stmtAddEventDetail.executeUpdate(sqlEventDetail);
				if (row_event > 0) {
					result = true;
				}
				
		 }catch (Exception ex) {
					ex.printStackTrace();
		 }finally{
					if(stmtAddEventDetail!= null)
						try{stmtAddEventDetail.close();}catch(Exception ex){
							ex.getStackTrace();
						}
						//close connection
						if(stmtAddEventDetail != null)
							try{stmtAddEventDetail.close();}catch(Exception ex){}
						if(con != null)
							try{con.close();}catch(Exception ex){}
				}
				 logger.debug("[OUT:RegisterService method register]");
				return result;					
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	

}


