package com.xplink.random_cm.servlets;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

public class EventAddServlet implements Controller {
	private static final Logger logger = Logger
			.getLogger(EventAddServlet.class);
	private EventManageService eventManageService;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.debug("[IN EventAddServlet ]");

		HttpSession session = request.getSession();

		int eventId = 0;

		String eventStatus = request.getParameter("event");
		logger.debug("[eventStatus :]" + eventStatus);
		if (eventStatus == null) {
			eventStatus = "no";
			logger.debug("[SeteventStatus :]" + eventStatus);
		}

		if (eventStatus.equals("no")) {
			logger.debug("[receive eventStatus no]");
			return new ModelAndView("addEvent");
		} else {

			logger.debug("[recive eventStatus ]");

			String eventName = request.getParameter("eventName");

			logger.debug("[event name :]" + eventName);

			// update date
			String data = "";
			try {
				Locale locale = new Locale("en", "US");
				SimpleDateFormat sdfUpdated = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss", locale);
				String updateDate = sdfUpdated.format(new Date());
				data = updateDate;

				logger.debug("[set value to eventdata]");

				MemberBean member = (MemberBean) session.getAttribute("member");

				EventBean eventdata = new EventBean();

				eventdata.setEventName(eventName);
				eventdata.setCreateBy(member.getMemberid() + "");
				eventdata.setCreateDate(StringToTimeStamp(data));

				logger.debug("[--- create event ---]");

				String email = member.getEmail();
				logger.debug("[mail from :]" + email);
				eventManageService.create(eventdata, email);

				eventId = eventManageService.getLastEventId(member
						.getMemberid());
				eventdata.setId(eventId);

				request.setAttribute("eventdata", eventdata);

			} catch (Exception e) {
				logger.error("Can't get updated date : ", e);
				return new ModelAndView("bug");
			}

			logger.debug("[OUT EventAddServlet ]");
		}

		return new ModelAndView(new RedirectView(
				"GetEventToedit.html?event=no&eventId=" + eventId));

	}

	public Timestamp StringToTimeStamp(String date) throws ParseException {
		date = date + " 00:00:00 ";
		SimpleDateFormat setFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date dateTime = setFormat.parse(date);
		java.sql.Timestamp timeDate = new java.sql.Timestamp(dateTime.getTime());
		return timeDate;
	}

	public EventManageService getEventManageService() {
		return eventManageService;
	}

	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}

}
