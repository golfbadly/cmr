package com.xplink.random_cm.managements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.xplink.random_cm.datamodel.KeywordBean;

public class GetMissionManager {
	private DataSource dataSource;
	ResultSet rs;
	private static final Logger logger = LogManager.getLogger(GetMissionManager.class);

	public KeywordBean getKeyword(int memberid ,int eventId) throws SQLException {
		
		logger.debug("[IN GetMissionManager]");
		
		Connection con = dataSource.getConnection();
		Statement stmt = con.createStatement();
	
		KeywordBean keyword = new  KeywordBean();
		try {

			String sql = "SELECT k.EVENT_DETAIL_ID,OUTKEYWORD FROM cmr_event_detail ed, cmr_keyword k "+
							"WHERE ed.EVENT_DETAIL_ID = k.EVENT_DETAIL_ID "+"AND ed.INVITE_USER_ID ="+memberid+
							" AND ed.EVENT_ID ="+eventId;
			
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
					
				keyword.setEventDetailId((rs.getInt("k.EVENT_DETAIL_ID")));
				keyword.setOutkeyword(rs.getString("OUTKEYWORD"));
				
				logger.debug("[keyword.EventDetailID :]"+keyword.getEventDetailId());
				logger.debug("[keyword.Outkeyword :]"+keyword.getOutkeyword());
				
			}
			logger.debug("GetMissionManager: "+keyword);
			logger.debug("[OUT GetMissionManager]");

			return keyword;
			
		} catch (Exception ex) {
			logger.debug("[Exception :]"+ex.getMessage());
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
