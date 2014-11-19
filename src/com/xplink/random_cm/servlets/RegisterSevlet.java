package com.xplink.random_cm.servlets;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.xplink.random_cm.dataencryption.SymmetricCipher;
//import com.xplink.random_cm.datamodel.KeywordBean;
import com.xplink.random_cm.datamodel.MemberBean;
import com.xplink.random_cm.service.RegisterService;

 public class RegisterSevlet  implements Controller {
   static final long serialVersionUID = 1L;
      
   RegisterService registerService; 
   
   private SymmetricCipher symmetricCipher;
   
   private static final Logger logger = Logger.getLogger(SymmetricCipher.class);
     
public ModelAndView handleRequest(HttpServletRequest request,
		   HttpServletResponse response) throws ServletException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, Base64DecodingException 
   {
			//declaretion 
	     HttpSession session = request.getSession();
			
	    	logger.debug("IN:RegisterServlet");
		
			String resultregister = "false";
			
			//get data from page		 
			String name= request.getParameter("name1");
			String surname=request.getParameter("surname");
			String email=request.getParameter("email");
			String username=request.getParameter("usr");
			String status="0";
			
			// Comment on 4/11/57
			//System.out.println("password : "+request.getParameter("pwd"));
			
			String password=request.getParameter("pwd");
			String passwordEn=symmetricCipher.encryptString(request.getParameter("pwd"));
			
			//set data to bean
			MemberBean mb = new MemberBean();
			mb.setName(name);
			mb.setSurname(surname);
			mb.setEmail(email);
			mb.setUsername(username);
			mb.setPassword(passwordEn);
			mb.setStatus(status);
			
			try {

				registerService.register(name,surname,email,username,passwordEn,status);
				MemberBean newMb = new MemberBean();			
				newMb = registerService.getMember(mb.getUsername());
				newMb.setPassword(symmetricCipher.decryptString(newMb.getPassword()));
				
				logger.debug("RegisterServlet: MemberID "+newMb.getMemberid());
							
				logger.debug("OUT:RegisterServlet register success.");
				//session.setAttribute("registerStatus", false);
				
				boolean registerStatus = (Boolean) session.getAttribute("registerStatus");
				if(registerStatus == true){
					int eventId = (Integer) session.getAttribute("checkMemberEventId");
					logger.debug("RegisterServlet register status: "+registerStatus);
					logger.debug("RegisterServlet EventID: "+eventId);
					
					session.setAttribute("LoginStatus", true);
					session.setAttribute("LoginUsername", username);
					session.setAttribute("LoginPassword", password);
					
				return new ModelAndView(new RedirectView("CheckMember.html"
						+ "?checkMemberEmail="+email+"&checkMemberEventId="+eventId));
				}else{
					logger.debug("RegisterServlet register status: "+registerStatus);
				return new ModelAndView(new RedirectView("Login.html"
						+ "?username="+username+"&password="+password));
				}
				
			} catch (SQLException e) {				
				e.printStackTrace();
			} catch (GeneralSecurityException e) {
				e.printStackTrace();
			}catch(Exception ex ){
				logger.error("excepiton:"+ex.getMessage(),ex);
			}
			logger.debug("OUT:RegisterServlet register fail.");
			ModelAndView registererror = new ModelAndView("bug");
			request.setAttribute("result", resultregister);
			return registererror;
	}


public void addSpaceNewKeyword(int newKeyword) throws Exception{
	   
	   try {
		   registerService.addSpace(newKeyword);
	} catch (SQLException e) {
		logger.error("addSpaceNewKeyword exception:"+e.getMessage(),e);
		throw e;
	}
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

		public void setRegisterService(RegisterService registerService) {
			this.registerService = registerService;
		}
		
		

}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
