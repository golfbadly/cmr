/*package com.xplink.random_cm.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.xplink.random_cm.service.EventAcceptService;

public class EventAcceptServlet extends AbstractController{
	private EventAcceptService eventAcceptService;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		String request_ids = request.getParameter("request_ids");
		
		logger.debug("[IN EventAcceptServlet]");
		logger.debug("[request_ids :]"+request_ids);
				
		eventAcceptService.eventDecode(request_ids);	
		
		return null;
	}

	public EventAcceptService getEventAcceptService() {
		return eventAcceptService;
	}

	public void setEventAcceptService(EventAcceptService eventAcceptService) {
		this.eventAcceptService = eventAcceptService;
	}
	

}
*/