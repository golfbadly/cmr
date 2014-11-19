package com.xplink.random_cm.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.xplink.random_cm.datamodel.SlideShow;
import com.xplink.random_cm.service.QueryService;

public class SlideShowController extends SimpleFormController  {
	private String JSP_VIEW = "slideShow";
	private static final Logger logger = Logger.getLogger(SlideShowController.class);
	private QueryService queryService;
	private String imageDir;
	private String adsDir;
	private String rule;
	private String viewAdsImg;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<SlideShow> listSlideShow = (List<SlideShow>)queryService.getListSlideShow();
		
		logger.info("IN SlideShowController");
		
		request.setAttribute("imageDir", imageDir);
		request.setAttribute("adsDir", adsDir);
		request.setAttribute("listSlideShow", listSlideShow);
		request.setAttribute("viewAdsImg", viewAdsImg);
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

	public String getViewAdsImg() {
		return viewAdsImg;
	}

	public void setViewAdsImg(String viewAdsImg) {
		this.viewAdsImg = viewAdsImg;
	}
	

}
