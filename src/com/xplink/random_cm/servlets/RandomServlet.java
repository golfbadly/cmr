package com.xplink.random_cm.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.xplink.random_cm.dataencryption.SymmetricCipher;
import com.xplink.random_cm.datamodel.EventBean;
import com.xplink.random_cm.datamodel.KeywordBean;
import com.xplink.random_cm.datamodel.MemberBean;
import com.xplink.random_cm.managements.GetListKeywordManager;
import com.xplink.random_cm.managements.GetMissionManager;
import com.xplink.random_cm.managements.GetlistManager;
import com.xplink.random_cm.managements.SendMailManager;
import com.xplink.random_cm.random.RandomKeyWord;
import com.xplink.random_cm.service.EventManageService;
import com.xplink.random_cm.service.PostFBWallService;

public class RandomServlet implements Controller {

	   ArrayList<MemberBean> list;
	   private SymmetricCipher symmetricCipher;
	   private GetListKeywordManager getListKeywordManager;
	   private GetMissionManager getMissionManager ;
	   private GetlistManager getlistManager;
	   private static final Logger logger = Logger.getLogger(RandomServlet.class);
	   private RandomKeyWord randomKeyWord;	
	   private EventManageService eventManageService; 
	   private PostFBWallService postFBWallService;
	

	private String fbCanvasPage ;
	
public ModelAndView handleRequest(HttpServletRequest request,
			   HttpServletResponse response) throws ServletException, IOException {
		
		 logger.debug("[IN RandomServlet]");		 
					 
		 ModelAndView modelAndView = new ModelAndView(new RedirectView("event-list.html"));
		 
	try {
		HttpSession session = request.getSession();
			
		String eventId = request.getParameter("eventId");	

		//random keyword.

		ArrayList<KeywordBean> keywordList;
		
		 logger.debug("[eventId :]"+eventId );
		 request.setAttribute("eventId",eventId );
		
		keywordList = getListKeywordManager.getListKeyword(Integer.parseInt(eventId));
		randomKeyWord.randomKeyword(keywordList,Integer.parseInt(eventId));				
				
		
			
		//================= sent mail ===========================
			
			logger.debug("[--Start sent mail--]");
			
			ArrayList<MemberBean> members =  (ArrayList<MemberBean>)getlistManager.getMember(Integer.parseInt(eventId));
			logger.debug("[members :]"+ members);
			logger.debug("[members size:]"+ members.size());
			
				for(int i=0;i<members.size();i++)
				{
					MemberBean member =  (MemberBean)members.get(i);
					String mailTo = member.getEmail();
					int memberid = member.getMemberid();
					logger.debug("[memberId :]"+ memberid);
					logger.debug("[mail to :]"+ mailTo);
					
					KeywordBean keyw = getMissionManager.getKeyword(memberid,Integer.parseInt(eventId));
					logger.debug("[key :]"+ keyw);
					
					String sendKey = keyw.getOutkeyword();
					logger.debug("[sendKey :]"+ sendKey);
					
					String keyout = symmetricCipher.decryptString(sendKey);
					logger.debug("[decryptString] keyword : "+keyout);
					logger.debug("[SendmailServlet] keyword to send mail : "+keyout);
					
					SendMailManager.sendMail("goobre.bkk@gmail.com","275-4377","smtp.gmail.com",
							"465","true","true",true,"javax.net.ssl.SSLSocketFactory","false",
							mailTo,"Receive keyword for party christmas","Your keyword is [ "+keyout+" ] "
									+ "Please keep it be a secret.");
					
				}
				logger.debug("[--END sent mail--]");
				
			
				//================= PostFB ===========================
				
				String accessToken = (String) session.getAttribute("accessToken");
				String memberName = request.getParameter("memberName");
				String eventName = request.getParameter("eventName");
				String memberId = request.getParameter("memberId");
				logger.debug("[memberName :]"+ memberName);
				logger.debug("[eventName :]"+eventName);
				
				MemberBean memberBean = (MemberBean) session.getAttribute("member");
				String fb = memberBean.getFB();
				logger.debug("FB status: "+fb);
				
				if(fb.equals("T")){
					logger.debug("PostFB");
					String dataPost = URLEncoder.encode("access_token", "UTF-8") + "=" + URLEncoder.encode(accessToken, "UTF-8");
					dataPost += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(memberName+" has sented keyword of "+eventName+" on Christmas Random", "UTF-8");
					dataPost += "&" + URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(memberName+" has sented keyword of "+eventName+" on Christmas Random", "UTF-8");
					dataPost += "&" + URLEncoder.encode("caption", "UTF-8") + "=" + URLEncoder.encode("On Christmas Random", "UTF-8");
					dataPost += "&" + URLEncoder.encode("link", "UTF-8") + "=" + URLEncoder.encode(fbCanvasPage, "UTF-8");
					dataPost += "&" + URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode("Christmas Keyword Random for Gift Exchange Game.", "UTF-8");
					dataPost += "&" + URLEncoder.encode("picture", "UTF-8") + "=" + URLEncoder.encode("https://goobre.com/cmr/pics/gift.gif", "UTF-8");

					postFBWallService.post(dataPost);
				}
			    
			logger.debug("[OUT SendmailServlet]");
		
			EventBean event = eventManageService.getEventById(Integer.parseInt(eventId));
		    event.setRandomed("t");
		    java.util.Date date= new java.util.Date();
		    event.setUpdateDate(new Timestamp(date.getTime()));
		    eventManageService.updateEvent(event, Integer.parseInt(eventId), Integer.parseInt(memberId));
			
			
		} catch (Exception e) {
			logger.error("[Exception :]"+e.getMessage(),e);
			return new ModelAndView("bug");
		}
			
		 logger.debug("[OUT RandomServlet]");
	
		return modelAndView;		

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

	public GetMissionManager getGetMissionManager() {
		return getMissionManager;
	}

	public void setGetMissionManager(GetMissionManager getMissionManager) {
		this.getMissionManager = getMissionManager;
	}

	public GetListKeywordManager getGetListKeywordManager() {
		return getListKeywordManager;
	}

	public void setGetListKeywordManager(GetListKeywordManager getListKeywordManager) {
		this.getListKeywordManager = getListKeywordManager;
	}

	public SymmetricCipher getSymmetricCipher() {
		return symmetricCipher;
	}

	public void setSymmetricCipher(SymmetricCipher symmetricCipher) {
		this.symmetricCipher = symmetricCipher;
	}


	public RandomKeyWord getRandomKeyWord() {
		return randomKeyWord;
	}


	public void setRandomKeyWord(RandomKeyWord randomKeyWord) {
		this.randomKeyWord = randomKeyWord;
	}  
	public String getFbCanvasPage() {
		return fbCanvasPage;
	}

	public void setFbCanvasPage(String fbCanvasPage) {
		this.fbCanvasPage = fbCanvasPage;
	}

	public PostFBWallService getPostFBWallService() {
		return postFBWallService;
	}

	public void setPostFBWallService(PostFBWallService postFBWallService) {
		this.postFBWallService = postFBWallService;
	}
	
}
