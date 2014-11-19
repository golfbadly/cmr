package com.xplink.random_cm.controller.ajax;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.xplink.random_cm.datamodel.SlideShowUpload;
import com.xplink.random_cm.service.QueryService;

public class DeleteImageAjaxController implements Controller {
	private static final Logger logger = Logger
	.getLogger(DeleteImageAjaxController.class);
	private QueryService queryService;

	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		logger.info("[IN] DeleteImageAjaxController");
		String uploadId = request.getParameter("uploadId");
		SlideShowUpload slideShowUpload = queryService.getSlideShowUploadById(Integer.parseInt(uploadId));
		queryService.deleteslideShowUploadImgFile(slideShowUpload);
		queryService.deleteslideShowUpload(slideShowUpload);
		logger.info("[OUT] DeleteImageAjaxController");
		
		return new ModelAndView(new RedirectView(""));

	}


	public QueryService getQueryService() {
		return queryService;
	}

	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}
}
