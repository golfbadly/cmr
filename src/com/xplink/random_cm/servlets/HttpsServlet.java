package com.xplink.random_cm.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;


public class HttpsServlet extends HttpServlet {

	private static final long serialVersionUID = -4509052825706785581L;
	private static final Logger logger = Logger.getLogger(HttpsServlet.class);
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		  logger.debug("[LoadIndex *get]");
		  String sig_req = request.getParameter("signed_request");  
		  String request_ids = request.getParameter("request_ids");	
		  logger.debug("[*sined request :]"+sig_req);
		  HttpSession session =request.getSession();
		  if(request_ids!="null"&&request_ids!=null){	  
			  session.setAttribute("request_ids", request_ids);
		  }
				request.setAttribute("signed_request", sig_req);
				RequestDispatcher dispatcher = request.getRequestDispatcher("home.html");
				dispatcher.forward(request, response);

		}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		  logger.debug("[LoadIndex *post]");
		  String sig_req = request.getParameter("signed_request");  
		  String request_ids = request.getParameter("request_ids");	
		  logger.debug("[*sined request :]"+sig_req);
		  HttpSession session =request.getSession();
		  if(request_ids!="null"&&request_ids!=null){	  
			  session.setAttribute("request_ids", request_ids);
		  }
				request.setAttribute("signed_request", sig_req);
				RequestDispatcher dispatcher = request.getRequestDispatcher("home.html");
				dispatcher.forward(request, response);

		}  
}
