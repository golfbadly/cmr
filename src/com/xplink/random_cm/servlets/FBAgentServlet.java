package com.xplink.random_cm.servlets;

import java.security.GeneralSecurityException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import com.xplink.random_cm.dataencryption.SymmetricCipher;
import com.xplink.random_cm.datamodel.KeywordBean;
import com.xplink.random_cm.datamodel.MemberBean;
import com.xplink.random_cm.service.EventAcceptService;
import com.xplink.random_cm.service.EventManageService;
import com.xplink.random_cm.service.LoginService;
import com.xplink.random_cm.service.RegisterService;

public class FBAgentServlet extends AbstractController {
	  private static final Logger logger = Logger.getLogger(FBAgentServlet.class);
	  private SymmetricCipher symmetricCipher;
	  private LoginService loginService;
	  private RegisterService registerService; 
	  private EventManageService eventManageService;
	  private EventAcceptService eventAcceptService;
	  
@SuppressWarnings("finally")
protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
    	 logger.debug("[IN FBAgent]");	
    	
      	 HttpSession session = request.getSession();

    	 ModelAndView mav = new ModelAndView(new RedirectView("agent.html"));
    	
    	String redirectUrl = (String)request.getParameter("redirectUrl");
		 logger.debug("[redirctUrl:]"+redirectUrl);
		 
		 String request_ids = (String)session.getAttribute("request_ids");
		 
		// EventAcceptService eventAcceptService = new EventAcceptService() ;

     if(redirectUrl!=null ){ 
    		 logger.debug("[Autorization page]");
    		 request.setAttribute("redirectUrl", redirectUrl);
    		 
    	     return new ModelAndView("agent");
               
   	} else{
    	    logger.debug("[IN user Allowed ]");
    	    
    	    String accessToken = (String)session.getAttribute("accessToken");
    	    
           	FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
           	logger.debug("[accessToken :]"+accessToken);
    		
    		User user = facebookClient.fetchObject("me", User.class);
    				  
    	  String userName =	user.getName();
    	  String pass = user.getId();
    	  logger.debug("[username :]"+userName);
    	  logger.debug("[pass :]"+pass);
    	 
    	  
    	  String passWord = symmetricCipher.encryptString(user.getId());    
    	  logger.debug("[password encrypt :]"+passWord);
    	  
    	  Boolean loginPassed =  loginService.validate(userName, passWord);   	    					
    	  
		    	if(loginPassed){
		    		logger.debug("[IN login passed ]");
		    		 
		    		MemberBean member =loginService.getMember(userName);
					member.setPassword(symmetricCipher.decryptString(member.getPassword()));
					session.setAttribute("member", member);
				
					  logger.debug("[ request_ids :]"+request_ids );					  					  
					
					  if(request_ids!="null"&&request_ids!=null){
						  
						  logger.debug("[-received request_ids-]" );						  						  						
						  
						  int memberId = member.getMemberid();
						  logger.debug("[memberId :]"+memberId);			  
						  String email = member.getEmail();
						  logger.debug("Email :"+email);
			  			
			  			 int dataEventId = Integer.parseInt(eventAcceptService.eventDecode(request_ids)); 
			  			 eventManageService.addEventDetailFB(dataEventId, memberId, email);					  				 			  		 	    
						  
						 int eventDetailId = eventManageService.getEventDetailId(dataEventId, memberId);
						  
						  registerService.addSpace(eventDetailId);
						  logger.debug("[eventDId :]"+eventDetailId);	
			  		 	     session.setAttribute("eventDetailId", eventDetailId);
			  		 	     
			  		 	     request.setAttribute("eventDId", eventDetailId); 
			  		 	     return new ModelAndView(new RedirectView("event-list.html?eventDId="+eventDetailId));
			  		 	     
			  		 	     
					    }else{
					
							logger.debug("[OUT login passed ]");    		
							return new ModelAndView(new RedirectView("event-list.html"));
							
					  }	
		    	}else{
		    		
		    		// if login not pass
		    		
		   	    	logger.debug("[IN:RegisterService]");
		  	        
		  			//KeywordBean kb = new KeywordBean();
		  		
		  			String resultregister = "false";
		  			
		  			//get data from facebook	 
		  			String name = user.getName();
		  			String surname = user.getLastName();
		  			String email = user.getEmail() ;
		  			String username= user.getName();
		  			String status="0";
		   			String password = symmetricCipher.encryptString(user.getId());
		  			
		   			logger.debug("[name :]"+name);   
		  			logger.debug("[surname :]"+surname);   
		  			logger.debug("[email :]"+email);   
		  			logger.debug("[username :]"+username);   
		  			logger.debug("[password :]"+password);   
		  			logger.debug("[status :]"+status); 
		   			
		  			//set data to bean
		  			MemberBean memberBean = new MemberBean();
		  			memberBean.setName(name);
		  			memberBean.setSurname(surname);
		  			memberBean.setEmail(email);
		  			memberBean.setUsername(username);
		  			memberBean.setPassword(password);
		  			memberBean.setStatus(status);
		  			
		  		  		  			
		  			try {
		
		  				//regis.register(memberBean);
		  				registerService.registerFB(name,surname,email,username,password,status);
		  				MemberBean newMb = new MemberBean();			
		  				newMb = registerService.getMember(memberBean.getUsername());
		  				newMb.setPassword(symmetricCipher.decryptString(newMb.getPassword()));
		  				session.setAttribute("member", newMb);
		  				int eventDetailId = 0;
	  				    logger.debug("[request_ids :]"+request_ids);
	  					  						  						  				
		  				if(request_ids!="null"&&request_ids!=null){
		  					logger.debug("regiscomplete!");
		  					// 12/11/57
			  				 int dataEventId = Integer.parseInt(eventAcceptService.eventDecode(request_ids)); 
							  
			  				 int memberId = newMb.getMemberid();
			  				 logger.debug("MemberID :"+memberId);
			  				 String emailfb = newMb.getEmail();
			  				 logger.debug("Email :"+emailfb);
			  				 
			  				eventManageService.addEventDetailFB(dataEventId, memberId, emailfb);	
			  				 
			  				 eventDetailId = eventManageService.getEventDetailId(dataEventId, memberId);
			  				 registerService.addSpace(eventDetailId);
			  		 	     
			  		 	     session.setAttribute("eventDetailId", ""+eventDetailId);
			  		 	 	 		 	    
		  				 }
	  				
		  				logger.debug("[OUT:RegisterServlet]");
		  			    return new ModelAndView(new RedirectView("event-list.html"));
		  			
		  			} catch (SQLException e) {
		  				logger.debug("[SQLException :]"+e.getMessage());
		  				e.printStackTrace();
		  				return new ModelAndView("bug");
		  			} catch (GeneralSecurityException ex) {
		  				logger.debug("[SecurityException :]"+ex.getMessage());
		  				ex.printStackTrace();
		  				return new ModelAndView("bug");
		  			}finally{		  			
		  			request.setAttribute("result", resultregister);
		    	}
		    	} //end if log in no pass
    		    		
    		
    	}// end if else redirect Url
    	
    }//end method handlerequestIntenal


public LoginService getLoginService() {
	return loginService;
}


public void setLoginService(LoginService loginService) {
	this.loginService = loginService;
}


public SymmetricCipher getSymmetricCipher() {
	return symmetricCipher;
}

public void setSymmetricCipher(SymmetricCipher symmetricCipher) {
	this.symmetricCipher = symmetricCipher;
}


public RegisterService getRegisterService() {
	return registerService;
}


public EventManageService getEventManageService() {
	return eventManageService;
}

public void setRegisterService(RegisterService registerService) {
	this.registerService = registerService;
}


public void setEventManageService(EventManageService eventManageService) {
	this.eventManageService = eventManageService;
}


public EventAcceptService getEventAcceptService() {
	return eventAcceptService;
}


public void setEventAcceptService(EventAcceptService eventAcceptService) {
	this.eventAcceptService = eventAcceptService;
}

}

