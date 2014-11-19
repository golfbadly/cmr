package com.xplink.random_cm.servlets;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import com.xplink.random_cm.datamodel.EventBean;
import com.xplink.random_cm.service.SimpleAjaxController;
import com.xplink.random_cm.service.EventManageService;

public class EventImgDeleteServlet extends SimpleAjaxController {

	private static final Logger logger = Logger.getLogger(EventImgDeleteServlet.class);
	private String imgPath;
	private EventManageService eventManageService;

protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
	
		logger.debug("[IN EventImgDeleteservlet ]");	
		   String result = "NO";
		
	try{	
	    String eventId = request.getParameter("eventId");
	    String type = request.getParameter("type");
    
		if(logger.isDebugEnabled()){
		logger.debug("Event ID is : " +eventId);
		}
		String imageFullName  = imgPath + "/" + eventId + "." + type ;
		logger.debug("Image Path : " + imageFullName);
		File imageFile = new File(imageFullName);
		if(imageFile.delete()){
			if(logger.isDebugEnabled()){
			logger.info("Delete File success");
					eventManageService.setNullImg(Integer.parseInt(eventId));			
			  result ="YES";
			}
		}
		if(logger.isDebugEnabled()){
	    logger.debug("OUT : deleteFile ");
		}

		EventBean eventdata =  new EventBean();
			        eventdata = eventManageService.getEventById(Integer.parseInt(eventId));
		request.setAttribute("eventdata", eventdata);
		
		logger.debug("[OUT EventImgDeleteservlet ]");
	  
	}catch(Exception ex){
		logger.debug("[Exception :]"+ex.getMessage());		
		return new ModelAndView("bug");
	}
	   return getModelAndViewWithResult(result);
			
	}		
	
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public EventManageService getEventManageService() {
		return eventManageService;
	}

	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}
	
	
}
