package com.xplink.random_cm.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.xplink.random_cm.datamodel.KeywordBean;
import com.xplink.random_cm.datamodel.MemberBean;

public class LoginService {
	private ResultSet rsValidate;
	private ResultSet rsMemberDetail;
	private ResultSet rsKeyword;
	private DataSource dataSource;
	private Connection con;

	private static final Logger logger = Logger.getLogger(LoginService.class);

	public boolean validate(String username, String password)
			throws SQLException {

		logger.debug("IN:Log in Service method validate");

		con = dataSource.getConnection();

		Statement stmtValidate = con.createStatement();

		try {

			String sql = "SELECT USERNAME,PASSWORD FROM cmr_member";
			rsValidate = stmtValidate.executeQuery(sql);

			while (rsValidate.next()) {

				if ((rsValidate.getString("USERNAME").equals(username))
						&& (rsValidate.getString("PASSWORD").equals(password))) {
					logger.debug("OUT:Log in Service method validate complete");
					return true;
				}
			}

		} catch (Exception ex) {

			logger.error("Log in Service Exception:" + ex.getMessage(), ex);
		} finally {
			if (rsValidate != null)
				try {
					rsValidate.close();
				} catch (Exception ex) {
				}
			if (stmtValidate != null)
				try {
					stmtValidate.close();
				} catch (Exception ex) {
				}
			if (con != null) {
				con.close();
			}

		}

		return false;
	}

	public MemberBean getMember(String username) throws SQLException {

		logger.debug("IN:LoginService method getMember");
		con = dataSource.getConnection();

		Statement stmtGetMember = con.createStatement();
		MemberBean memberbean = new MemberBean();
		try {

			String sql = "SELECT * FROM cmr_member WHERE USERNAME = '"
					+ username + "'";
			rsMemberDetail = stmtGetMember.executeQuery(sql);

			while (rsMemberDetail.next()) {
				memberbean = new MemberBean();
				memberbean.setMemberid(rsMemberDetail.getInt("MEMBER_ID"));
				memberbean.setName(rsMemberDetail.getString("NAME"));
				memberbean.setSurname(rsMemberDetail.getString("SURNAME"));
				memberbean.setEmail(rsMemberDetail.getString("EMAIL"));
				memberbean.setUsername(rsMemberDetail.getString("USERNAME"));
				memberbean.setPassword(rsMemberDetail.getString("PASSWORD"));
				memberbean.setInviteEmail(rsMemberDetail
						.getString("INVITE_USER_EMAIL"));
				
				memberbean.setFB(rsMemberDetail.getString("FB"));
				

			}

			logger.debug("OUT:LoginService method getMember");

			return memberbean;
		} catch (Exception ex) {

			logger.error("getMember excepiton:" + ex.getMessage(), ex);
			return null;
		} finally {
			if (rsMemberDetail != null)
				try {
					rsMemberDetail.close();
				} catch (Exception ex) {
				}
			if (stmtGetMember != null)
				try {
					stmtGetMember.close();
				} catch (Exception ex) {
				}
			if (con != null) {
				con.close();
			}
		}
	}

	public MemberBean getMemberByID(int id) throws SQLException {

		logger.debug("IN:LoinService method getMemberByID");
		con = dataSource.getConnection();

		Statement stmtMemberID = con.createStatement();
		MemberBean memberbean = new MemberBean();
		try {

			String sql = "SELECT * FROM cmr_member WHERE MEMBER_ID =" + id;
			rsMemberDetail = stmtMemberID.executeQuery(sql);

			while (rsMemberDetail.next()) {
				memberbean = new MemberBean();
				memberbean.setMemberid(rsMemberDetail.getInt("MEMBER_ID"));
				memberbean.setName(rsMemberDetail.getString("NAME"));
				memberbean.setSurname(rsMemberDetail.getString("SURNAME"));
				memberbean.setEmail(rsMemberDetail.getString("EMAIL"));
				memberbean.setUsername(rsMemberDetail.getString("USERNAME"));
				memberbean.setPassword(rsMemberDetail.getString("PASSWORD"));
				memberbean.setInviteEmail(rsMemberDetail
						.getString("INVITE_USER_EMAIL"));
				
				memberbean.setFB(rsMemberDetail.getString("FB"));

			}

			logger.debug("ON:LoinService method getMemberById");
			return memberbean;
		} catch (Exception ex) {
			logger.error("getMemberByID excepiton:" + ex.getMessage(), ex);
			return null;
		} finally {
			if (rsMemberDetail != null)
				try {
					rsMemberDetail.close();
				} catch (Exception ex) {
				}
			if (stmtMemberID != null)
				try {
					stmtMemberID.close();
				} catch (Exception ex) {
				}
			if (con != null) {
				con.close();
			}
		}
	}

	public KeywordBean getKeywordBean(int memberid, int eventId)
			throws SQLException {

		logger.debug("IN:LoinService method getKeywordBean");
		con = dataSource.getConnection();
		Statement stmtKeyword = con.createStatement();

		try {

			KeywordBean keywordBean = new KeywordBean();

			String sql = "SELECT * FROM cmr_event_detail ed, cmr_keyword k "
					+ "WHERE ed.EVENT_DETAIL_ID = k.EVENT_DETAIL_ID AND ( ed.EVENT_ID ="
					+ eventId + " AND ed.INVITE_USER_ID =" + memberid + ")";

			rsKeyword = stmtKeyword.executeQuery(sql);

			while (rsKeyword.next()) {
				keywordBean.setEventDetailId(rsKeyword
						.getInt("EVENT_DETAIL_ID"));
				keywordBean.setInkeyword(rsKeyword.getString("INKEYWORD"));
				keywordBean.setOutkeyword(rsKeyword.getString("OUTKEYWORD"));
				keywordBean.setYear(rsKeyword.getString("YEAR"));
			}

			logger.debug("OUT:LoinService method getKeywordBean");
			return keywordBean;

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {
			if (rsKeyword != null)
				try {
					rsKeyword.close();
				} catch (Exception ex) {
				}
			if (stmtKeyword != null)
				try {
					stmtKeyword.close();
				} catch (Exception ex) {
				}
			if (con != null) {
				con.close();
			}
		}
		return null;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean CheckMemberByEmail(String email) throws SQLException {

		logger.debug("IN:LoinService method CheckMemberByEmail");
		con = dataSource.getConnection();
		boolean result = false;

		Statement stm = con.createStatement();
		try {

			logger.debug("IN:LoinService method CheckMemberByEmail: Email "
					+ email);

			String sql = "SELECT * FROM cmr_member WHERE INVITE_USER_EMAIL ='"
					+ email + "'";
			rsMemberDetail = stm.executeQuery(sql);

			while (rsMemberDetail.next()) {
				return true;
			}
			logger.debug(sql);
		} catch (Exception ex) {
			logger.error("getMemberByID excepiton:" + ex.getMessage(), ex);
			return false;
		}
		return result;
	}

	public MemberBean getMemberByEmail(String email) throws SQLException {

		logger.debug("IN:LoinService method getMemberByEmail");
		con = dataSource.getConnection();

		Statement stmtMemberID = con.createStatement();
		MemberBean memberbean = new MemberBean();
		try {
			logger.debug("IN:LoinService method getMemberByEmail Email: "
					+ email);
			String sql = "SELECT * FROM cmr_member WHERE INVITE_USER_EMAIL  ='"
					+ email + "'";
			rsMemberDetail = stmtMemberID.executeQuery(sql);

			while (rsMemberDetail.next()) {
				memberbean = new MemberBean();
				memberbean.setMemberid(rsMemberDetail.getInt("MEMBER_ID"));
				memberbean.setName(rsMemberDetail.getString("NAME"));
				memberbean.setSurname(rsMemberDetail.getString("SURNAME"));
				memberbean.setEmail(rsMemberDetail.getString("EMAIL"));
				memberbean.setUsername(rsMemberDetail.getString("USERNAME"));
				memberbean.setPassword(rsMemberDetail.getString("PASSWORD"));
				memberbean.setInviteEmail(rsMemberDetail
						.getString("INVITE_USER_EMAIL"));
				
				memberbean.setFB(rsMemberDetail.getString("FB"));

			}

			logger.debug("ON:LoinService method getMemberByEmail");
			con.close();
		} catch (Exception ex) {
			logger.error("getMemberByID excepiton:" + ex.getMessage(), ex);
			con.close();
		}
		return memberbean;
	}

}
