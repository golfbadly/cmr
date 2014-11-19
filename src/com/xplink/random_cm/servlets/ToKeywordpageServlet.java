package com.xplink.random_cm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.xplink.random_cm.managements.InputKeywordManager;
import com.xplink.random_cm.managements.UpdateStatusManager;

public class ToKeywordpageServlet implements Controller{
	 public ModelAndView handleRequest(HttpServletRequest request,
			   HttpServletResponse response) throws ServletException, IOException {
		
			ModelAndView modelAndView = new ModelAndView("InputKeyword"); //insert file.jsp want to go.
			
			try {

				return modelAndView;
				
			} catch (Exception e) {
			
				e.printStackTrace();
			}
			response.sendRedirect("Getlist.html");

			return null;		
		
		}	
}
