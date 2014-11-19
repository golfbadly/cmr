package com.xplink.random_cm.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.xplink.random_cm.datamodel.EventBean;
import com.xplink.random_cm.datamodel.MemberBean;

/**
 * Ajax controller class for switch language
 * 
 * @author Jitrika Nanta
 */

public class AjaxSwitchLangController extends SimpleAjaxLocaleController{
	private static final Logger logger = Logger.getLogger(AjaxSwitchLangController.class);
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	logger.info("[In AjaxSwitchLangController]");
    	HttpSession session = request.getSession(true);
    	
        String language = request.getParameter("LOCALE");
        String controller = request.getParameter("controller");
        language = language.trim();
        Locale locale = new Locale(language);
        SessionLocaleResolver localeResolver = (SessionLocaleResolver) getApplicationContext().getBean("localeResolver");
        localeResolver.setDefaultLocale(locale);
        
        session.setAttribute("LOCALE",language);
        
    //get list for event-list			
        
        
        return getModelAndViewWithResult(language,controller);
        
    }
    
}
