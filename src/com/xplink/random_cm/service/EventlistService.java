package com.xplink.random_cm.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.xplink.random_cm.datamodel.EventBean;

public class EventlistService {

	ResultSet rs;
	private static final Logger logger = LogManager
			.getLogger(EventlistService.class);
	private DataSource dataSource;

	public ArrayList<EventBean> getEvent(int memberId, String email)
			throws Exception {
		logger.debug("[IN EventlistService]");

		Connection con = dataSource.getConnection();
		Statement stmt = con.createStatement();
		ArrayList<EventBean> list = new ArrayList<EventBean>();

		logger.debug("[member Id :]" + memberId);
		logger.debug("[member Email :]" + email);
		try {
			String sql = "SELECT * FROM cmr_event, cmr_event_detail "
					+ "WHERE cmr_event.EVENT_ID = cmr_event_detail.EVENT_ID "
					+ "AND cmr_event_detail.INVITE_USER_EMAIL= '" + email + "'";

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				EventBean event = new EventBean();
				int eventId = rs.getInt("EVENT_ID");
				event.setId(eventId);
				logger.debug("event id :" + eventId);

				event.setEventName(rs.getString("EVENT_NAME"));
				logger.debug("event name :" + event.getEventName());
				event.setRandomed(rs.getString("EVENT_RANDOMED"));
				logger.debug("event randomed :" + event.getRandomed());
				event.setDateStart(rs.getTimestamp("START_DATE"));
				logger.debug("event start :" + event.getDateStart());
				event.setDateEnd(rs.getTimestamp("END_DATE"));
				logger.debug("event end :" + event.getDateEnd());
				event.setEventDetailId(rs
						.getInt("cmr_event_detail.EVENT_DETAIL_ID"));
				logger.debug("event detailId :" + event.getEventDetailId());
				event.setInviteUserId(rs
						.getInt("cmr_event_detail.INVITE_USER_ID"));
				logger.debug("event inviteUserId :" + event.getInviteUserId());
				event.setCreateBy(rs.getString("CREATE_BY"));
				logger.debug("event createBy :" + event.getCreateBy());

				// set readyState
				event.setReadyState(isAlready(eventId));
				logger.debug("[EventlistService: Event = ]" + event);

				list.add(event);
			}
			logger.debug("[EventlistService:] List = " + list);
			logger.debug("[OUT EventlistService]");
			return list;
		} catch (Exception ex) {
			logger.error("exception:" + ex.toString(), ex);
			throw ex;
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception ex) {
				}
		}
	}

	public Boolean isAlready(int eventId) throws Exception {

		Connection con = dataSource.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = null;

		try {
			String sql = "SELECT INKEYWORD FROM `cmr_keyword` WHERE EVENT_DETAIL_ID IN (SELECT EVENT_DETAIL_ID  FROM  cmr_event_detail  WHERE EVENT_ID = "
					+ eventId + " )";
			rs = stmt.executeQuery(sql);
			Boolean ready = true;
			int count = 0;
			while (rs.next()) {
				count++;
				String keyword = rs.getString("INKEYWORD");
				if (keyword == null || keyword.equals("")) {
					ready = false;
					break;
				}
			}
			if (count < 2) {
				// Must have 2+ players
				ready = false;
			}

			return ready;
		} catch (Exception ex) {
			logger.error("exception:" + ex.toString(), ex);
			throw ex;
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception ex) {
				}
		}

	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
