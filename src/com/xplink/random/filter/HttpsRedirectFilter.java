package com.xplink.random.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpsRedirectFilter implements Filter {
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//System.out.println("[In httpsRedirect]");
		if (request instanceof HttpServletRequest
				&& response instanceof HttpServletResponse) {
			HttpServletRequest httpReq = (HttpServletRequest) request;
			String redirectTarget = httpReq.getRequestURL().toString();
			//System.out.println("[redirectTarget in] :" + redirectTarget);
			redirectTarget = redirectTarget.replaceFirst("http", "https");
			redirectTarget = redirectTarget.replaceFirst(":8080", ":8443");
			//System.out.println("[redirectTarget out] :" + redirectTarget);
			// redirectTarget = redirectTarget.replaceFirst("home", "home.do");
			if (request.isSecure()) {
				((HttpServletResponse) response).sendRedirect(redirectTarget);
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
}