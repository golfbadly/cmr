package com.xplink.random_cm.servlets;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;
import com.xplink.random_cm.service.EventManageService;

public class EventDeleteServlet implements Controller {

	private static final Logger logger = Logger
			.getLogger(EventDeleteServlet.class);
	private String imgPath;
	private EventManageService eventManageService;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String[] eventId = request.getParameterValues("eventDel");

			logger.debug("{eventId length :}" + eventId.length);
			logger.debug("{eventId :}" + eventId);

			for (int i = 0; i < eventId.length; i++) {
				// delete image
				String imageFullName = imgPath
						+ "/"
						+ eventId[i]
						+ "."
						+ eventManageService.getImgTypeById(Integer
								.parseInt(eventId[i]));
				logger.debug("Image Path : " + imageFullName);
				File imageFile = new File(imageFullName);
				imageFile.delete();

				// delete event data
				eventManageService.deleteEvent(Integer.parseInt(eventId[i]));
			}

		} catch (Exception e) {
			logger.debug("[Exception :]" + e.getMessage());
			return new ModelAndView("bug");
		}

		logger.debug("{delete event complete }");
		return new ModelAndView(new RedirectView("event-list.html"));

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
