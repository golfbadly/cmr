package com.xplink.random_cm.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Servlet implementation class for Servlet: getMemberID
 *
 */
 public class GetDetailMemberServlet implements Controller {
	  	    
		public ModelAndView handleRequest(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {

			 HttpSession session = request.getSession();

			// ModelAndView modelAndView = new ModelAndView("Getlist"); //insert file.jsp want to go.
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			session.setAttribute("username", username);

//			try {
//				if () {
//					return new ModelAndView(new RedirectView("Getlist.html"));
//				}
//
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block --> LOGING
//				e.printStackTrace();
//			}

			ModelAndView login = new ModelAndView("Login");
			return login;

		}
}