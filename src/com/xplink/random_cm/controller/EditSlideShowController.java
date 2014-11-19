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


public class EditSlideShowController extends  ServletFileUploadHandler {

	private static final String JSP_EDIT = "editSlideShow";
	private static final Logger logger = Logger.getLogger(EditSlideShowController.class);
	private QueryService queryService;
	private String IMAGE_PREFIX = "image";
	private String IMAGE_DETAIL_PREFIX = "image-detail";
	private String viewAdsImg;
	
	public EditSlideShowController(){
		setCommandClass(EditSlideShowController.class);
		setCommandName("EditSlideShowController");
	}

	public ModelAndView  processFormSubmission(HttpServletRequest request,HttpServletResponse response, Object object, BindException errors)
	{	
	 
		logger.info("[In] EditSlideShowController");
		String slideId = "";
		try{
			FormItemsFactory formItemsFactory = new FormItemsFactory();
			FormItems formItems = formItemsFactory.buildFormItems(request,
					1024 * 1024 * 30);
			
			Map<String, String> mapString =  formItems.getFieldItems();
			slideId = mapString.get("slideId");
			
			process(request, response,"upload", errors, formItems.getFieldItems(), formItems
					.getFileItems());
		
		logger.info("[OUT] EditSlideShowController");
		request.setAttribute("slideId", ""+formItems.getFieldItems().get("slideId"));

		}catch (Exception e) {
			logger.error("[ERROR IN EditSlideShowController] "+e.getMessage());
		}
		
		return new ModelAndView(new RedirectView("detailSlide.html?slideId="+slideId));
		
	}

	
	@Override
	protected ModelAndView process(HttpServletRequest request,
			HttpServletResponse response, Object obj, BindException bindException,
			Map<String, String> mapString, Map<String, FileItem> mapfileItem)
			 {
		
		try{
		
		FileItem image = mapfileItem.get("image");
		FileItem document = mapfileItem.get("document-1");
		logger.info("[IN] UPDATE INTRO ");
		logger.debug("prodImg :"+image.getName());

		String slideId = mapString.get("slideId");
		
		SlideShow slideShow = queryService.getSlideShowById(Integer.parseInt(slideId));
		String url = mapString.get("url");
		String displayDate = mapString.get("displayDateSlide");
		String expireDate = mapString.get("expireDateSlide");
		String user = request.getRemoteUser();
		String previewId = mapString.get("previewId");
		
		List<SlideShowUpload> slideShowUploadList = new ArrayList<SlideShowUpload>();
		List<FileItem> imageList = new ArrayList<FileItem>();
		
		if(image.getSize()>0){
			slideShow.setImage(image.getName());
		}
		
		
		for (String key : mapfileItem.keySet()) {
			String fileDESC = "";
			logger.debug("key:"+key);
			if (key.startsWith(IMAGE_PREFIX)) {
				FileItem imageItem = mapfileItem.get(key);

				if (imageItem != null&&imageItem.getSize()>0) {
					logger.debug("docItem.getSize();"+imageItem.getSize());
					for (String imageDESC : mapString.keySet()) {
						logger.debug("imageDESC:"+imageDESC);
						if (imageDESC.startsWith(IMAGE_DETAIL_PREFIX)) {
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
		
		if(url!=null){
			slideShow.setUrl(queryService.setEncodindString(url));
		}
		if(displayDate!=null){
			slideShow.setDisplayDate(setFormatDate(displayDate));
		}
		if(expireDate!=null){
			slideShow.setExpireDate(setFormatDate(expireDate));
		}
		
		slideShow.setUpdatedBy(user);
		slideShow.setUpdatedDate(DateUtil.getSysDate());
		
		// Remove old preview
		SlideShowPreview slideShowPreview = queryService.getSlideShowPreviewById(previewId);
		if (slideShowPreview != null) {
			queryService.removePreview(slideShowPreview);
		}
		
			queryService.updateSlideShow(slideShow, image, slideShowUploadList, imageList );
		

	  } catch (Exception e) {
					logger.error("[ERROR] process :" + e.toString(),e);
	  }

		return null;
	
	}
	
	public SlideShowUpload setFileItemSlideShowUpload(SlideShow slideShow,
			FileItem fileItem, String uploadType, String fileDESC,String user ) throws NamingException {

		SlideShowUpload slideShowUpload = new SlideShowUpload();
		slideShowUpload.setUploadType(uploadType);
		slideShowUpload.setFileName(fileItem.getName());
		if(fileDESC!=null){
			slideShowUpload.setFileDESC(queryService.setEncodindString(fileDESC));
		}else{
			logger.debug("=============="+fileDESC+"==================");
			slideShowUpload.setFileDESC(fileDESC);
		}
		
		if(user!=null){
			slideShowUpload.setCreatedBy(user);
			slideShowUpload.setUpdatedBy(user);
		}else{
			slideShowUpload.setCreatedBy("admin");
			slideShowUpload.setUpdatedBy("admin");
		}
		slideShowUpload.setCreatedDate(DateUtil.getSysDate());
		slideShowUpload.setUpdatedDate(DateUtil.getSysDate());
		return slideShowUpload;

	}
	public String setEncodindString(String data){
		   byte[] dataToUTF8;
		   String  changedData=null;
		try {
			dataToUTF8 = data.getBytes("ISO8859_1");
			changedData = new String(dataToUTF8,"UTF-8" );
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return changedData;
		
	}

	public Timestamp setFormatDate(String dateString){

		Date convertedDate;
		Timestamp setTimestamp = null;
		try {
			dateString=dateString.replaceAll("-", "/");
			DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy HH:mm");
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

	public String getViewAdsImg() {
		return viewAdsImg;
	}

	public void setViewAdsImg(String viewAdsImg) {
		this.viewAdsImg = viewAdsImg;
	}
	

}
