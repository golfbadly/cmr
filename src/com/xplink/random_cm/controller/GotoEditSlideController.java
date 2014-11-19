package com.xplink.random_cm.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.xplink.random_cm.datamodel.SlideShow;
import com.xplink.random_cm.datamodel.SlideShowUpload;
import com.xplink.random_cm.service.QueryService;
import com.xplink.random_cm.viewModel.SlideViewModel;

public class GotoEditSlideController extends SimpleFormController  {
	private static final Logger logger = Logger.getLogger(SlideShowController.class);
	private static final String JSP_EDIT = "editSlideShow";
	private QueryService queryService;
	private String viewAdsImg;


	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("[In] GotoEditSlideController return page");

		try{	
			String slideId= request.getParameter("slideId");
			logger.debug("slideId:"+slideId);
			SlideShow slideShow = (SlideShow)queryService.getSlideShowById(Integer.parseInt(slideId));
			List<SlideShowUpload> slideShowUploadList = queryService.getSlideShowUploadBySlideShow(slideShow);
			logger.debug("slideShowUploadList:"+slideShowUploadList);
			SlideViewModel slideViewModel = queryService.convertViewModel(slideShow);
			String path = request.getContextPath();
			
			request.setAttribute("previewId", request.getRemoteUser()+"_"+System.currentTimeMillis());
			request.setAttribute("path", path);
			request.setAttribute("slideShowModel", slideViewModel);
			request.setAttribute("slideShowUploadList", slideShowUploadList);
			request.setAttribute("viewAdsImg", viewAdsImg);
		}catch(Exception ex){
			logger.error("error:"+ex.toString(), ex);
		}
			
			
			return new ModelAndView(JSP_EDIT);

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

}
