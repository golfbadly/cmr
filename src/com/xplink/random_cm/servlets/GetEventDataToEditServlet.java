package com.xplink.random_cm.servlets;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.xplink.random_cm.datamodel.EventBean;
import com.xplink.random_cm.service.EventManageService;

public class GetEventDataToEditServlet implements Controller {
	
	 private static final Logger logger = Logger.getLogger(GetEventDataToEditServlet.class);
	 private String imgPath;
	 private String imgPathOnServer;
	 private static final SimpleDateFormat dayMonthYearformatter = new SimpleDateFormat("yyyy-MM-dd");
	 private EventManageService eventManageService ;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		logger.debug("[IN GetEventDataToEdit hand]");
		
		int eventId = Integer.parseInt(request.getParameter("eventId"));
		
		try{	
		    	logger.debug("[recive eventStatus no edit]");
		    	
		    	EventBean eventData = new EventBean();
		    	eventData = eventManageService.getEventById(eventId);
		    	
		    	logger.debug("[eventId :]"+eventData.getId());
		    	logger.debug("[eventEventName :]"+eventData.getEventName());
		    	logger.debug("[eventImgType :]"+eventData.getImgType());
		    	logger.debug("[eventCreateBy :]"+eventData.getCreateBy());
		    	
		    	
		    	request.setAttribute("eventdata", eventData);	
		    
		    if(eventData.getDateStart()!=null){
		    	
		    String startDate = dayMonthYearformatter.format((java.util.Date) eventData.getDateStart());	
		    String endDate=	dayMonthYearformatter.format((java.util.Date) eventData.getDateEnd());
		    
		    	request.setAttribute("startDate",startDate);
		    	request.setAttribute("endDate",endDate);
		    	request.setAttribute("imgPathOnServer", imgPathOnServer);
		    }	
				logger.debug("[OUT GetEventDataToEdit hand]");
		 
	
		}catch(Exception ex){
			logger.error("[Exception]"+ex.getMessage());
			return new ModelAndView("bug");
		}
		
	 return new ModelAndView("editEvent"); 
	
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getImgPathOnServer() {
		return imgPathOnServer;
	}

	public void setImgPathOnServer(String imgPathOnServer) {
		this.imgPathOnServer = imgPathOnServer;
	}

	public EventManageService getEventManageService() {
		return eventManageService;
	}

	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}
	
	

}
