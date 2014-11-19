package com.xplink.random_cm.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.xplink.random_cm.datamodel.MemberBean;
import com.xplink.random_cm.service.EventManageService;
import com.xplink.random_cm.service.LoginService;

public class CheckMemberServlet implements Controller {

	private static final Logger logger = Logger
			.getLogger(CheckMemberServlet.class);
	private LoginService loginService;
	private EventManageService eventManageService;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.debug("IN CheckMemberServlet");
		HttpSession session = request.getSession();

		String email = request.getParameter("checkMemberEmail");
		logger.debug("IN CheckMemberServlet: Email " + email);
		int eventId = Integer.parseInt(request
				.getParameter("checkMemberEventId"));
		logger.debug("IN CheckMemberServlet: EventID " + eventId);

		session.setAttribute("checkMemberEmail", email);
		session.setAttribute("checkMemberEventId", eventId);

		logger.debug("IN CheckMemberServlet: Checking...");

		boolean LoginStatus;
		session.setAttribute("registerStatus", false);
		
		boolean check = loginService.CheckMemberByEmail(email);
		logger.debug("IN CheckMemberServlet: Checking result " + check);

		if (check == true) {
			logger.debug("IN CheckMemberServlet: Meet member");

			MemberBean member = loginService.getMemberByEmail(email);
			int memberId = member.getMemberid();
			logger.debug("IN CheckMemberServlet: MemberId: " + memberId);

			eventManageService.addEventDetailFromEmail(eventId, email, memberId);
			
			logger.debug("IN CheckMemberServlet: Checking event ");
			
			try{
					LoginStatus = (Boolean) session.getAttribute("LoginStatus");
					if (LoginStatus == true) {
						logger.debug("IN CheckMemberServlet: Login status "+LoginStatus);
				
						String LoginUsername = (String) session.getAttribute("LoginUsername");
						String LoginPassword = (String) session.getAttribute("LoginPassword");
				
						logger.debug("IN CheckMemberServlet: Set attribute username "+LoginUsername);
						logger.debug("IN CheckMemberServlet: Set attribute password "+LoginPassword);
				
						return new ModelAndView(new RedirectView("Login.html"
								+ "?username="+LoginUsername+"&password="+LoginPassword));
					} else {
						logger.debug("IN CheckMemberServlet: Login status "+LoginStatus);
						return new ModelAndView(new RedirectView("CallLogin.html"));
					}
			}catch(Exception e){
				logger.debug("IN CheckMemberServlet: Call login page pass link from InviteEmail");
				return new ModelAndView(new RedirectView("CallLogin.html"));
			}

		} else {
			session.setAttribute("registerStatus", true);
			logger.debug("IN CheckMemberServlet: Not meet member");
			return new ModelAndView(new RedirectView("CallRegister.html"));

		}

	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public EventManageService getEventManageService() {
		return eventManageService;
	}

	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}

}
