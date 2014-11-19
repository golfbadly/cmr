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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.xplink.random_cm.dataencryption.SymmetricCipher;
import com.xplink.random_cm.datamodel.MemberBean;
import com.xplink.random_cm.service.LoginService;

/**
 * Servlet implementation class for Servlet: LoginServlet
 * 
 */
public class LoginServlet implements Controller {
	private static final Logger logger = LogManager
			.getLogger(LoginServlet.class);
	private SymmetricCipher symmetricCipher;
	private LoginService loginService;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = request.getParameter("username");

		logger.debug("LoginServlet Checking....");
		try {
			
			String password = symmetricCipher.encryptString(request
					.getParameter("password"));
			
			logger.debug("LoginServlet Checking...."+username+"&&"+password);
			
			boolean validateresult = loginService.validate(username, password);

			if (validateresult) {
				MemberBean member = loginService.getMember(username);
				member.setPassword(symmetricCipher.decryptString(member
						.getPassword()));
				session.setAttribute("member", member);

				logger.debug("LoginServlet return Event-list");
				return new ModelAndView(new RedirectView("event-list.html"));
			}

		} catch (SQLException e) {
			logger.debug("Exception" + e.getMessage());
			e.printStackTrace();
			return new ModelAndView("bug");
		} catch (InvalidKeyException e) {
			logger.debug("Exception" + e.getMessage());
			e.printStackTrace();
			return new ModelAndView("bug");
		} catch (IllegalBlockSizeException e) {
			logger.debug("Exception" + e.getMessage());
			e.printStackTrace();
			return new ModelAndView("bug");
		} catch (BadPaddingException e) {
			logger.debug("Exception" + e.getMessage());
			e.printStackTrace();
			return new ModelAndView("bug");
		} catch (NoSuchAlgorithmException e) {
			logger.debug("Exception" + e.getMessage());
			e.printStackTrace();
			return new ModelAndView("bug");
		} catch (NoSuchPaddingException e) {
			logger.debug("Exception" + e.getMessage());
			e.printStackTrace();
			return new ModelAndView("bug");
		} catch (Base64DecodingException e) {
			logger.debug("Exception" + e.getMessage());
			e.printStackTrace();
			return new ModelAndView("bug");
		} catch (GeneralSecurityException e) {
			logger.debug("Exception" + e.getMessage());
			e.printStackTrace();
			return new ModelAndView("bug");
		}

		request.setAttribute("result", "false");
		final ModelAndView login = new ModelAndView("LoginPage");
		return login;

	}

	public SymmetricCipher getSymmetricCipher() {
		return symmetricCipher;
	}

	public void setSymmetricCipher(SymmetricCipher symmetricCipher) {
		this.symmetricCipher = symmetricCipher;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
