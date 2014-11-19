package com.xplink.random_cm.controller.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.sql.*;

public class AjaxNoteTextServlet implements Controller {
	private DataSource dataSource;
	private static final Logger logger = Logger.getLogger(AjaxNoteTextServlet.class);
	
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		String noteText = req.getParameter("noteText");
		String result = "" ;
		
		if(noteText == null){
			
			return new ModelAndView("notePaper");
			
		}
	
		logger.debug("[IN AjaxNoteTextServlet]");
		
		Connection conn = null;
		Statement stmt = null;
		try{
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "INSERT INTO cmr_test_noteText VALUES ('"+noteText+"')" ;
			stmt.executeUpdate(sql);
			result = noteText ;
		}catch(Exception e){
			result = "have error" ;
			logger.error("Exception AjaxNoteTextServlet:"+e.toString(),e);
		}finally{
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		}
		
		ModelAndView mv = new ModelAndView("getAjaxResult");
		mv.addObject("ajaxResult", result);
		return mv;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}



