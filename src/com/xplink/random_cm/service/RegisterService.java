package com.xplink.random_cm.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import com.xplink.random_cm.datamodel.MemberBean;

public class RegisterService {
	private ResultSet rsGetMember;
	private ResultSet rsGetMemberByID;
	private DataSource dataSource;

	private static final Logger logger = Logger
			.getLogger(RegisterService.class);
	
	//Not use 4/11/57

	/*
	 * public boolean register(MemberBean mb) throws Exception { boolean result
	 * = false;
	 * 
	 * logger.debug("[IN:RegisterService method register]");
	 * 
	 * Connection con = dataSource.getConnection(); Statement stmtRegisMember =
	 * con.createStatement();
	 * 
	 * try { //user register String SQL_Member =
	 * "INSERT INTO cmr_member VALUES ('','" + mb.getName() + "','"+
	 * mb.getSurname() + "','" + mb.getEmail() + "','" + mb.getUsername() +
	 * "','" + mb.getPassword() + "','"+mb.getStatus()+"','F')";
	 * 
	 * int row_member = stmtRegisMember.executeUpdate(SQL_Member); if
	 * (row_member > 0) { result = true; }
	 * 
	 * 
	 * } catch (Exception ex) { logger.error("error:"+ex.toString(),ex); }
	 * finally{ if(stmtRegisMember!= null)
	 * try{stmtRegisMember.close();}catch(Exception ex){} if(con != null){ try{
	 * con.close(); }catch(Exception e){
	 * logger.error("exception:"+e.toString(),e); } }
	 * 
	 * } logger.debug("[OUT:RegisterService method register]");
	 * 
	 * return result; }
	 */
	
	/*register FB*/
	public boolean registerFB(String name, String surname, String email,
			String username, String password, String status)
			throws SQLException {
		boolean result = false;

		logger.debug("[IN:RegisterService method registerFB]");
		logger.debug("[IN:RegisterService Name: ]"+name);

		Connection con = dataSource.getConnection();
		Statement stmtRegister = con.createStatement();
		stmtRegister = con.createStatement();
		try {
			String SQL = "INSERT INTO cmr_member VALUES ('','" + name + "','"
					+ surname + "','" + email + "','" + username + "','"
					+ password + "','" + status + "','T','" + email + "')";// ,'"+email+"'

			int row = stmtRegister.executeUpdate(SQL);
			if (row > 0) {
				result = true;
			}
			logger.debug(SQL);
			logger.debug("[OUT:RegisterService method registerFB]");

		} catch (Exception ex) {
			logger.error("error:" + ex.toString(), ex);
		} finally {
			if (stmtRegister != null)
				try {
					stmtRegister.close();
				} catch (Exception ex) {
				}

			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					logger.error("exception:" + e.toString(), e);
				}
			}
		}

		return result;
	}

	
	

	public boolean register(String name, String surname, String email,
			String username, String password, String status)
			throws SQLException {
		boolean result = false;

		logger.debug("[IN:RegisterService method register]");

		Connection con = dataSource.getConnection();
		Statement stmtRegister = con.createStatement();
		stmtRegister = con.createStatement();
		try {
			String SQL = "INSERT INTO cmr_member VALUES ('','" + name + "','"
					+ surname + "','" + email + "','" + username + "','"
					+ password + "','" + status + "','F','" + email + "')";// ,'"+email+"'

			int row = stmtRegister.executeUpdate(SQL);
			if (row > 0) {
				result = true;
			}

			logger.debug("[OUT:RegisterService method register]");

		} catch (Exception ex) {
			logger.error("error:" + ex.toString(), ex);
		} finally {
			if (stmtRegister != null)
				try {
					stmtRegister.close();
				} catch (Exception ex) {
				}

			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					logger.error("exception:" + e.toString(), e);
				}
			}
		}

		return result;
	}

	public boolean addSpace(int dataEventId, Connection con) throws Exception {
		boolean result = false;

		logger.debug("IN:RegisterService method addSpace");
		// Connection con = dataSource.getConnection();
		Statement stmtAddSpace = con.createStatement();
		stmtAddSpace = con.createStatement();

		try {
			Calendar toDay = Calendar.getInstance();
			int year = toDay.get(Calendar.YEAR);

			String SQL = "INSERT INTO cmr_keyword VALUES ('','',''," + year
					+ "," + dataEventId + ")";

			int row = stmtAddSpace.executeUpdate(SQL);
			if (row > 0) {
				result = true;
			}
		} catch (Exception ex) {
			logger.error("error:" + ex.toString(), ex);
			throw ex;
		} finally {
			if (stmtAddSpace != null)
				try {
					stmtAddSpace.close();
				} catch (Exception ex) {
				}
		}

		logger.debug("OUT:RegisterService method addSpace");
		return result;
	}

	public boolean addSpace(int dataEventId) throws Exception {
		boolean result = false;

		logger.debug("IN:RegisterService method addSpace");
		Connection con = dataSource.getConnection();
		Statement stmtAddSpace = con.createStatement();
		// stmtAddSpace = con.createStatement();

		try {
			Calendar toDay = Calendar.getInstance();
			int year = toDay.get(Calendar.YEAR);

			Statement stmt = con.createStatement();
			ResultSet rs = null;

			String sql = "SELECT * FROM cmr_keyword " + " WHERE YEAR = " + year
					+ " AND EVENT_DETAIL_ID =" + dataEventId;

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				return false;
			}

			String SQL = "INSERT INTO cmr_keyword VALUES ('','',''," + year
					+ "," + dataEventId + ")";
			// stmtAddSpace.executeUpdate(SQL);
			int row = stmtAddSpace.executeUpdate(SQL);
			if (row > 0) {
				result = true;
				// con.commit();
			}
		} catch (Exception ex) {
			// con.rollback();
			logger.error("error:" + ex.toString(), ex);
			throw ex;
		} finally {
			if (stmtAddSpace != null)
				try {
					stmtAddSpace.close();
				} catch (Exception ex) {
				}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					logger.error("exception:" + e.toString(), e);
				}
			}
		}

		logger.debug("OUT:RegisterService method addSpace");
		return result;
	}

	public MemberBean getMember(String username) throws SQLException {

		logger.debug("IN:RegisterService method getMember");
		Connection con = dataSource.getConnection();
		Statement stmtGetMember = con.createStatement();
		MemberBean memberbean = new MemberBean();
		try {

			String sql = "SELECT * FROM cmr_member WHERE USERNAME = '"
					+ username + "'";
			rsGetMember = stmtGetMember.executeQuery(sql);

			while (rsGetMember.next()) {
				memberbean = new MemberBean();
				memberbean.setMemberid(rsGetMember.getInt("MEMBER_ID"));
				memberbean.setName(rsGetMember.getString("NAME"));
				memberbean.setSurname(rsGetMember.getString("SURNAME"));
				memberbean.setEmail(rsGetMember.getString("EMAIL"));
				memberbean.setUsername(rsGetMember.getString("USERNAME"));
				memberbean.setPassword(rsGetMember.getString("PASSWORD"));
				
				memberbean.setFB(rsGetMember.getString("FB"));
				
			}

		} catch (Exception ex) {
			logger.error("error:" + ex.toString(), ex);
		} finally {
			if (rsGetMember != null)
				try {
					rsGetMember.close();
				} catch (Exception ex) {
				}
			if (stmtGetMember != null)
				try {
					stmtGetMember.close();
				} catch (Exception ex) {
				}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					logger.error("exception:" + e.toString(), e);
				}
			}
		}

		return memberbean;
	}

	public MemberBean getMemberByID(int id) throws SQLException {

		logger.debug("IN:RegisterService method getMemberByID");
		Connection con = dataSource.getConnection();
		Statement stmtGetMemberByID = con.createStatement();
		MemberBean memberbean = new MemberBean();
		try {

			String sql = "SELECT * FROM cmr_member WHERE MEMBER_ID =" + id;
			rsGetMemberByID = stmtGetMemberByID.executeQuery(sql);

			while (rsGetMemberByID.next()) {
				memberbean = new MemberBean();
				memberbean.setMemberid(rsGetMemberByID.getInt("MEMBER_ID"));
				memberbean.setName(rsGetMemberByID.getString("NAME"));
				memberbean.setSurname(rsGetMemberByID.getString("SURNAME"));
				memberbean.setEmail(rsGetMemberByID.getString("EMAIL"));
				memberbean.setUsername(rsGetMemberByID.getString("USERNAME"));
				memberbean.setPassword(rsGetMemberByID.getString("PASSWORD"));
				
				memberbean.setFB(rsGetMemberByID.getString("FB"));
			}

		} catch (Exception ex) {
			logger.error("error:" + ex.toString(), ex);
		} finally {
			if (rsGetMemberByID != null)
				try {
					rsGetMemberByID.close();
				} catch (Exception ex) {
				}
			if (stmtGetMemberByID != null)
				try {
					stmtGetMemberByID.close();
				} catch (Exception ex) {
				}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					logger.error("exception:" + e.toString(), e);
				}
			}
		}

		return memberbean;
	}

	//Not use 4/11/57
	/*
	 * public Boolean isExistmembet(String email) throws SQLException {
	 * ResultSet member = null; Connection con = dataSource.getConnection();
	 * Statement stmtExistMember = con.createStatement();
	 * 
	 * String sql = "select * from cmr_member where email = "+email; member =
	 * stmtExistMember.executeQuery(sql);
	 * 
	 * return false; }
	 */

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
