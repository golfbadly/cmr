package com.xplink.random_cm.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.xplink.random_cm.dataencryption.SymmetricCipher;
import com.xplink.random_cm.datamodel.MemberBean;
import com.xplink.random_cm.managements.InputKeywordManager;
import com.xplink.random_cm.managements.UpdateStatusManager;
import com.xplink.random_cm.service.PostFBWallService;

public class InputkeywordServlet implements Controller {

	private static final Logger logger = LogManager
			.getLogger(InputkeywordServlet.class);

	private SymmetricCipher symmetricCipher;
	private UpdateStatusManager updateStatusManager;
	private InputKeywordManager inputKeywordManager;
	private PostFBWallService postFBWallService;
	private String fbCanvasPage;
	MemberBean memberbean = new MemberBean();

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		logger.debug("[IN: InputKeywordServlet]");

		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("member");
		String eventDetailId = (String) request.getParameter("eventDetailId");
		String memberId = request.getParameter("memberId");
		String eventId = request.getParameter("eventId");

		logger.debug("eventDetailId:" + eventDetailId);
		logger.debug("memberId:" + memberId);
		logger.debug("eventId:" + eventId);

		try {

			String keyword = request.getParameter("keyword");
			logger.debug("get keyword is : " + keyword);
			String kw = symmetricCipher.encryptString(keyword);
			logger.debug("encryptString is : " + kw);

			logger.debug(memberBean.getMemberid());
			updateStatusManager.UpdateStatus(memberBean.getMemberid());
			
			//inputKeywordManager.inputKeyword(Integer.parseInt(eventDetailId),kw);
			Boolean insertKey = inputKeywordManager.inputKeyword(Integer.parseInt(eventDetailId), kw);
			logger.debug("Input keyword complete!");

			// postFB user wall when create event
			
			String fb = memberBean.getFB();
			logger.debug("FB status: "+fb);
			
			if(fb.equals("T")){
				logger.debug("PostFB");
				String accessToken = (String) session.getAttribute("accessToken");
				String eventName = (String) request.getParameter("eventName");
				
	        
				if (insertKey == true){  
					String dataPost = URLEncoder.encode("access_token", "UTF-8") + "=" + URLEncoder.encode(accessToken, "UTF-8");
					dataPost += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(memberBean.getName()+" has created keyword for "+encodingString(eventName)+" on Christmas Random", "UTF-8");
					dataPost += "&" + URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(memberBean.getName()+" has created keyword for "+encodingString(eventName)+" on Christmas Random", "UTF-8");
					dataPost += "&" + URLEncoder.encode("link", "UTF-8") + "=" + URLEncoder.encode(fbCanvasPage+"event-list.html", "UTF-8");
					dataPost += "&" + URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode("Christmas Keyword Random for Gift Exchange Game.", "UTF-8");
					dataPost += "&" + URLEncoder.encode("picture", "UTF-8") + "=" + URLEncoder.encode("https://goobre.com/cmr/pics/gift.gif", "UTF-8");
	    		
					postFBWallService.post(dataPost);       
				}
			}

			logger.debug("[OUT: InputKeywordServlet]");

			ModelAndView mav = new ModelAndView("getAjaxResult");
			mav.addObject("ajaxResult", keyword);
			return mav;

		} catch (Exception e) {
			logger.error("[Exception  :]" + e.getMessage(), e);
			return new ModelAndView("bug");
		}

	}

	private String encodingString(String text) throws UnsupportedEncodingException{
		 byte[] eventDesc = text.getBytes("ISO8859_1");
		 text = new String(eventDesc,"UTF-8");
	   	 
	   	 return text;
	}

	public SymmetricCipher getSymmetricCipher() {
		return symmetricCipher;
	}

	public void setSymmetricCipher(SymmetricCipher symmetricCipher) {
		this.symmetricCipher = symmetricCipher;
	}

	public UpdateStatusManager getUpdateStatusManager() {
		return updateStatusManager;
	}

	public InputKeywordManager getInputKeywordManager() {
		return inputKeywordManager;
	}

	public void setUpdateStatusManager(UpdateStatusManager updateStatusManager) {
		this.updateStatusManager = updateStatusManager;
	}

	public void setInputKeywordManager(InputKeywordManager inputKeywordManager) {
		this.inputKeywordManager = inputKeywordManager;
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