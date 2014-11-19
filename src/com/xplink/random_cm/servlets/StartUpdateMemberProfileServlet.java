package com.xplink.random_cm.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import com.xplink.random_cm.dataencryption.SymmetricCipher;
import com.xplink.random_cm.datamodel.KeywordBean;
import com.xplink.random_cm.datamodel.MemberBean;
import com.xplink.random_cm.managements.GetMemberDetailManager;

public class StartUpdateMemberProfileServlet implements Controller {
	
	   private SymmetricCipher symmetricCipher;
	   private GetMemberDetailManager getMemberDetailManager;
	   
	private static final Logger logger = LogManager.getLogger(StartUpdateMemberProfileServlet.class);

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		logger.debug("[IN StartUpdateMemberProfileServlet]");
	    HttpSession session = request.getSession();
		String username = request.getParameter("username");
		logger.debug("[username :]"+username);
		ModelAndView modelAndView = new ModelAndView("UpdateProfilePage");
		
		KeywordBean kb = (KeywordBean) request.getAttribute("keyword");
		String eventDetailId = request.getParameter("eventDetailId");
		request.setAttribute("eventDetailId",eventDetailId);

		try {
				MemberBean data = getMemberDetailManager.getMember(username);	
				data.setPassword(symmetricCipher.decryptString(data.getPassword()));
				session.setAttribute("member", data);
			
				
			}catch(Exception ex){
				logger.debug("[Exception :]"+ex.getMessage());
				return new ModelAndView("bug");
			}

		
		logger.debug("[OUT StartUpdateMemberProfileServlet]");
		return modelAndView;

	}
	
	   		public SymmetricCipher getSymmetricCipher() {
			return symmetricCipher;
			}
			
			public void setSymmetricCipher(SymmetricCipher symmetricCipher) {
				this.symmetricCipher = symmetricCipher;
			}

			public GetMemberDetailManager getGetMemberDetailManager() {
				return getMemberDetailManager;
			}

			public void setGetMemberDetailManager(
					GetMemberDetailManager getMemberDetailManager) {
				this.getMemberDetailManager = getMemberDetailManager;
			}
			
			

}
