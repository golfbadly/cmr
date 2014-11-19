package com.xplink.random_cm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.xplink.random_cm.datamodel.SlideShow;
import com.xplink.random_cm.service.QueryService;
import com.xplink.random_cm.util.DateUtil;



public class DeleteSlideShowController implements Controller {
	private static final Logger logger = Logger.getLogger(DeleteSlideShowController.class);
	private String JSP_VIEW = "listSlideShow";
	private QueryService queryService;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("[IN] DeleteSlideShowController");
		String slideId = request.getParameter("slideId");
		try {
			if (slideId != null) {
				logger.info("[IN] Delete from DETAIL");
				SlideShow slideShow = setUpdateSlideShow(slideId);
				queryService.deleteSlideShow(slideShow);

				logger.info("DELETE SlideShow SUCCESS");
			}

		} catch (Exception e) {
			logger.error("" + e.getMessage());
		}
		logger.debug("[OUT] DeleteSlideShowController");
		
		return new ModelAndView(new RedirectView("listAdsSlide.html"));
	}

	public SlideShow setUpdateSlideShow(String slideId) {
		SlideShow slideShow = new SlideShow();
		try {
			if (slideId != null) {
				logger.info("[IN] Delete from DETAIL");
				slideShow = queryService.getSlideShowById(Integer
						.parseInt(slideId));
				slideShow.setDeleteFlag('Y');
				slideShow.setUpdatedBy("admin"); //hardcode user name setting
				slideShow.setUpdatedDate(DateUtil.getSysDate());
			}

		} catch (Exception e) {
			logger.error("" + e.getMessage());
		}
		return slideShow;

	}

	public QueryService getQueryService() {
		return queryService;
	}

	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}



}
