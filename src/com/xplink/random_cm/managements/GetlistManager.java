package com.xplink.random_cm.managements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.xplink.random_cm.datamodel.MemberBean;

public class GetlistManager {
	ArrayList<MemberBean> list = new ArrayList<MemberBean>();
	private DataSource dataSource;
	ResultSet rs;
	private static final Logger logger = LogManager.getLogger(GetlistManager.class);

	public ArrayList<MemberBean> getMember(int eventId) throws SQLException {
		logger.debug("[IN GetlistManager]");
		
		Connection con = dataSource.getConnection();
		Statement stmt = con.createStatement();
		MemberBean memberbean;
		try {
			String sql = "SELECT m.MEMBER_ID, m.NAME, m.SURNAME, m.EMAIL, m.STATUS "+
                         "FROM cmr_member m INNER JOIN cmr_event_detail ed ON m.MEMBER_ID = ed.INVITE_USER_ID "+
                         "JOIN cmr_event e ON e.EVENT_ID = ed.EVENT_ID WHERE e.EVENT_ID = "+eventId +" ORDER BY m.MEMBER_ID ASC ";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				logger.debug("[result set next...]");
				memberbean = new MemberBean();
				memberbean.setMemberid(rs.getInt("m.MEMBER_ID"));
				memberbean.setName(rs.getString("m.NAME"));
				logger.debug("name :"+rs.getString("m.NAME"));
				memberbean.setSurname(rs.getString("m.SURNAME"));
				memberbean.setEmail(rs.getString("m.EMAIL"));
				memberbean.setStatus(rs.getString("m.STATUS"));
				list.add(memberbean);
			}
			
			logger.debug("[OUT GetlistManager]");
			return list;
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
