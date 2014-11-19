package com.xplink.random_cm.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.xplink.random_cm.datamodel.SlideShow;
import com.xplink.random_cm.datamodel.SlideShowPreview;
import com.xplink.random_cm.datamodel.SlideShowUpload;
import com.xplink.random_cm.service.QueryService;
import com.xplink.random_cm.util.DateUtil;

import fileupload.FormItems;
import fileupload.FormItemsFactory;
import fileupload.spring.ServletFileUploadHandler;

public class AddSlideShowController extends  ServletFileUploadHandler {
	private static final Logger logger = Logger.getLogger(AddSlideShowController.class);

	private QueryService queryService;
	private String IMAGE_PREFIX = "image";
	private String IMAGE_DETAIL_PREFIX = "image-detail";

	public AddSlideShowController(){
		setCommandClass(EditSlideShowController.class);
		setCommandName("EditSlideShowController");
	}
	
	public ModelAndView  processFormSubmission(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors)
	{	
		
		logger.info("[In] AddSlideShowController submit");
		
		try {
			FormItemsFactory formItemsFactory = new FormItemsFactory();
			FormItems formItems = formItemsFactory.buildFormItems(request,
					1024 * 1024 * 30);
			process(request, response,"upload", errors, formItems.getFieldItems(), formItems
					.getFileItems());

		} catch (Exception e) {
			logger.error("[ERROR IN AddSlideShowController] " + e.getMessage());
		}
		logger.info("[OUT] AddSlideShowController");
		return new ModelAndView(new RedirectView("listAdsSlide.html"));
		
	}

	
	@Override
	protected ModelAndView process(HttpServletRequest request,
			HttpServletResponse response, Object obj, BindException bindException,
			Map<String, String> mapString, Map<String, FileItem> mapfileItem)
			 {

		try {
			
			FileItem image = mapfileItem.get("image");
//			logger.debug("Image name :"+image.getName());
//			logger.debug("Image name set encoding:"+queryService.setEncodindString(image.getName()));

			String url = mapString.get("url");
			String displayDate = mapString.get("displayDateSlide");
			String expireDate = mapString.get("expireDateSlide");
			String user = request.getRemoteUser();
			String previewId = mapString.get("previewId");

			SlideShow slideShow = new SlideShow();
			slideShow.setImage(image.getName());
			slideShow.setUrl(queryService.setEncodindString(url));
			slideShow.setDeleteFlag('N');

			slideShow.setDisplayDate(setFormatDate(displayDate));
			slideShow.setExpireDate(setFormatDate(expireDate));

//			slideShow.setCreatedBy(request.getRemoteUser());
			slideShow.setCreatedBy("Aujiwa_Itachi");
			slideShow.setCreatedDate(DateUtil.getSysDate());
//			slideShow.setUpdatedBy(request.getRemoteUser());
			slideShow.setUpdatedBy("Aujiwa_Itachi");
			slideShow.setUpdatedDate(DateUtil.getSysDate());

			List<SlideShowUpload> slideShowUploadList = new ArrayList<SlideShowUpload>();
			List<FileItem> imageList = new ArrayList<FileItem>();
			List<FileItem> docList = new ArrayList<FileItem>();

			for (String key : mapfileItem.keySet()) {
				String fileDESC = "";
				logger.debug("key:"+key);
				if (key.startsWith(IMAGE_PREFIX)) {
					FileItem imageItem = mapfileItem.get(key);
					
					logger.debug("imageItem:" + imageItem);

					if (imageItem != null && imageItem.getSize()>0) {
						for (String imageDESC : mapString.keySet()) {
							if (imageDESC.startsWith(IMAGE_DETAIL_PREFIX)
									&& imageDESC.endsWith(key.substring(key
											.lastIndexOf("-")))) {
								fileDESC = mapString.get(imageDESC);
							}
						}
						if(fileDESC.length()==0){
							fileDESC = null;
						}
						SlideShowUpload slideShowUpload = setFileItemSlideShowUpload(
								slideShow, imageItem, "1", fileDESC,user);
						slideShowUploadList.add(slideShowUpload);

						imageList.add(imageItem);
					}
				}
				

			}
			//logger.debug("previewId :"+previewId);
			// Remove old preview
			SlideShowPreview slideShowPreview = queryService.getSlideShowPreviewById(previewId);
			if (slideShowPreview != null) {
				queryService.removePreview(slideShowPreview);
			}


			slideShow = queryService.createSlideShow(slideShow, image, slideShowUploadList,
					imageList, docList);
			
			logger.debug("slideShowUploadList size:"+slideShowUploadList.size());
			
			request.setAttribute("slideId", ""+slideShow.getSlideId());
			
		} catch (Exception e) {
			logger.error("[ERROR] process :" + e.toString(),e);
		}
		
		return new ModelAndView(new RedirectView("listAdsSlide.html"));

	}

	public SlideShowUpload setFileItemSlideShowUpload(SlideShow slideShow,
			FileItem fileItem, String uploadType, String fileDESC,String user) throws Exception, NamingException {

		SlideShowUpload slideShowUpload = new SlideShowUpload();
		slideShowUpload.setUploadType(uploadType);
		slideShowUpload.setFileName(fileItem.getName());
		if(fileDESC!=null){
			slideShowUpload.setFileDESC(queryService.setEncodindString(fileDESC));
		}else{
			logger.debug("=============="+fileDESC+"==================");
			slideShowUpload.setFileDESC(fileDESC);
		}
		if(user != null){
			slideShowUpload.setCreatedBy(user);
			slideShowUpload.setUpdatedBy(user);
		}else{
			slideShowUpload.setCreatedBy("admin");
			slideShowUpload.setUpdatedBy("admin");
		}
		slideShowUpload.setUpdatedDate(DateUtil.getSysDate());
		slideShowUpload.setCreatedDate(DateUtil.getSysDate());
		
		return slideShowUpload;

	}


	public Timestamp setFormatDate(String dateString) {

		Date convertedDate;
		Timestamp setTimestamp = null;
		try {
			dateString = dateString.replaceAll("-", "/");
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			convertedDate = dateFormat.parse(dateString);
			setTimestamp = new Timestamp(convertedDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return setTimestamp;

	}

	public QueryService getQueryService() {
		return queryService;
	}

	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}


}
