package com.xplink.random_cm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.xplink.random_cm.datamodel.SlideShowPreview;
import com.xplink.random_cm.datamodel.SlideShowUploadPreview;
import com.xplink.random_cm.service.QueryService;
import com.xplink.random_cm.common.Constant;

public class PreviewSlideShowController extends SimpleFormController {
	private static final Logger logger = Logger
			.getLogger(PreviewSlideShowController.class);

	private static final String JSP_DETAIL = "detailSlideShow";
	private QueryService queryService;
	private String imageDir;
	private String docDir;
	private String rule;


	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		boolean permission = false;
		boolean isPreview = true;
		String slideId = request.getParameter("slideId");
		SlideShowPreview slideShowPreview = (SlideShowPreview) queryService.getSlideShowPreviewById(slideId);
		logger.debug("slideShowPreview slide id:"+slideShowPreview.getSlideId());
		List<SlideShowUploadPreview> slideShowUploadList = queryService.getSlideShowUploadPreviewBySlideShow(slideShowPreview);


		logger.debug("permission :" + permission);
		request.setAttribute("previewId", slideShowPreview.getSlideId());
		request.setAttribute("isPreview", isPreview);
		request.setAttribute("imageDir", imageDir+"-"+Constant.PREVIEW);
		request.setAttribute("docDir", docDir+"-"+Constant.PREVIEW);
		request.setAttribute("slideDetail", slideShowPreview);
		request.setAttribute("slideShowUploadList", slideShowUploadList);
		
		return new ModelAndView(JSP_DETAIL);
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

	public String getDocDir() {
		return docDir;
	}

	public void setDocDir(String docDir) {
		this.docDir = docDir;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}
}
