package com.xplink.random_cm.managements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.xplink.random_cm.datamodel.KeywordBean;

public class GetListKeywordManager {

	private DataSource dataSource;
	Connection con;
	Statement stmt;
	ResultSet rs;
	
	private static final Logger logger = LogManager.getLogger(GetListKeywordManager.class);
	
	public ArrayList<KeywordBean> getListKeyword(int eventId){
		
		logger.debug("[IN getListKeyword ]");		
		logger.debug("[eventId :]"+eventId);
		
		ArrayList<KeywordBean> list = new ArrayList<KeywordBean>();
		
		try {
		
			con = dataSource.getConnection();
			stmt = con.createStatement();
			KeywordBean kb;
			
			String sql = "SELECT * FROM cmr_event_detail ed, cmr_keyword k "+
			             " WHERE ed.EVENT_DETAIL_ID = k.EVENT_DETAIL_ID AND ed.EVENT_ID ="+eventId;
			
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				logger.debug("[result set next... ]");
				
				kb = new KeywordBean();
				
				kb.setEventDetailId(rs.getInt("EVENT_DETAIL_ID"));
				kb.setYear(rs.getString("YEAR"));
				kb.setInkeyword(rs.getString("INKEYWORD"));
				kb.setOutkeyword(rs.getString("OUTKEYWORD"));
				
				list.add(kb);
			}
			logger.debug("[OUT getListKeyword ]");		
			return list;
			
		} catch (Exception ex) {
			
			logger.debug("[Exception :]"+ex.getMessage());		

			ex.printStackTrace();

		}finally{
			if(rs != null)
				try{rs.close();}catch(Exception ex){}
			if(stmt != null)
				try{stmt.close();}catch(Exception ex){}
			if(con != null)
				try{con.close();}catch(Exception ex){}
		}
		return null;

	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
}
