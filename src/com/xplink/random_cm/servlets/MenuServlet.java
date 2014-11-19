package com.xplink.random_cm.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class MenuServlet implements Controller {
	
	 private static final Logger logger = Logger.getLogger(MenuServlet.class);
		

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.debug("[IN Menu Servlet]");
		
		return new ModelAndView("menu");
	}

}
