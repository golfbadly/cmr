package com.xplink.random_cm.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.xplink.random_cm.common.Constant;
import com.xplink.random_cm.datamodel.SlideShow;
import com.xplink.random_cm.service.QueryService;

public class GotoAdsSlideController extends SimpleFormController  {
	private String JSP_VIEW = "addSlideShow";
	private static final Logger logger = Logger.getLogger(SlideShowController.class);
	private QueryService queryService;
	private String imageDir;
	private String adsDir;
	private String rule;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("[In] GotoAdsSlideController return page");
		String DATE_FORMAT = Constant.DATE_FORMAT;
		SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();

		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE));
		request.setAttribute("displayDateSlide", (String) sdf.format(calendar
				.getTime()));
		calendar.add(Calendar.DATE, 7);
		request.setAttribute("expireDateSlide", (String) sdf.format(calendar
				.getTime()));
		request.setAttribute("previewId", request.getRemoteUser()+"_"+System.currentTimeMillis());

		String path = request.getContextPath();
		request.setAttribute("path", path);
		
		
		return new ModelAndView(JSP_VIEW);
	}

	public QueryService getQueryService() {
		return queryService;
	}

	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	public String getImageDir() {
		return imageDir;
	}

	public void setImageDir(String imageDir) {
		this.imageDir = imageDir;
	}
	

	public String getAdsDir() {
		return adsDir;
	}

	public void setAdsDir(String adsDir) {
		this.adsDir = adsDir;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

}
