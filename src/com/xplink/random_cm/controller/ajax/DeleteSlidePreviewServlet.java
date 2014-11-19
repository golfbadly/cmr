package com.xplink.random_cm.controller.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xplink.random_cm.datamodel.SlideShowPreview;
import com.xplink.random_cm.service.QueryService;


/**
 * Servlet implementation class DeleteSlidePreviewServlet
 */
public class DeleteSlidePreviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger
	.getLogger(DeleteSlidePreviewServlet.class);
	private QueryService queryService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSlidePreviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String previewId = (String)request.getParameter("previewId");
		ApplicationContext context = WebApplicationContextUtils
		.getWebApplicationContext(getServletContext());
		queryService = (QueryService) context.getBean("QueryService");
		// Remove old preview
		SlideShowPreview slideShowPreview;
		boolean status = false;
		try {
			slideShowPreview = queryService.getSlideShowPreviewById(previewId);
			if (slideShowPreview != null) {
				status =queryService.removePreview(slideShowPreview);
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		if(status){
			logger.debug("delete success");
		}else{
			logger.debug("delete fail");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public QueryService getQueryService() {
		return queryService;
	}

	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

}
