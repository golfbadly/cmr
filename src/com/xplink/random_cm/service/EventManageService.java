package com.xplink.random_cm.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.xplink.random_cm.datamodel.EventBean;

public class EventManageService {

	private DataSource dataSource;
	private RegisterService registerService;
	ResultSet rsEventId;
	ResultSet rsEvent;
	ResultSet rsGetType;

	private static final Logger logger = Logger
			.getLogger(EventManageService.class);

	public boolean create(EventBean eventdata, String email) throws Exception {

		Connection con = dataSource.getConnection();
		Statement stmtCreateEvent = con.createStatement();
		stmtCreateEvent = con.createStatement();
		boolean result = false;
		logger.debug("[--- IN add event to database ---]");
		logger.debug("[- before insert -]");

		try {
			//SQL command of cmr.sql 4/11/57
			/*
			 * String SQL = "INSERT INTO cmr_event VALUES ('','" +
			 * eventdata.getEventName() + "','f',"+null+","+null+",'" +
			 * eventdata.getCreateBy() + "','" + eventdata.getCreateDate()
			 * +"','',"+null+",'','')";
			 */
			
			//SQL command of cmrUpdate.sql 4/11/57
			String SQL = "INSERT INTO cmr_event VALUES ('','"
					+ eventdata.getEventName() + "'," + null + "," + null
					+ ",'" + eventdata.getCreateBy() + "','"
					+ eventdata.getCreateDate() + "',''," + null + ",'','','')";

			int row = stmtCreateEvent.executeUpdate(SQL);
			if (row > 0) {
				int lastEventId = getLastEventId(
						Integer.parseInt(eventdata.getCreateBy()), con);
				logger.debug("[lastEventId :]" + lastEventId);
				logger.debug("[createBy :]" + eventdata.getCreateBy());
				logger.debug("[Email :]" + email);
				addEventDetail(lastEventId,
						Integer.parseInt(eventdata.getCreateBy()), email, con);

				int eventDetailId = getEventDetailId(lastEventId,
						Integer.parseInt(eventdata.getCreateBy()), con);
				logger.debug("[eventDetailId :]" + eventDetailId);

				registerService.addSpace(eventDetailId, con);
				result = true;

				// con.commit();
			}

		} catch (Exception ex) {
			// con.rollback();
			logger.error("[Exception :]" + ex.getMessage(), ex);
		} finally {
			if (stmtCreateEvent != null)
				try {
					stmtCreateEvent.close();

				} catch (Exception ex) {
					logger.error("Exception:" + ex.toString(), ex);
				}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					logger.error("Exception:" + e.toString(), e);
				}
			}

		}
		logger.debug("[--- OUT add event to database ---]");
		return result;
	}

	public boolean addEventDetail(int dataEventId, int memberId, String email,
			Connection con) throws Exception {
		logger.debug("[IN :addEventDetail]");
		// Connection con = dataSource.getConnection();
		Statement stmtAddEventDetail = con.createStatement();
		boolean result = false;

		Statement stmt = con.createStatement();
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM cmr_event_detail "
					+ "WHERE INVITE_USER_ID = " + memberId + " "
					+ "AND EVENT_ID = " + dataEventId + " "
					+ "AND INVITE_USER_EMAIL = '" + email + "' ";
			logger.debug(sql);
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				return false;
			}

			String sqlEventDetail = "INSERT INTO cmr_event_detail VALUES ('',"
					+ dataEventId + "," + memberId + "," + "'" + email + "')";

			logger.debug("[--add Event Detail--]");

			int row_event = stmtAddEventDetail.executeUpdate(sqlEventDetail);

			if (row_event > 0) {
				logger.debug("[--add Complete--]");

				int eventDetailId = getEventDetailId(dataEventId, memberId, con);
				logger.debug("[Event Detail ID :]" + eventDetailId);

				result = true;
			}

		} catch (Exception ex) {
			logger.error("[exception DB:]" + ex.getMessage(), ex);
			throw ex;
		} finally {
			if (stmtAddEventDetail != null) {
				try {
					stmtAddEventDetail.close();
				} catch (Exception ex) {
					ex.getStackTrace();
				}
			}
		}
		logger.debug("[OUT:addEventDetail]");
		return result;
	}

	public boolean addEventDetailFB(int dataEventId, int memberId, String email)
			throws Exception {
		logger.debug("[IN :addEventDetail]");
		Connection con = dataSource.getConnection();
		Statement stmtAddEventDetail = con.createStatement();
		boolean result = false;
		Statement stmt = con.createStatement();
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM cmr_event_detail "
					+ " WHERE INVITE_USER_ID = " + memberId + " AND EVENT_ID ="
					+ dataEventId;

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				return false;
			}

			String sqlEventDetail = "INSERT INTO cmr_event_detail VALUES ('',"
					+ dataEventId + "," + memberId + "," + "'" + email + "')";

			logger.debug("[--add Event Detail--]");

			int row_event = stmtAddEventDetail.executeUpdate(sqlEventDetail);

			if (row_event > 0) {
				logger.debug("[--add Complete--]");

				int eventDetailId = getEventDetailId(dataEventId, memberId, con);
				logger.debug("[Event Detail ID :]" + eventDetailId);

				result = true;
			}
			// con.commit();
		} catch (Exception ex) {
			// con.rollback();
			logger.error("[exception DB:]" + ex.getMessage(), ex);
			throw ex;
		} finally {
			if (stmtAddEventDetail != null) {
				try {
					stmtAddEventDetail.close();
				} catch (Exception ex) {
					ex.getStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					logger.error("Exception:" + e.toString(), e);
				}
			}
		}
		logger.debug("[OUT:addEventDetail]");
		return result;
	}

	public int getEventDetailId(int dataEventId, int memberId, Connection con)
			throws Exception {
		int eventDetailId = 0;
		Statement stmtGetEvnetDetailId = con.createStatement();

		try {

			String sqlGetEventDetailId = "SELECT EVENT_DETAIL_ID FROM cmr_event_detail WHERE EVENT_ID='"
					+ dataEventId + "' AND INVITE_USER_ID='" + memberId + "'";
			rsEventId = stmtGetEvnetDetailId.executeQuery(sqlGetEventDetailId);

			rsEventId.next();
			eventDetailId = rsEventId.getInt(1);

			logger.debug("[eventDetailId:]" + eventDetailId);
		} catch (Exception ex) {
			logger.error("[exception DB:]" + ex.getMessage(), ex);
			throw ex;
		} finally {

			if (stmtGetEvnetDetailId != null)
				try {
					stmtGetEvnetDetailId.close();
				} catch (Exception ex) {
					ex.getStackTrace();
				}
			if (rsEventId != null) {
				try {
					rsEventId.close();
				} catch (Exception ex) {
					logger.error("exception close result set:" + ex.toString(),
							ex);
				}
			}

		}
		logger.debug("[OUT:getEventDetailId]");

		return eventDetailId;
	}

	public int getEventDetailId(int dataEventId, int memberId)
			throws SQLException {
		int eventDetailId = 0;

		Connection con = dataSource.getConnection();
		Statement stmtGetEvnetDetailId = con.createStatement();

		try {

			String sqlGetEventDetailId = "SELECT EVENT_DETAIL_ID FROM cmr_event_detail WHERE EVENT_ID='"
					+ dataEventId + "' AND INVITE_USER_ID='" + memberId + "'";
			rsEventId = stmtGetEvnetDetailId.executeQuery(sqlGetEventDetailId);

			rsEventId.next();
			eventDetailId = rsEventId.getInt(1);

			logger.debug("[eventDetailId:]" + eventDetailId);
		} catch (Exception ex) {
			logger.error("[exception DB:]" + ex.getMessage(), ex);
		} finally {
			if (stmtGetEvnetDetailId != null)
				try {
					stmtGetEvnetDetailId.close();
				} catch (Exception ex) {
					ex.getStackTrace();
				}
			if (rsEventId != null) {
				try {
					rsEventId.close();
				} catch (Exception ex) {
					logger.error("exception close result set:" + ex.toString(),
							ex);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					logger.error("Exception:" + e.toString(), e);
				}
			}

		}
		logger.debug("[OUT:getEventDetailId]");

		return eventDetailId;
	}

	public int getLastEventId(int memberId, Connection con) throws Exception {

		int eventId = 0;
		Statement stmtGetLastEvnetId = con.createStatement();

		try {
			logger.debug("[memberId:]" + memberId);

			String sqlGetLastEventId = "SELECT EVENT_ID FROM cmr_event ORDER BY EVENT_ID DESC LIMIT 1";

			rsEventId = stmtGetLastEvnetId.executeQuery(sqlGetLastEventId);

			rsEventId.next();
			eventId = rsEventId.getInt(1);

			logger.debug("[eventId:]" + eventId);
		} catch (Exception ex) {
			logger.error("[exception DB:]" + ex.getMessage(), ex);
			throw ex;
		} finally {
			if (stmtGetLastEvnetId != null)
				try {
					stmtGetLastEvnetId.close();
				} catch (Exception ex) {
					ex.getStackTrace();
				}
		}

		logger.debug("[OUT:getLastEventId]");

		return eventId;
	}

	public int getLastEventId(int memberId) throws Exception {

		int eventId = 0;
		Connection con = dataSource.getConnection();
		Statement stmtGetLastEvnetId = con.createStatement();

		try {
			logger.debug("[memberId:]" + memberId);

			String sqlGetLastEventId = "SELECT EVENT_ID FROM cmr_event ORDER BY EVENT_ID DESC LIMIT 1";

			rsEventId = stmtGetLastEvnetId.executeQuery(sqlGetLastEventId);

			rsEventId.next();
			eventId = rsEventId.getInt(1);

			logger.debug("[eventId:]" + eventId);
		} catch (Exception ex) {
			logger.error("[exception DB:]" + ex.getMessage(), ex);
			throw ex;
		} finally {
			if (stmtGetLastEvnetId != null) {
				try {
					stmtGetLastEvnetId.close();
				} catch (Exception ex) {
					ex.getStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					logger.error("Exception:" + e.toString(), e);
				}
			}
		}
		logger.debug("[OUT:getLastEventId]");

		return eventId;
	}

	public EventBean getEventById(int eventId) throws Exception {

		Connection con = dataSource.getConnection();
		Statement stmtGetEventById = con.createStatement();
		EventBean event = new EventBean();

		try {
			logger.debug("[eventId:]" + eventId);

			String sqlGetEventById = "SELECT * FROM cmr_event WHERE EVENT_ID = "
					+ eventId;
			logger.debug("SqlRandom:" + sqlGetEventById);
			rsEvent = stmtGetEventById.executeQuery(sqlGetEventById);

			rsEvent.next();
			event.setId(rsEvent.getInt("EVENT_ID"));
			event.setEventName(rsEvent.getString("EVENT_NAME"));
			event.setRandomed(rsEvent.getString("EVENT_RANDOMED"));
			event.setCreateBy(rsEvent.getString("CREATE_BY"));
			event.setImgType(rsEvent.getString("IMG_TYPE"));
			event.setDateStart(rsEvent.getTimestamp("START_DATE"));
			event.setDateEnd(rsEvent.getTimestamp("END_DATE"));
			event.setDesc(rsEvent.getString("EVENT_DESC"));

			logger.debug("get event ID :" + event.getId());
			logger.debug("event name :" + event.getEventName());
			logger.debug("date start :" + event.getDateStart());
			logger.debug("date end :" + event.getDateEnd());
			logger.debug("[eventId:]" + eventId);

		} catch (Exception ex) {
			logger.debug("[exception DB:]" + ex.getMessage(), ex);
			throw ex;
		} finally {
			if (stmtGetEventById != null)
				try {
					stmtGetEventById.close();
				} catch (Exception ex) {
					logger.error("exception:" + ex.toString(), ex);
				}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					logger.error("exception:" + e.toString(), e);
				}
			}
		}
		logger.debug("[OUT:getEventById]");

		return event;
	}

	public String getImgTypeById(int eventId) throws Exception {

		Connection con = dataSource.getConnection();
		Statement stmtGetImgTypeById = con.createStatement();
		String imgType = null;
		logger.debug("[In getImgTypeById]");
		try {
			logger.debug("[eventId:]" + eventId);

			String sqlGetImgTypeById = "SELECT IMG_TYPE FROM cmr_event WHERE EVENT_ID ="
					+ eventId;

			rsGetType = stmtGetImgTypeById.executeQuery(sqlGetImgTypeById);

			rsGetType.next();
			imgType = rsGetType.getString("IMG_TYPE");

			logger.debug(" Image Type :" + imgType);

		} catch (Exception ex) {
			logger.error("[exception DB:]" + ex.getMessage(), ex);
			throw ex;
		} finally {
			if (stmtGetImgTypeById != null) {
				try {
					stmtGetImgTypeById.close();
				} catch (Exception ex) {
					logger.debug("[exception DB:]" + ex.getMessage());
					ex.getStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					logger.error("Exception:" + e.toString(), e);
				}
			}
		}
		logger.debug("[OUT getImgTypeById]");

		return imgType;
	}

	public int getEventId(int eventDetailId) throws Exception {

		Connection con = dataSource.getConnection();
		Statement stmtGetEvnetId = con.createStatement();

		int eventId = 0;

		try {
			logger.debug("[eventDetailId:]" + eventDetailId);

			String sqlGetEventDetailId = "SELECT EVENT_ID FROM cmr_event_detail WHERE EVENT_DETAIL_ID='"
					+ eventDetailId + "'";
			rsEventId = stmtGetEvnetId.executeQuery(sqlGetEventDetailId);

			rsEventId.next();
			eventId = rsEventId.getInt(1);

			logger.debug("[eventId:]" + eventId);
		} catch (Exception ex) {
			logger.debug("[exception DB:]" + ex.getMessage(), ex);
			throw ex;
		} finally {
			if (stmtGetEvnetId != null)
				try {
					stmtGetEvnetId.close();
				} catch (Exception ex) {
					ex.getStackTrace();
				}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					logger.error("Exception:" + e.toString(), e);
				}
			}
		}
		logger.debug("[OUT:getEventId]");

		return eventId;
	}

	public String deleteEvent(int eventId) throws SQLException, Exception {

		Connection con = dataSource.getConnection();
		Statement stmtDeleteEventId = con.createStatement();

		logger.debug("[In deleteEvent ]");
		String result;

		try {
			logger.debug("[eventId:]" + eventId);

			String sqlDeleteEvent = "DELETE FROM cmr_event WHERE  cmr_event.EVENT_ID ="
					+ eventId;
			int delete = stmtDeleteEventId.executeUpdate(sqlDeleteEvent);

			if (delete == 1) {
				logger.debug("Event is deleted.");
				result = "true";
			} else {
				logger.debug("Event is not deleted.");
				result = "false";
			}
		} catch (SQLException s) {
			logger.error("[Exception :]" + s.getMessage(), s);
			result = "false";
			throw s;
		} catch (Exception e) {
			logger.error("[Exception :]" + e.getMessage(), e);
			result = "false";
			throw e;
		} finally {
			if (stmtDeleteEventId != null)
				try {
					stmtDeleteEventId.close();
				} catch (Exception ex) {
					ex.getStackTrace();
				}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					logger.error("Exception:" + e.toString(), e);
				}
			}
		}

		logger.debug("Out deleteEvent ]");

		return result;
	}

	public boolean setNullImg(int eventId) throws Exception {
		logger.debug("[IN :setNullImg]");
		Connection con = dataSource.getConnection();
		Statement stmtUpdateImgType = con.createStatement();

		boolean result = false;

		try {
			logger.debug("event id :" + eventId);

			String sqlUpdateEvent = "UPDATE cmr_event SET  IMG_TYPE=''"
					+ " WHERE EVENT_ID='" + eventId + "'";

			int row_event = stmtUpdateImgType.executeUpdate(sqlUpdateEvent);

			if (row_event > 0) {
				logger.debug("[--update Complete--]");

				result = true;
			}

		} catch (Exception ex) {
			logger.error("[exception DB:]" + ex.getMessage(), ex);
			throw ex;
		} finally {
			if (stmtUpdateImgType == null)
				try {
					//stmtUpdateImgType.close();
				} catch (Exception ex) {
					ex.getStackTrace();
				}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					logger.error("Exception:" + e.toString(), e);
				}
			}
		}
		logger.debug("[OUT :setNullImg]");
		return result;

	}

	public boolean updateEvent(EventBean eventdata, int eventId, int memberId)
			throws Exception {
		logger.debug("[IN :updateEvent]");
		Connection con = dataSource.getConnection();
		Statement stmtUpdateEvent = con.createStatement();

		boolean result = false;

		try {
			// logger.debug("event name :"+eventdata.getEventName());
			// logger.debug("start date :"+eventdata.getDateStart());
			// logger.debug("end date :"+eventdata.getDateEnd());
			// logger.debug("member id :"+memberId);
			// logger.debug("update date :"+eventdata.getUpdateDate());
			// logger.debug("event id :"+eventId);
			// logger.debug("event img type :"+eventdata.getImgType());
			// logger.debug("event desc :"+eventdata.getDesc());

			String sqlUpdateEvent = "UPDATE cmr_event SET  EVENT_NAME='"
					+ eventdata.getEventName() + "'" + ", START_DATE='"
					+ eventdata.getDateStart() + "'" + ", END_DATE='"
					+ eventdata.getDateEnd() + "',UPDATE_BY='" + memberId + "'"
					+ ",UPDATE_DATE='" + eventdata.getUpdateDate() + "'"
					+ ",EVENT_RANDOMED ='" + eventdata.getRandomed() + "'"
					+ ", EVENT_DESC='" + eventdata.getDesc()
					+ "'  WHERE EVENT_ID='" + eventId + "'";

			logger.debug("Update" + sqlUpdateEvent);

			int row_event = stmtUpdateEvent.executeUpdate(sqlUpdateEvent);

			if (row_event > 0) {
				logger.debug("[--update Complete--]");

				result = true;
			}

			// con.commit();

		} catch (Exception ex) {
			// con.rollback();
			logger.debug("[exception DB:]" + ex.getMessage(), ex);
			throw ex;
		} finally {
			if (stmtUpdateEvent != null) {
				try {
					stmtUpdateEvent.close();
				} catch (Exception ex) {
					ex.getStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					logger.error("Exception:" + e.toString(), e);
				}
			}
		}
		logger.debug("[OUT :updateEvent]");
		return result;
	}

	public boolean updateImageEvent(EventBean eventdata, int eventId,
			int memberId) throws Exception {
		logger.debug("[IN :updateImageEvent]");
		Connection con = dataSource.getConnection();
		Statement stmtUpdateImageEvent = con.createStatement();

		boolean result = false;

		try {
			logger.debug("event name :" + eventdata.getEventName());
			logger.debug("start date :" + eventdata.getDateStart());
			logger.debug("end date :" + eventdata.getDateEnd());
			logger.debug("member id :" + memberId);
			logger.debug("update date :" + eventdata.getUpdateDate());
			logger.debug("event id :" + eventId);
			logger.debug("event img type :" + eventdata.getImgType());
			logger.debug("event desc :" + eventdata.getDesc());

			String sqlUpdateEvent = "UPDATE cmr_event SET  EVENT_NAME='"
					+ eventdata.getEventName() + "'" + ", START_DATE='"
					+ eventdata.getDateStart() + "'" + ", END_DATE='"
					+ eventdata.getDateEnd() + "',UPDATE_BY='" + memberId + "'"
					+ ",UPDATE_DATE='" + eventdata.getUpdateDate()
					+ "',IMG_TYPE='" + eventdata.getImgType() + "'"
					+ ", EVENT_DESC='" + eventdata.getDesc()
					+ "' WHERE EVENT_ID='" + eventId + "'";

			int row_event = stmtUpdateImageEvent.executeUpdate(sqlUpdateEvent);

			if (row_event > 0) {
				logger.debug("[--update Complete--]");

				result = true;
				// con.commit();
			}

		} catch (Exception ex) {
			// con.rollback();
			logger.error("[exception :]" + ex.getMessage(), ex);
			throw ex;
		} finally {
			if (stmtUpdateImageEvent != null)
				try {
					stmtUpdateImageEvent.close();
				} catch (Exception ex) {
					logger.error("exception:" + ex.toString(), ex);
				}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					logger.error("exception:" + e.toString(), e);
				}
			}
		}
		logger.debug("[OUT :updateImageEvent]");
		return result;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public RegisterService getRegisterService() {
		return registerService;
	}

	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}

	public boolean addEventDetailFromEmail(int eventId, String email,
			int memberId) throws Exception {
		logger.debug("[IN :addEventDetailFromEmail]");
		Connection con = dataSource.getConnection();
		Statement stmtAddEventDetail = con.createStatement();

		Statement stmt = con.createStatement();
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM cmr_event_detail "
					+ "WHERE EVENT_ID = " + eventId + " "
					+ "AND INVITE_USER_EMAIL = '" + email + "' ";
			logger.debug(sql);
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				return false;
			}

			String sqlEventDetail = "INSERT INTO cmr_event_detail VALUES ('',"
					+ eventId + "," + memberId + "," + "'" + email + "')";
			logger.debug("[IN :addEventDetailFromEmail Email: ]" + email);
			logger.debug("[IN :addEventDetailFromEmail EventID: ]" + eventId);

			stmtAddEventDetail.executeUpdate(sqlEventDetail);
			logger.debug("[--add Event Detail complete--]");

			// Add cmr_keyword.

			String sqlkey = "SELECT EVENT_DETAIL_ID FROM cmr_event_detail "
					+ "WHERE EVENT_ID = " + eventId + " "
					+ "AND INVITE_USER_EMAIL = '" + email + "' ";
			logger.debug(sqlkey);
			rs = stmt.executeQuery(sqlkey);
			rs.next();
			int eventDetailId = rs.getInt(1);

			logger.debug("[EventDetailID: ]" + eventDetailId);

			Connection conn = dataSource.getConnection();
			registerService.addSpace(eventDetailId, conn);
			logger.debug("[--add cmr_keyword complete--]");

			logger.debug("[OUT:addEventDetail]");
			return true;

		} catch (Exception ex) {
			logger.error("[exception DB:]" + ex.getMessage(), ex);
			return false;
		}
	}

}
