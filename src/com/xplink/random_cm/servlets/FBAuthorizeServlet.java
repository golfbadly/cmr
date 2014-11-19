package com.xplink.random_cm.servlets;

import java.io.IOException;

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
import com.xplink.random_cm.service.FBAuthorizeService;

public class FBAuthorizeServlet extends AbstractController {
	// public class FBAuthorizeServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(FBAuthorizeService.class);
	private FBAuthorizeService fbAuthorizeService;

	public FBAuthorizeService getFbAuthorizeService() {
		return fbAuthorizeService;
	}

	public void setFbAuthorizeService(FBAuthorizeService fbAuthorizeService) {
		this.fbAuthorizeService = fbAuthorizeService;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String redirect = null;
		logger.debug("[IN: FBAuthorizeServlet]");

		//Test sendRedirect 17/11/57
		/*redirect = "https://www.facebook.com/dialog/oauth?client_id=1503306796600671&redirect_uri=https%3A%2F%2Fapps.facebook.com%2Frandom_cm&scope=publish_stream,email";
		response.getWriter().println(
				   "<script>" + 
				   "top.location.href = \""+redirect+"\"" +
				   "</script>"
				);
		
		logger.debug("[OUT: FBAuthorizeServlet]");
		
		return null;*/

		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView("agent");

		String sign = (String) request.getParameter("signed_request");
		if (sign == null) {
			sign = (String) request.getAttribute("signed_request");
		}
		logger.debug("[sined request :]" + sign);
		// logger.debug("[request_ids :]"+request_ids);

		try {
			if (!sign.equals("null")) {
				logger.debug("get signed_request");

				// FBAuthorizeService fbAuth = new FBAuthorizeService();
				// String passAuth = fbAuth.authorize(sign);
				String passAuth = fbAuthorizeService.authorize(sign);

				if (!passAuth.equals("null") || !passAuth.equals(null)) {
					logger.debug("[IN: redirect]");
					if (passAuth.equals("redirect")) {
						logger.debug("[IN oauth]redirectUrl to oauth");

						redirect = fbAuthorizeService.getRedirectUrl();					
					    //mav.addObject("redirectUrl", redirect);
					    
						if (redirect != null) {
							logger.debug("[redirectUrl :]" + redirect);	
							//response.sendRedirect(redirect);
							response.getWriter().println(
									   "<script>" + 
									   "top.location.href = \""+redirect+"\"" +
									   "</script>"
									);
							return null;
						}
		
					}
					if (passAuth.equals("accessToken")) {
						logger.debug("[IN accessToken]this is authorized user, get their info from Graph API using received access token");
						String accessToken = fbAuthorizeService
								.getAccessToken();
						FacebookClient facebookClient = new DefaultFacebookClient(
								accessToken);
						logger.debug("[accessToken :]" + accessToken);
						session.setAttribute("accessToken", accessToken);
						User user = facebookClient
								.fetchObject("me", User.class);
						mav.addObject("user", user);
					}
				} else {
					logger.debug("[ FBAuthorizeServlet error algorithm]");
				}
			} else {
				logger.debug(" //this page was opened not inside facebook // iframe possibly as a post-authorization redirect // do server side forward to facebook app");
				// this page was opened not inside facebook iframe,
				// possibly as a post-authorization redirect.
				// do server side forward to facebook app
				return new ModelAndView(new RedirectView("event-list.html"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("[Exception :]" + e);
			return new ModelAndView("bug");
		}
		logger.debug("[OUT: FBAuthorizeServlet]");
		return mav;

	}
}