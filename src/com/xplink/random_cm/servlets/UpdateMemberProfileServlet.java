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
import org.springframework.web.servlet.view.RedirectView;
import com.xplink.random_cm.dataencryption.SymmetricCipher;
import com.xplink.random_cm.datamodel.MemberBean;
import com.xplink.random_cm.managements.GetMemberDetailManager;
import com.xplink.random_cm.managements.UpdateMemberProfileManager;

public class UpdateMemberProfileServlet implements Controller {
	
	   private SymmetricCipher symmetricCipher;
	   private static final Logger logger = LogManager.getLogger(UpdateMemberProfileServlet.class);
       private	GetMemberDetailManager getMemberDetailManager ;
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		logger.debug("[IN UpdateMemberProfileServlet]");

		UpdateMemberProfileManager updatemember = new UpdateMemberProfileManager();
	    HttpSession session = request.getSession();
	    
	    String eventDetailId = request.getParameter("eventDetailId");
	    
	    logger.debug("[eventDetailId :]"+eventDetailId);

		try {
				MemberBean member = (MemberBean) session.getAttribute("member");
				
				logger.debug("[UpdateMemberProfileServlet] memberid : "+member.getMemberid());
				logger.debug("[UpdateMemberProfileServlet] name : "+request.getParameter("name"));
				logger.debug("[UpdateMemberProfileServlet] surname : "+request.getParameter("surname"));
				logger.debug("[UpdateMemberProfileServlet] emails : "+request.getParameter("emails"));

				updatemember.updateMemberProfile(member.getMemberid(), 
						  request.getParameter("name"), 
						  request.getParameter("surname"), 
						  request.getParameter("emails")); 					  
				
				MemberBean memberafterupdate = new MemberBean();
				memberafterupdate = getMemberDetailManager.getMemberByID(member.getMemberid());
				
				memberafterupdate.setPassword(symmetricCipher.decryptString(memberafterupdate.getPassword()));
				session.setAttribute("member", memberafterupdate);
				
				logger.debug("[OUT UpdateMemberProfileServlet]");

		} catch (Exception e) {
			logger.debug("[Exception :]"+e.getMessage());
			return new ModelAndView("bug");
		} 

		return new ModelAndView(new RedirectView("MyView.html?eventDetailId="+eventDetailId));
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
