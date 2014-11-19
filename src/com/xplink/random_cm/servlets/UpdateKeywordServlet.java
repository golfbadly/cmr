package com.xplink.random_cm.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.xplink.random_cm.managements.UpdateKeywordManager;

/**
 * Servlet implementation class for Servlet: UpdateKeywordServlet
 */
 public class UpdateKeywordServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   UpdateKeywordManager updatekey;
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
   private static final Logger logger = LogManager.getLogger(UpdateKeywordServlet.class);

	public UpdateKeywordServlet() {
		super();
		updatekey = new UpdateKeywordManager();
	}   	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String kw = request.getParameter("kw");
		String memberid = request.getParameter("memberid");
		
	try{	
		//updatekey.UpdateKW(eventDetailId, kw);
	}catch(Exception e){
		  logger.debug("[Exception :]"+e.getMessage());
	}
		response.sendRedirect("/Random_CM/GetlistServlet");
		
		
		
		
	}   	  	    
}