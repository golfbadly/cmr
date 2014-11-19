package com.xplink.random_cm.managements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.xplink.random_cm.datamodel.KeywordBean;

public class GetKeywordManager {

	private DataSource dataSource;
	private static final Logger logger = LogManager.getLogger(GetKeywordManager.class);

	public KeywordBean getKeywordBean(int memberid) throws Exception{
		logger.info("In getKywordBean");
		Connection con = dataSource.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = null;
		
		try {
			
			KeywordBean kb = new KeywordBean();
			
			String sql = "SELECT * FROM cmr_keyword k, cmr_event_detail ed "+
			             "WHERE ed.EVENT_DETAIL_ID = k.EVENT_DETAIL_ID AND ed.EVENT_DETAIL_ID="+memberid;
		
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				kb.setEventDetailId((rs.getInt("k.EVENT_DETAIL_ID")));				
				kb.setInkeyword(rs.getString("INKEYWORD"));
				kb.setOutkeyword(rs.getString("OUTKEYWORD"));
				kb.setYear(rs.getString("YEAR"));
			}
			logger.info("getKywordBean: "+kb);
			logger.info("OUT getKywordBean");
			return kb;
			
		} catch (Exception ex) {
			logger.error("error:"+ex.toString(),ex);
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
