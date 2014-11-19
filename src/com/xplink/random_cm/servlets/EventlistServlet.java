package com.xplink.random_cm.servlets;

import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.xplink.random_cm.datamodel.EventBean;
import com.xplink.random_cm.datamodel.MemberBean;
import com.xplink.random_cm.service.EventManageService;
import com.xplink.random_cm.service.EventlistService;

//import com.xplink.random_cm.service.PostFBWallService;

public class EventlistServlet implements Controller {
	private static final Logger logger = Logger
			.getLogger(EventlistServlet.class);
	private EventlistService eventlistService;
	private EventManageService eventManageService;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug("[IN EventlistServlet]");
		ModelAndView mav = new ModelAndView("event-list");
		HttpSession session = request.getSession();
		MemberBean member = (MemberBean) session.getAttribute("member");
		
		// Comment on 4/11/57
		// PostFBWallService postFBWallService = new PostFBWallService();

		try {
			ArrayList<EventBean> eventlist = eventlistService.getEvent(
					member.getMemberid(), member.getEmail());
			logger.debug("[Member :]" + member.getMemberid());
			logger.debug("[eventlist :]" + eventlist);

			if (eventlist.isEmpty()) {
				logger.debug("[eventlist : null]");
				return new ModelAndView(new RedirectView("addEvent.html"));
			}

			EventBean event = new EventBean();
			List<String> dateStart = new ArrayList<String>();
			List<String> dateEnd = new ArrayList<String>();
			String startDate = "";
			logger.debug("1: " + eventlist.size());
			for (int i = 0; i < eventlist.size(); i++) {
				event = (EventBean) eventlist.get(i);
				logger.debug("2: " + event);

				startDate = new SimpleDateFormat("dd-MM-yyyy").format(event
						.getDateStart());
				dateStart.add(startDate);
				logger.debug("3: " + startDate);

				String endDate = new SimpleDateFormat("dd-MM-yyyy")
						.format(event.getDateEnd());
				dateEnd.add(endDate);
				logger.debug("4: " + endDate);
			}

			int eventId = event.getId();
			logger.debug("[eventID :]" + eventId);
			session.setAttribute("eventId", eventId);
			String email = member.getEmail();
			logger.debug("[email :]" + email);
			session.setAttribute("email", email);
			
			String name = member.getName();
			logger.debug("[Name :]" + name);
			String FB = member.getFB();
			logger.debug("[FB :]" + FB);
			
			// Comment on 4/11/57

			/*
			 * String eventDid = (String) request.getParameter("eventDId");
			 * if(eventDid != null){
			 * 
			 * Integer eventId =
			 * eventManageService.getEventId(Integer.parseInt(eventDid));
			 * logger.debug("[eventID :]"+eventId); EventBean eventModel =
			 * eventManageService.getEventById(eventId);
			 * 
			 * 
			 * String accessToken = (String)
			 * session.getAttribute("accessToken");
			 * 
			 * logger.debug("accessToken :"+accessToken);
			 * 
			 * String dataPost = URLEncoder.encode("access_token", "UTF-8") +
			 * "=" + URLEncoder.encode(accessToken, "UTF-8"); dataPost += "&" +
			 * URLEncoder.encode("name", "UTF-8") + "=" +
			 * URLEncoder.encode(member
			 * .getName()+" has received event for "+eventModel
			 * .getEventName()+" on Christmas Random", "UTF-8"); dataPost += "&"
			 * + URLEncoder.encode("message", "UTF-8") + "=" +
			 * URLEncoder.encode(
			 * member.getName()+" has received event for "+eventModel
			 * .getEventName()+" on Christmas Random", "UTF-8"); dataPost += "&"
			 * + URLEncoder.encode("caption", "UTF-8") + "=" +
			 * URLEncoder.encode("Start :"+startDate, "UTF-8"); dataPost += "&"
			 * + URLEncoder.encode("link", "UTF-8") + "=" +
			 * URLEncoder.encode("http://apps.facebook.com/christmasrandom/",
			 * "UTF-8"); dataPost += "&" + URLEncoder.encode("description",
			 * "UTF-8") + "=" +
			 * URLEncoder.encode("Christmas Keyword Random for Gift Exchange Game."
			 * , "UTF-8"); dataPost += "&" + URLEncoder.encode("picture",
			 * "UTF-8") + "=" +
			 * URLEncoder.encode("http://goobre.com/cmr/pics/gift.gif",
			 * "UTF-8");
			 * 
			 * postFBWallService.post(dataPost); }
			 */

			request.setAttribute("list", eventlist);
			request.setAttribute("dateStart", dateStart);
			request.setAttribute("dateEnd", dateEnd);

			logger.debug("[OUT EventlistServlet]");
			return mav;

		} catch (SQLException e) {
			logger.error("[SQLException :]" + e.getMessage(), e);
			return new ModelAndView("bug");
		} catch (UnsupportedEncodingException eu) {
			logger.error("[EncodingException eu :]" + eu.getMessage(), eu);
			return new ModelAndView("bug");
		} catch (Exception ex) {
			logger.error("[Exception ex :]" + ex.getMessage(), ex);
			return new ModelAndView("bug");
		}

	}

	public EventlistService getEventlistService() {
		return eventlistService;
	}

	public EventManageService getEventManageService() {
		return eventManageService;
	}

	public void setEventlistService(EventlistService eventlistService) {
		this.eventlistService = eventlistService;
	}

	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}

}
