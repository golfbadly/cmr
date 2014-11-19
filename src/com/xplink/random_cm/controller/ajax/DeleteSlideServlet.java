package com.xplink.random_cm.controller.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xplink.random_cm.datamodel.SlideShow;
import com.xplink.random_cm.service.QueryService;
import com.xplink.random_cm.util.DateUtil;


/**
 * Servlet implementation class DeleteSlideServlet
 */
public class DeleteSlideServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
	.getLogger(DeleteSlideServlet.class);
	
	private QueryService queryService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSlideServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("IN : doGet");
		}
		String slideId = request.getParameter("selectItem");
		logger.debug("slideId :"+slideId);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		StringBuffer messages = new StringBuffer();
		
		try {
			if (slideId != null) {
				logger.info("[IN] Delete from LIST");
				SlideShow slideShow = setUpdateSlideShow(slideId);
				ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
				queryService = (QueryService) context.getBean("QueryService");
				
				boolean status = queryService.deleteSlideShow(slideShow);

				logger.debug("Status :" + status);
				
				if (status) {
					messages.append("success");
				} else {
					messages.append("fail");
				}
			}

		} catch (Exception e) {
			logger.error("Cannot remove slide:" + e);
			e.printStackTrace();
			messages.append("false");
		}
		
		logger.debug("Message :" + messages.toString());
		out.print(messages);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	

	public SlideShow setUpdateSlideShow(String slideId) {
		SlideShow slideShow = new SlideShow();
		try {
			if (slideId != null) {
				logger.info("[IN] Delete from DETAIL");
				ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
				queryService = (QueryService) context.getBean("QueryService");
				
				slideShow = queryService.getSlideShowById(Integer
						.parseInt(slideId));
				slideShow.setDeleteFlag('Y');
				slideShow.setUpdatedBy("admin"); //hardcode setting user name
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
