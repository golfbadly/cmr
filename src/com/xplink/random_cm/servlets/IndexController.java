package com.xplink.random_cm.servlets;

	import java.util.Locale;
	import java.util.Map;

	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;

	import org.springframework.validation.BindException;
	import org.springframework.web.servlet.ModelAndView;
	import org.springframework.web.servlet.i18n.SessionLocaleResolver;
	import org.springframework.web.servlet.mvc.SimpleFormController;

	import com.xplink.random_cm.locale.Constant;

	/**
	 * Class for control index.jsp
	 * 
	 * @author Jitrika Nanta
	 */

	public class IndexController extends SimpleFormController {
	
		// What new view		
		@Override
		protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			HttpSession session = request.getSession();
			String defaultLocale = (String) session.getAttribute(Constant.SESSION_DEFAULT_LOCALE);
			if(defaultLocale==null){
				Locale locale = new Locale(Constant.LOCALE_NAME_TH);
		        SessionLocaleResolver localeResolver = (SessionLocaleResolver) getApplicationContext().getBean("localeResolver");
		        localeResolver.setDefaultLocale(locale);
		        session.setAttribute(Constant.SESSION_DEFAULT_LOCALE, "true");
			}
			return super.handleRequestInternal(request, response);
		}

		@SuppressWarnings("unchecked")
		protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
				Object obj, BindException bindException) throws Exception {
			logger.debug("[IN:onSubmit]");
			Map models = bindException.getModel();

			// add language
				SessionLocaleResolver localeResolver = (SessionLocaleResolver) getApplicationContext()
						.getBean(Constant.BEAN_localeResolver);
				String localeName = localeResolver.resolveLocale(request).getLanguage();
				//									
				return null;

		}
	}
	
