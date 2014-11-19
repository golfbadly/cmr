package com.xplink.random_cm.servlets;

import java.io.IOException;
//import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.xplink.random_cm.managements.SendMailManager;

//Comment on 4/11/57

//import com.xplink.random_cm.service.EventManageService;

//import com.restfb.DefaultFacebookClient;
//import com.restfb.FacebookClient;
//import com.restfb.Parameter;
//import com.restfb.types.FacebookType;

public class SendmailServlet implements Controller {

	private static final Logger logger = LogManager
			.getLogger(SendmailServlet.class);

	/*
	 * private SymmetricCipher symmetricCipher; private GetMissionManager
	 * getMissionManager; private GetlistManager getlistManager;
	 */

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		logger.debug("[IN SendmailServlet]");

		HttpSession sessionSet = request.getSession();
		
		// Comment on 4/11/57
		// String eventId = request.getParameter("eventId");
		
		int eventId = (Integer) sessionSet.getAttribute("eventId");
		logger.debug("[eventId :]" + eventId);

		String mailTo = request.getParameter("mailTo");
		logger.debug("[mail to :]" + mailTo);

		String subjectMail = request.getParameter("subject");
		String textMail = request.getParameter("text");
		String textSend = textMail + "\n Accept event. \n "
				+ "http://www.goobre.com/cmr/CheckMember.html"
				+ "?checkMemberEmail=" + mailTo + "&checkMemberEventId="
				+ eventId;

		//String email = request.getParameter("email");
		//logger.debug("[mail from :]" + email);

		logger.debug("SendmailServlet: Mail sending.");
		try {

			SendMailManager.sendMail("goobre.bkk@gmail.com", "275-4377",
					"smtp.gmail.com", "465", "true", "true", true,
					"javax.net.ssl.SSLSocketFactory", "false", mailTo,
					subjectMail, textSend);

			logger.debug("SendmailServlet: send mail complete.");

			return new ModelAndView(new RedirectView("event-list.html"));

		} catch (Exception e) {
			logger.debug("[Exception :]" + e.getMessage());
			return new ModelAndView("bug");
		}

		
		// Comment on 4/11/57
		
		/*
		 * try {
		 * 
		 * logger.debug("[--before sent mail--]");
		 * 
		 * ArrayList<MemberBean> members =
		 * (ArrayList<MemberBean>)getlistManager.
		 * getMember(Integer.parseInt(eventId)); logger.debug("[members :]"+
		 * members);
		 * 
		 * if(members.size()!=0){
		 * 
		 * for(int i=0;i<members.size();i++) { MemberBean member =
		 * (MemberBean)members.get(i); String mailTo = member.getEmail(); int
		 * memberid = member.getMemberid(); logger.debug("[memberId :]"+
		 * memberid); logger.debug("[mail to :]"+ mailTo);
		 * 
		 * KeywordBean keyw =
		 * getMissionManager.getKeyword(memberid,Integer.parseInt(eventId));
		 * logger.debug("[key :]"+ keyw);
		 * 
		 * String sendKey = keyw.getOutkeyword(); logger.debug("[sendKey :]"+
		 * sendKey);
		 * 
		 * String keyout = symmetricCipher.decryptString(sendKey);
		 * logger.debug("[decryptString] keyword : "+keyout);
		 * logger.debug("[SendmailServlet] keyword to send mail : "+keyout);
		 * SendMailManager
		 * .sendMail("goobre.bkk@gmail.com","275-4377","smtp.gmail.com"
		 * ,"465","true"
		 * ,"true",true,"javax.net.ssl.SSLSocketFactory","false",mailTo
		 * ,"Receive keyword for party christmas"
		 * ,"Your keyword is [ "+keyout+" ] Please keep it be a secret."); }
		 * 
		 * //post on wall when send keyword complete. String accessToken =
		 * (String) session.getAttribute("accessToken"); String memberName =
		 * request.getParameter("memberName"); String eventName =
		 * request.getParameter("eventName"); logger.debug("[memberName :]"+
		 * memberName); logger.debug("[eventName :]"+eventName);
		 * 
		 * FacebookClient facebookClient = new
		 * DefaultFacebookClient(accessToken); FacebookType
		 * publishMessageResponse = facebookClient.publish("me/feed",
		 * FacebookType.class, Parameter.with("message",
		 * memberName+" has sented keyword of "
		 * +eventName+" event to friend e-mail "));
		 * 
		 * }
		 * 
		 * logger.debug("[OUT SendmailServlet]"); //return new ModelAndView(new
		 * RedirectView("Getlist.html?eventDetailId="+eventDetailId)); return
		 * new ModelAndView(new RedirectView("event-list.html")); } catch
		 * (Exception e) { logger.debug("[Exception :]"+e.getMessage()); return
		 * new ModelAndView("bug"); }
		 */

	}

}
