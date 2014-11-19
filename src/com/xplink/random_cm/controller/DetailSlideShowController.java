package com.xplink.random_cm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.xplink.random_cm.datamodel.SlideShow;
import com.xplink.random_cm.datamodel.SlideShowUpload;
import com.xplink.random_cm.service.QueryService;

public class DetailSlideShowController implements Controller {
	private static final Logger logger = Logger
	.getLogger(DetailSlideShowController.class);
	private static final String JSP_DETAIL = "detailSlideShow";
	private QueryService queryService;
	private String viewAdsImg;
	private String rule;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

//		boolean permission = false;
		String slideId= request.getParameter("slideId");
		logger.debug("slide ID:"+slideId);
		SlideShow slideShow = (SlideShow)queryService.getSlideShowById(Integer.parseInt(slideId));
		List<SlideShowUpload> slideShowUploadList = queryService.getSlideShowUploadBySlideShow(slideShow);
		logger.debug("slideShow:"+slideShow.getUrl());
		logger.debug("slideShowUploadList:"+slideShowUploadList.size());
		
//		logger.debug("permission :"+permission);
		request.setAttribute("viewAdsImg", viewAdsImg);
		request.setAttribute("slideDetail", slideShow);
		request.setAttribute("slideShowUploadList", slideShowUploadList);
		return new ModelAndView(JSP_DETAIL);
	}

	public QueryService getQueryService() {
		return queryService;
	}

	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	public String getViewAdsImg() {
		return viewAdsImg;
	}

	public void setViewAdsImg(String viewAdsImg) {
		this.viewAdsImg = viewAdsImg;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}


}
