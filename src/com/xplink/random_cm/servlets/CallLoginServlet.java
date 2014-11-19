package com.xplink.random_cm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class CallLoginServlet implements Controller {

	private static final Logger logger = Logger.getLogger(CallLoginServlet.class);
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		logger.debug("IN:CallLoginServlet");
		
		ModelAndView modelAndView = new ModelAndView("LoginPage");
		request.setAttribute("result", "non");
		return modelAndView;

	}
}
