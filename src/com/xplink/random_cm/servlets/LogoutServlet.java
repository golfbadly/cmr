package com.xplink.random_cm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;


public class LogoutServlet implements Controller{

	private static final Logger logger = LogManager.getLogger(LoginServlet.class);
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	    HttpSession session = request.getSession();
		ModelAndView modelAndView = new ModelAndView(new RedirectView("CallLogin.html"));
		logger.debug("[Start Logout!]");
		try {
			if(!session.isNew()){
				
				// Comment on 4/11/57
				/*session.removeAttribute("keyword");
				session.removeAttribute("member");
				session.removeAttribute("memberid");
				session.removeAttribute("result");*/
				
				session.removeAttribute("eventName");
				session.removeAttribute("username");
				session.removeAttribute("member");
				session.removeAttribute("eventId");
				session.removeAttribute("email");
				session.removeAttribute("checkMemberEmail");
				session.removeAttribute("checkMemberEventId");
				session.removeAttribute("registerStatus");
				session.removeAttribute("LoginStatus");
				session.removeAttribute("LoginUsername");
				session.removeAttribute("LoginPassword");
				
				logger.debug("[Finish Logout]");
				return modelAndView;
			}

		} catch (Exception ex) {
			logger.debug("[Exception :]"+ex.getMessage());
			ex.printStackTrace();
			return new ModelAndView("bug");
		}
		return modelAndView;

	}
}
