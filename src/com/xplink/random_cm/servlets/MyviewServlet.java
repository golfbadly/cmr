package com.xplink.random_cm.servlets;

import java.security.GeneralSecurityException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.xplink.random_cm.dataencryption.SymmetricCipher;
import com.xplink.random_cm.datamodel.EventBean;
import com.xplink.random_cm.datamodel.KeywordBean;
import com.xplink.random_cm.managements.GetKeywordManager;
import com.xplink.random_cm.managements.GetMissionManager;
import com.xplink.random_cm.service.EventManageService;

public class MyviewServlet implements Controller {

	private static final Logger logger = LogManager
			.getLogger(MyviewServlet.class);
	private SymmetricCipher symmetricCipher;
	private GetKeywordManager getKeywordManager;
	private GetMissionManager getMissionManager;
	private EventManageService eventManageService;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug("[In MyviewServlet]");
		HttpSession session = request.getSession();
		String eventDetailId = "";

		try {
			if (request.getParameter("eventDetailId") != null) {

				eventDetailId = request.getParameter("eventDetailId");
				request.setAttribute("eventDetailId", eventDetailId);

			}

			logger.debug("[eventDetailId]" + eventDetailId);

			String memberId = request.getParameter("memberId");
			String eventId = request.getParameter("eventId");

			EventBean eventData = new EventBean();
			eventData = eventManageService.getEventById(Integer
					.valueOf(eventId));

			String eventName = eventData.getEventName();
			request.setAttribute("randomed", eventData.getRandomed());

			if (eventName.equals("null") || eventName != null) {
				session.setAttribute("eventName", eventName);
			}

			if (eventDetailId != "null" && eventDetailId != null) {

				int eventdetailId = Integer.valueOf(eventDetailId);

				// Keyword IN
				KeywordBean keyword = getKeywordManager
						.getKeywordBean(eventdetailId);
				logger.debug("[Keyword IN :]" + keyword);

				if (keyword.getInkeyword() != null
						&& !keyword.getInkeyword().isEmpty()) {
					keyword.setInkeyword(symmetricCipher.decryptString(keyword
							.getInkeyword()));
					logger.debug("Keyword IN Decrypt: " + keyword);
				}

				request.setAttribute("keyword", keyword);

				// Keyword OUT
				KeywordBean keyw = null;
				keyw = getMissionManager.getKeyword(Integer.valueOf(memberId),
						Integer.valueOf(eventId));
				logger.debug("[Keyword OUT :]" + keyw);

				String sendKey = keyw.getOutkeyword();
				logger.debug("[sendKey :]" + sendKey);

				String keyout = null;

				if (!"".equals(sendKey) && sendKey != null) {
					try {
						logger.debug("[sendKeyNotnull :]" + sendKey);
						keyout = symmetricCipher.decryptString(sendKey);
						logger.debug("[KeyoutNotnull :]" + keyout);
					} catch (GeneralSecurityException e) {
						e.printStackTrace();
						logger.debug("DecryptString Error!");
					}
				} else {
					keyout = "";
					logger.debug("[Keyout :]" + keyout);
				}
				request.setAttribute("keyout", keyout);
				logger.debug("[Out MyviewSerlet]");
			}

		} catch (NumberFormatException e) {
			logger.debug("[NumberFormatException :]" + e.getMessage());
			e.printStackTrace();
			return new ModelAndView("bug");
		} catch (Exception e) {
			logger.debug("[Exception :]" + e.getMessage());
			e.printStackTrace();
			return new ModelAndView("bug");
		}

		return new ModelAndView("MyView");
	}

	public SymmetricCipher getSymmetricCipher() {
		return symmetricCipher;
	}

	public void setSymmetricCipher(SymmetricCipher symmetricCipher) {
		this.symmetricCipher = symmetricCipher;
	}

	public GetKeywordManager getGetKeywordManager() {
		return getKeywordManager;
	}

	public void setGetKeywordManager(GetKeywordManager getKeywordManager) {
		this.getKeywordManager = getKeywordManager;
	}

	public GetMissionManager getGetMissionManager() {
		return getMissionManager;
	}

	public void setGetMissionManager(GetMissionManager getMissionManager) {
		this.getMissionManager = getMissionManager;
	}

	public EventManageService getEventManageService() {
		return eventManageService;
	}

	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}

}
