package com.xplink.random_cm.servlets;

import java.io.File;
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
import fileupload.FormItems;
import fileupload.FormItemsFactory;
import fileupload.spring.ServletFileUploadHandler;

public class EventCreateServlet extends ServletFileUploadHandler {

	private static final Logger logger = Logger
			.getLogger(EventCreateServlet.class);
	private static final String DESTINATION_DIR_PATH = "/home/golf/XP-Link/cmrMaster/cmr/WebContent/pics/eventImg";
	private int maxFileSize = 512 * 512 * 2;

	public EventCreateServlet() {
		setCommandClass(EventBean.class);
		setCommandName("eventBean");
	}

	public ModelAndView processFormSubmission(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		FormItemsFactory formItemsFactory = new FormItemsFactory();
		FormItems formItems = formItemsFactory.buildFormItems(request,
				maxFileSize);

		String eventStatus = request.getParameter("event");

		logger.debug("[eventStatus :]" + eventStatus);

		if (eventStatus.equals("no")) {
			logger.debug("[recive eventStatus no create]");
			return new ModelAndView("create-event");
		}

		if (eventStatus.equals("create")) {
			logger.debug("[recive eventStatus ]");

			process(request, response, command, errors,
					formItems.getFieldItems(), formItems.getFileItems());

		}

		return new ModelAndView(new RedirectView("event-list.html"));
	}

	public ModelAndView process(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors,
			Map<String, String> fieldItems, Map<String, FileItem> fileItems)
			throws Exception {

		HttpSession session = request.getSession();

		int eventId = 0;
		String createdDate = null;
		EventBean eventData = null;

		logger.debug("[IN EventCreateServlet ]");

		String eventName = fieldItems.get("eventName");
		String dateStart = fieldItems.get("dateStart");
		String dateEnd = fieldItems.get("dateEnd");
		FileItem eventImg = fileItems.get("fileImageUpdate");
		MemberBean member = (MemberBean) session.getAttribute("member");

		logger.debug("[event name :]" + eventName);
		logger.debug("[image name :]" + eventImg.getName());
		logger.debug("[image size :]" + eventImg.getSize());
		logger.debug("[image Type :]" + eventImg.getContentType());
		logger.debug("[date start :]" + dateStart);
		logger.debug("[date end :]" + dateEnd);
		logger.debug("[memberId :]" + member.getMemberid());

		// write file

		File file = new File(DESTINATION_DIR_PATH, eventImg.getName());
		eventImg.write(file);
		logger.debug("[write file path :]" + file.getPath());

		// update date
		String data = "";
		try {
			Locale locale = new Locale("en", "US");
			SimpleDateFormat sdfUpdated = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss", locale);
			String updateDate = sdfUpdated.format(new Date());
			data = updateDate;
		} catch (Exception e) {
			logger.error("Can't get updated date : ", e);
		}

		logger.debug("[set value to eventdata]");

		logger.debug("[event name :]" + eventName);

		logger.debug("[date start :]" + StringToTimeStamp(dateStart));
		logger.debug("[date stop :]" + StringToTimeStamp(dateEnd));
		logger.debug("[create by :]" + member.getMemberid());
		logger.debug("[cteate date :]" + StringToTimeStamp(data));
		logger.debug("[update by :]" + member.getMemberid());
		logger.debug("[update date :]" + StringToTimeStamp(data));

		EventBean eventdata = new EventBean();

		eventdata.setEventName(eventName);
		eventdata.setDateStart(StringToTimeStamp(dateStart));
		eventdata.setDateEnd(StringToTimeStamp(dateEnd));
		eventdata.setCreateBy(member.getMemberid() + "");
		eventdata.setCreateDate(StringToTimeStamp(data));
		createdDate = eventdata.getCreateDate() + "";
		eventdata.setUpdateBy(member.getMemberid() + "");
		eventdata.setUpdateDate(StringToTimeStamp(data));

		EventManageService createEvent = new EventManageService();

		logger.debug("[--- create event ---]");
		// boolean createComplete = createEvent.create(eventdata);

		request.setAttribute("eventdata", eventdata);

		eventId = createEvent.getLastEventId(member.getMemberid());

		return null;
	}

	public int getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(int maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public Timestamp StringToTimeStamp(String date) throws ParseException {
		date = date + " 00:00:00 ";
		SimpleDateFormat setFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date dateTime = setFormat.parse(date);
		java.sql.Timestamp timeDate = new java.sql.Timestamp(dateTime.getTime());
		return timeDate;
	}

}
