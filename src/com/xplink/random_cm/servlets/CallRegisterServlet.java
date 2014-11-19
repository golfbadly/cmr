package com.xplink.random_cm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class CallRegisterServlet implements Controller {

	private static final Logger logger = Logger.getLogger(CallRegisterServlet.class);
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		logger.debug("IN:CallRegisterServlet");
		
		HttpSession session = request.getSession();
		session.setAttribute("registerStatus", false);
		
		ModelAndView modelAndView = new ModelAndView("RegisterPage");
		return modelAndView;

	}
}
