package com.xplink.random_cm.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.xplink.random_cm.datamodel.MemberBean;
import com.xplink.random_cm.managements.GetlistManager;
import com.xplink.random_cm.service.EventManageService;

/**
 * Servlet implementation class for Servlet: GetlistServlet
 *
 */
 public class GetlistServlet implements Controller{
   static final long serialVersionUID = 1L;
   private  GetlistManager getlistManager;	
   private   EventManageService eventManageService ;
   
 ArrayList<MemberBean> list;
 
 private static final Logger logger = Logger.getLogger(GetlistServlet.class);
 
	public ModelAndView handleRequest(HttpServletRequest request,
			   HttpServletResponse response) throws ServletException, IOException {
		
		 logger.debug("[IN GetlistServlet]");
			
			ModelAndView mav= new ModelAndView("ListMember"); 
		
			  String eventDetailId = request.getParameter("eventDetailId");
			  request.setAttribute("eventDetailId", eventDetailId);
			  
			  logger.debug("[eveventDatailId :]"+eventDetailId);
		
			  try {
					
				  int eventId = eventManageService.getEventId(Integer.parseInt(eventDetailId));
			      request.setAttribute("eventId", eventId+"");
			      
				request.setAttribute("list", getlistManager.getMember(eventId));
				return mav;
				
			} catch (Exception e) {		
				 logger.debug("[Exception :]"+e.getMessage());					
				e.printStackTrace();
				return new ModelAndView("bug");
			}
		
		}

	public EventManageService getEventManageService() {
		return eventManageService;
	}

	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}

	public GetlistManager getGetlistManager() {
		return getlistManager;
	}

	public void setGetlistManager(GetlistManager getlistManager) {
		this.getlistManager = getlistManager;
	}	
	
	
	
}