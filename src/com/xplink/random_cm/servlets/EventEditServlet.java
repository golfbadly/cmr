package com.xplink.random_cm.servlets;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.xplink.random_cm.datamodel.EventBean;
import com.xplink.random_cm.datamodel.MemberBean;
import com.xplink.random_cm.service.EventManageService;
//import com.xplink.random_cm.service.PostFBWallService;
import fileupload.FormItems;
import fileupload.FormItemsFactory;
import fileupload.spring.ServletFileUploadHandler;

public class EventEditServlet extends ServletFileUploadHandler {

	private static final Logger logger = Logger
			.getLogger(EventEditServlet.class);
	private String imgPath;
	private String imgPathOnServer;
	private int maxFileSize = 512 * 512 * 2;
	private static final SimpleDateFormat dayMonthYearformatter = new SimpleDateFormat(
			"yyyy-MM-dd");
	private EventManageService eventManageService; // use update event or Image

	public EventEditServlet() {
		setCommandClass(EventBean.class);
		setCommandName("eventBean");
	}

	public ModelAndView processFormSubmission(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) {
		ModelAndView mav = new ModelAndView(new RedirectView("event-list.html"));

		try {

			FormItemsFactory formItemsFactory = new FormItemsFactory();
			FormItems formItems = formItemsFactory.buildFormItems(request,
					maxFileSize);

			Map<String, String> fieldItems = formItems.getFieldItems();
			Map<String, FileItem> fileItems = formItems.getFileItems();

			String eventStatus = request.getParameter("event");
			int eventId = Integer.parseInt(request.getParameter("eventId"));

			logger.debug("[eventStatus :]" + eventStatus);
			logger.debug("[eventId :]" + eventId);

			if (eventStatus.equals("edit")) {
				logger.debug("[Strart ]");

				HttpSession session = request.getSession();

				String createdDate = null;

				// declaretion path
				logger.debug("[IN EventEditServlet ]");

				// get data from page
				logger.debug("[eventName fieldItems]"
						+ fieldItems.get("eventName"));

				// default of data form jsp , servlet see data is ISO8859_1
				String eventName = fieldItems.get("eventName");
				byte[] nameEvent = eventName.getBytes("ISO8859_1");
				eventName = new String(nameEvent, "UTF-8");

				// ================================================================

				String dateStart = fieldItems.get("dateStart");
				String dateEnd = fieldItems.get("dateEnd");

				String descEvent = fieldItems.get("desc");
				descEvent = encodingString(descEvent);

				FileItem eventImg = fileItems.get("fileImageUpdate");
				MemberBean member = (MemberBean) session.getAttribute("member");

				// logger.debug("[event name :]"+eventName);
				// logger.debug("[image name :]"+eventImg.getName());
				// logger.debug("[image size :]"+eventImg.getSize());
				// logger.debug("[image Type :]"+eventImg.getContentType());
				// logger.debug("[date start :]"+dateStart);
				// logger.debug("[date end :]"+dateEnd);
				// logger.debug("[event desc :]"+descEvent);
				// logger.debug("[memberId :]"+member.getMemberid());

				// update date
				String data = "";

				Locale locale = new Locale("en", "US");
				SimpleDateFormat sdfUpdated = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss", locale);
				String updateDate = sdfUpdated.format(new Date());
				data = updateDate;

				logger.debug("[set value to eventdata]");
				logger.debug("[event name :]" + eventName);
				logger.debug("[date start :]" + StringToTimeStamp(dateStart));
				logger.debug("[date stop :]" + StringToTimeStamp(dateEnd));
				logger.debug("[create date :]" + StringToTimeStamp(data));
				logger.debug("[update date :]" + StringToTimeStamp(data));
				// logger.debug("[create by :]"+member.getMemberid());
				// logger.debug("[update by :]"+member.getMemberid());

				EventBean eventdata = new EventBean();

				eventdata.setId(eventId);
				eventdata.setEventName(eventName);
				eventdata.setRandomed("f");
				eventdata.setDateStart(StringToTimeStamp(dateStart));
				eventdata.setDateEnd(StringToTimeStamp(dateEnd));
				eventdata.setCreateBy(member.getMemberid() + "");
				eventdata.setCreateBy(member.getMemberid() + "");
				eventdata.setCreateDate(StringToTimeStamp(data));
				createdDate = eventdata.getCreateDate() + "";
				eventdata.setUpdateBy(member.getMemberid() + "");
				eventdata.setUpdateDate(StringToTimeStamp(data));
				eventdata.setDesc(descEvent);

				int memberId = Integer.parseInt(request
						.getParameter("memberId"));
				boolean updateComplete;

				// logger.debug("[eventImg :]"+eventImg);

				if (eventImg != null && !"".equals(eventImg.getName())) {

					// write file
					String[] name = eventImg.getName().split("\\.");
					String type = name[name.length - 1];

					eventdata.setImgType(type);
					// logger.debug("[img Type :]"+eventdata.getImgType());
					// logger.debug("[event Id :]"+eventdata.getId());
					// File file = new File(imgPath,eventId+"."+type); //image
					// is eventId+fileType
					// logger.debug("[write file path :]"+file.getPath());
					// eventImg.write(file);

					logger.debug("[--- update event and Image ---]");
					updateComplete = eventManageService.updateImageEvent(
							eventdata, eventId, memberId);

				} else {

					// eventdata.setImgType(request.getParameter("imgType"));

					logger.debug("[--- update event ---]");
					updateComplete = eventManageService.updateEvent(eventdata,
							eventId, memberId);

					// post user wall Facebook when create event
					// Now Not use 4/11/57

					/*
					 * PostFBWallService postFBWallService = new
					 * PostFBWallService(); String accessToken = (String)
					 * session.getAttribute("accessToken");
					 */

					/*
					 * if (updateComplete == true){ String dataPost =
					 * URLEncoder.encode("access_token", "UTF-8") + "=" +
					 * URLEncoder.encode(accessToken, "UTF-8"); dataPost += "&"
					 * + URLEncoder.encode("name", "UTF-8") + "=" +
					 * URLEncoder.encode
					 * (member.getName()+" has created event "+eventName
					 * +" on Christmas Random", "UTF-8"); dataPost += "&" +
					 * URLEncoder.encode("message", "UTF-8") + "=" +
					 * URLEncoder.encode
					 * (member.getName()+" has created event "+eventName
					 * +" on Christmas Random", "UTF-8"); dataPost += "&" +
					 * URLEncoder.encode("caption", "UTF-8") + "=" +
					 * URLEncoder.encode("Start :"+dateStart, "UTF-8"); dataPost
					 * += "&" + URLEncoder.encode("link", "UTF-8") + "=" +
					 * URLEncoder
					 * .encode("http://apps.facebook.com//christmasrandom/",
					 * "UTF-8"); dataPost += "&" +
					 * URLEncoder.encode("description", "UTF-8") + "=" +
					 * URLEncoder.encode("Let Random Keyword with me.",
					 * "UTF-8"); dataPost += "&" + URLEncoder.encode("picture",
					 * "UTF-8") + "=" +
					 * URLEncoder.encode("http://goobre.com/cmr/pics/gift.gif",
					 * "UTF-8");
					 * 
					 * 
					 * postFBWallService.post(dataPost); }
					 */
				}

				String startDate = dayMonthYearformatter
						.format((java.util.Date) eventdata.getDateStart());
				String endDate = dayMonthYearformatter
						.format((java.util.Date) eventdata.getDateEnd());

				request.setAttribute("startDate", startDate);
				request.setAttribute("endDate", endDate);
				request.setAttribute("eventdata", eventdata);
				request.setAttribute("imgPathOnServer", imgPathOnServer);

				logger.debug("[--- update event finish---]");
			}
		} catch (Exception e) {
			logger.error("[Exception :]" + e.getMessage(), e);
			return new ModelAndView("bug");
		}

		return mav;
	}

	public ModelAndView process(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors,
			Map<String, String> fieldItems, Map<String, FileItem> fileItems) {
		return null;
	}

	public int getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(int maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public Timestamp StringToTimeStamp(String date) throws ParseException {
		if ("".equals(date)) {
			SimpleDateFormat sdfUpdated = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String updateDate = sdfUpdated.format(new Date());
			java.util.Date dateTime = sdfUpdated.parse(updateDate);
			java.sql.Timestamp timeDate = new java.sql.Timestamp(
					dateTime.getTime());
			return timeDate;

		} else {
			date = date + " 00:00:00 ";
			SimpleDateFormat setFormat = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			java.util.Date dateTime = setFormat.parse(date);
			java.sql.Timestamp timeDate = new java.sql.Timestamp(
					dateTime.getTime());
			return timeDate;
		}
	}

	private String encodingString(String text)
			throws UnsupportedEncodingException {
		byte[] eventDesc = text.getBytes("ISO8859_1");
		text = new String(eventDesc, "UTF-8");

		return text;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getImgPathOnServer() {
		return imgPathOnServer;
	}

	public void setImgPathOnServer(String imgPathOnServer) {
		this.imgPathOnServer = imgPathOnServer;
	}

	public EventManageService getEventManageService() {
		return eventManageService;
	}

	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}

}
