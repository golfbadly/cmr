package com.xplink.random_cm.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.xplink.random_cm.datamodel.SlideShow;
import com.xplink.random_cm.service.QueryService;
import com.xplink.random_cm.viewModel.SlideViewModel;

public class ListSlideShowController extends SimpleFormController  {
	private static final Logger logger = Logger.getLogger(ListSlideShowController.class);
	private String JSP_VIEW = "listSlideShow";
	private QueryService queryService;
	private String imageDir;
	private String adsDir;
	private String viewAdsImg;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("[IN]SlideShowListController");
		String currentPage = request.getParameter("newPage");

		if(currentPage==null){
			currentPage = "1";
		}
		
		List<SlideShow> listSlide = (List<SlideShow>)queryService.getListIntroByPagination(Integer.parseInt(currentPage));

		List<SlideViewModel> convertViewModelList = (List<SlideViewModel>) queryService.convertViewModelList(listSlide);
		int pageNumber =queryService.getPageNumber();
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("listSlide", convertViewModelList);
		request.setAttribute("imageDir", imageDir);
		request.setAttribute("adsDir", adsDir);
		request.setAttribute("viewAdsImg", viewAdsImg);
		
		logger.info("[OUT]SlideShowListController");
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

	public String getViewAdsImg() {
		return viewAdsImg;
	}

	public void setViewAdsImg(String viewAdsImg) {
		this.viewAdsImg = viewAdsImg;
	}
	
	
}
