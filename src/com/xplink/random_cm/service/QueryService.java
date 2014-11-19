package com.xplink.random_cm.service;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.xplink.random_cm.dao.SlideDAO;
import com.xplink.random_cm.dao.SlidePreviewDAO;
import com.xplink.random_cm.dao.SlideUploadDAO;
import com.xplink.random_cm.dao.SlideUploadPreviewDAO;
import com.xplink.random_cm.datamodel.SlideShow;
import com.xplink.random_cm.datamodel.SlideShowPreview;
import com.xplink.random_cm.datamodel.SlideShowUpload;
import com.xplink.random_cm.datamodel.SlideShowUploadPreview;
import com.xplink.random_cm.service.uploadImg.UploadByCommon;
import com.xplink.random_cm.viewModel.SlideViewModel;

public class QueryService {
	private static final Logger logger = Logger.getLogger(QueryService.class);

	private SlideDAO slideDAO;
	private SlideUploadDAO slideUploadDAO;
	private String pageSize;
	private UploadByCommon uploadImgService;
	private PaginationService paginationService;
	private PlatformTransactionManager txManager;

	
	private SlidePreviewDAO slidePreviewDAO;
	private SlideUploadPreviewDAO slideUploadPreviewDAO;

	public SlideShow createSlideShow(SlideShow slideShow, FileItem fileItem,
			List<SlideShowUpload> slideShowUploadList,
			List<FileItem> imageList, List<FileItem> docList)
			throws HibernateException, Exception {

		// initialize transaction status
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName(Long.toString(System.currentTimeMillis()));
		TransactionStatus status = txManager.getTransaction(def);
		SlideShow newCreate = (SlideShow)slideDAO.create(slideShow);
		
		try {
			
			long start = System.currentTimeMillis();
			long end = System.currentTimeMillis();
			logger.debug("Inject time : " + ((float)(end-start)/1000) + " seconds");
		}
		catch(Exception e) {
			logger.error("Error while injecting content to Thaiquest", e);
			txManager.rollback(status);
			throw e;
		}

		if (slideShowUploadList.size() > 0) {
			for (int i = 0; i < slideShowUploadList.size(); i++) {
				SlideShowUpload slideShowUpload = slideShowUploadList.get(i);
				slideShowUpload.setSlideRef(newCreate);
				slideUploadDAO.create(slideShowUpload);
			}
		}
		if (imageList != null && !imageList.isEmpty()) {
			for (int i = 0; i < imageList.size(); i++) {
				logger.debug("imageList "+i+" :"+imageList.get(i) );
				addImageToDirectory(imageList.get(i), newCreate, "Other");
			}
		}

		if (docList != null && !docList.isEmpty()) {
			for (int i = 0; i < docList.size(); i++) {
				addDocumentToDirectory(docList.get(i), newCreate);
			}
		}

		addImageToDirectory(fileItem, newCreate, "ads");

		txManager.commit(status);

		return newCreate;
	}
	
	public SlideShowPreview createSlideShowPreview(SlideShowPreview slideShow, FileItem fileItem,
			List<SlideShowUploadPreview> slideShowUploadList,
			List<FileItem> imageList, List<FileItem> docList)
			throws HibernateException, Exception {

		// initialize transaction status
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName(Long.toString(System.currentTimeMillis()));
		TransactionStatus status = txManager.getTransaction(def);
		SlideShowPreview newCreate = (SlideShowPreview) slidePreviewDAO.create(slideShow);

		
		if (slideShowUploadList.size() > 0) {
			for (int i = 0; i < slideShowUploadList.size(); i++) {
				SlideShowUploadPreview slideShowUpload = slideShowUploadList.get(i);
				slideShowUpload.setSlideRef(newCreate);
				slideUploadPreviewDAO.create(slideShowUpload);
			}
		}
		if (imageList != null && !imageList.isEmpty()) {
			for (int i = 0; i < imageList.size(); i++) {
				logger.debug("imageList "+i+" :"+imageList.get(i) );
				addImagePreviewToDirectory(imageList.get(i), newCreate, "Other");
			}
		}

		if (docList != null && !docList.isEmpty()) {
			for (int i = 0; i < docList.size(); i++) {
				addDocumentPreviewToDirectory(docList.get(i), newCreate);
			}
		}

		addImagePreviewToDirectory(fileItem, newCreate, "Title");

		txManager.commit(status);

		return newCreate;
	}

	public List<SlideShowUpload> getSlideShowUploadBySlideShow(
			SlideShow slideRef) throws HibernateException, Exception {
		List<SlideShowUpload> slideShowUploadList = slideUploadDAO
				.getSlideShowUploadBySlideShow(slideRef);
		return slideShowUploadList;

	}
	
	public List<SlideShowUploadPreview> getSlideShowUploadPreviewBySlideShow(
			SlideShowPreview slideRef) throws HibernateException, Exception {
		List<SlideShowUploadPreview> slideShowUploadList = slideUploadPreviewDAO.getSlideShowUploadPreviewBySlideShow(slideRef);
		return slideShowUploadList;

	}

	public SlideShowUpload getSlideShowUploadById(int slideUploadId)
			throws HibernateException, Exception {
		SlideShowUpload slideShowUpload = slideUploadDAO.getSlideShowUploadById(slideUploadId);
		return slideShowUpload;

	}
	
	public void deleteSlideShowUploadedDocumentFromAjax(SlideShowUpload slideShowUpload) throws HibernateException, Exception {
		
		logger.debug("[IN] deleteSlideShowUploadedDocumentFromAjax");
		
		// initialize transaction status
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName(Long.toString(System.currentTimeMillis()));
		TransactionStatus status = txManager.getTransaction(def);
		
		slideUploadDAO.delete(slideShowUpload);
		
		try {
			
			long start = System.currentTimeMillis();
//			injector.edit(slideShowUpload.getSlideRef(), null);
			long end = System.currentTimeMillis();
			logger.debug("Inject time : " + ((float)(end-start)/1000) + " seconds");
		}
		catch(Exception e) {
			logger.error("Error while injecting content to Thaiquest", e);
			txManager.rollback(status);
			throw e;
		}
		
		txManager.commit(status);
	}
	
	public void deleteslideShowUpload(SlideShowUpload slideShowUpload) throws HibernateException, Exception{
		slideUploadDAO.delete(slideShowUpload);
	}
	public void deleteslideShowUploadImgFile(SlideShowUpload slideShowUpload) throws HibernateException, Exception{
		uploadImgService.deleteImageFromDirectoryById(slideShowUpload);
	}
	public void deleteslideShowUploadDocFile(SlideShowUpload slideShowUpload) throws HibernateException, Exception{
		uploadImgService.deleteDocumentFromDirectoryById(slideShowUpload);
	}
	public void updateSlideShow(SlideShow slideShow, FileItem fileItem,
			List<SlideShowUpload> slideShowUploadList,
			List<FileItem> imageList)
			throws HibernateException, Exception {
		
		// initialize transaction status
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName(Long.toString(System.currentTimeMillis()));
		TransactionStatus status = txManager.getTransaction(def);

		addImageToDirectory(fileItem, slideShow, "Title");
		slideDAO.update(slideShow);
		
		try {
			
			long start = System.currentTimeMillis();
			
//			if(docList.size() > 0) {
//				injector.edit(slideShow, convertToAttachments(docList));
//			}
//			else {
//				injector.edit(slideShow, convertToAttachment(slideShow));
//			}
			
			long end = System.currentTimeMillis();
			logger.debug("Edit time : " + ((float)(end-start)/1000) + " seconds");
		}
		catch(Exception e) {
			logger.error("Error while injecting content to Thaiquest", e);
			txManager.rollback(status);
			throw e;
		}
		
		if (slideShowUploadList.size() > 0) {
			for (int i = 0; i < slideShowUploadList.size(); i++) {
				SlideShowUpload slideShowUpload = slideShowUploadList.get(i);
				slideShowUpload.setSlideRef(slideShow);
				slideUploadDAO.create(slideShowUpload);
			}
		}
		if (imageList != null && !imageList.isEmpty()) {
			for (int i = 0; i < imageList.size(); i++) {
				addImageToDirectory(imageList.get(i), slideShow, "Other");
			}
		}
		
		txManager.commit(status);
	}

	public void updateSlideShow(SlideShow slideShow, FileItem fileItem,
			List<SlideShowUpload> slideShowUploadList,
			List<FileItem> imageList, List<FileItem> docList)
			throws HibernateException, Exception {
		
		// initialize transaction status
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName(Long.toString(System.currentTimeMillis()));
		TransactionStatus status = txManager.getTransaction(def);

		addImageToDirectory(fileItem, slideShow, "Title");
		slideDAO.update(slideShow);
		
		try {
			
			long start = System.currentTimeMillis();
			
//			if(docList.size() > 0) {
//				injector.edit(slideShow, convertToAttachments(docList));
//			}
//			else {
//				injector.edit(slideShow, convertToAttachment(slideShow));
//			}
			
			long end = System.currentTimeMillis();
			logger.debug("Edit time : " + ((float)(end-start)/1000) + " seconds");
		}
		catch(Exception e) {
			logger.error("Error while injecting content to Thaiquest", e);
			txManager.rollback(status);
			throw e;
		}
		
		if (slideShowUploadList.size() > 0) {
			for (int i = 0; i < slideShowUploadList.size(); i++) {
				SlideShowUpload slideShowUpload = slideShowUploadList.get(i);
				slideShowUpload.setSlideRef(slideShow);
				slideUploadDAO.create(slideShowUpload);
			}
		}
		if (imageList != null && !imageList.isEmpty()) {
			for (int i = 0; i < imageList.size(); i++) {
				addImageToDirectory(imageList.get(i), slideShow, "Other");
			}
		}

		if (docList != null && !docList.isEmpty()) {
			for (int i = 0; i < docList.size(); i++) {
				addDocumentToDirectory(docList.get(i), slideShow);
			}
		}
		
		txManager.commit(status);
	}

	public boolean deleteSlideShow(SlideShow slideShow)
			throws HibernateException, Exception {

		// List<SlideShowUpload> slideShowUploadList =
		// getSlideShowUploadBySlideShow(slideShow);
		// for(int i=0;i<slideShowUploadList.size();i++){
		// slideUploadDAO.delete(slideShowUploadList.get(i));
		// }
		boolean statusDelete = false;
		// initialize transaction status

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName(Long.toString(System.currentTimeMillis()));
		TransactionStatus status = txManager.getTransaction(def);
		try {
			slideDAO.update(slideShow);
			try {

				long start = System.currentTimeMillis();
//				injector.delete(slideShow);
				long end = System.currentTimeMillis();
				logger.debug("Delete time : " + ((float) (end - start) / 1000)
						+ " seconds");
			} catch (Exception e) {
				logger.error("Error while injecting content to Thaiquest", e);
				txManager.rollback(status);
				throw e;
			}

			uploadImgService.deleteImageFromDirectory(slideShow);
			uploadImgService.deleteDocumentFromDirectory(slideShow);

//			txManager.commit(staif (!Constant.PERMISSION_VIEW.equals(rule)) {
//				boolean admin = queryService.isMember("wpsadmins", request);
//				boolean slide = queryService.isMember("slide-admin", request);
//
//				logger.debug("slide-admin Permission: " + slide);
//				logger.debug("wpsadmins Permission: " + admin);
//
//				if (admin || slide) {
//					permission = true;
//				}
//			}tus);
			statusDelete = true;
		} catch (Exception e) {
			logger.error("Error while injecting content to Thaiquest", e);
			txManager.rollback(status);
			e.printStackTrace();
		}
		return statusDelete;
	}

	public SlideShow getSlideShowById(int slideId) throws HibernateException,
			Exception {
		SlideShow slideShow = (SlideShow) slideDAO.getSlideShowById(slideId);
		return slideShow;
	}

	public SlideShowPreview getSlideShowPreviewById(String previewId) throws HibernateException,
			Exception {
		logger.debug("preview id :"+previewId);
		SlideShowPreview slideShow = (SlideShowPreview) slidePreviewDAO.getSlideShowPreviewById(previewId);
		return slideShow;
	}


	public List<SlideShow> getListSlideShow() throws HibernateException,
			Exception {
		List<SlideShow> slideShowList = (List<SlideShow>) slideDAO.getAllSlideShow();
		logger.debug("slideShow size:"+slideShowList.size());
		
		for(int i=0; i < slideShowList.size(); i++ ){
			logger.debug("slide id:"+slideShowList.get(i).getSlideId());
		}
		
		return slideShowList;

	}
	
	public List<SlideShowUpload> getSlideShowUploadBySlideAndUploadType(SlideShow slideShow,
			String uploadType) throws HibernateException, Exception {
		List<SlideShowUpload> listSlide = slideUploadDAO.getSlideShowUploadBy(
				slideShow, uploadType);
		return listSlide;
	}

	public void addImageToDirectory(FileItem fileItem, SlideShow slideShow,
			String imagePosition) throws HibernateException, Exception {
		uploadImgService.addImageToDirectory(fileItem, slideShow, imagePosition);
	}
	
	public void addImagePreviewToDirectory(FileItem fileItem, SlideShowPreview slideShow,
			String imagePosition) throws HibernateException, Exception {
		uploadImgService.addImagePreviewToDirectory(fileItem, slideShow, imagePosition);
	}

	public void addDocumentToDirectory(FileItem fileItem, SlideShow slideShow)
			throws HibernateException, Exception {
		uploadImgService.addDocumentToDirectory(fileItem, slideShow);
	}
	
	public void addDocumentPreviewToDirectory(FileItem fileItem, SlideShowPreview slideShow)
			throws HibernateException, Exception {
		uploadImgService.addDocumentPreviewToDirectory(fileItem, slideShow);
	}

	public SlideShow getSlideShowByObject(SlideShow slideShow)
			throws HibernateException, Exception {
		SlideShow slide = (SlideShow) slideDAO.getSlideShowByObject(slideShow);
		return slide;

	}

	public int getPageNumber() throws HibernateException, Exception {
		List<SlideShow> introList = (List<SlideShow>) slideDAO
				.getAllSlideShow();
		int pageNumber = paginationService.calculatePageAll(introList);
		return pageNumber;
	}

	public List<SlideShow> getListIntroByPagination(int currentPage)
			throws HibernateException, Exception {
		List<SlideShow> slideShowList = (List<SlideShow>) slideDAO.getAllSlideShowByPagination(Integer.parseInt(pageSize),
						currentPage);
		return slideShowList;

	}

	public List<SlideViewModel> convertViewModelList(List<SlideShow> SlideList) {
		logger.debug("SlideList  " + SlideList);
		List<SlideViewModel> slideShowViewModelList = new ArrayList<SlideViewModel>();
		for (int i = 0; i < SlideList.size(); i++) {
			SlideShow slideShow = SlideList.get(i);
			logger.debug("slideShow.get(I)  " + slideShow);
			SlideViewModel slideViewModel = new SlideViewModel();

			slideViewModel.setSlideId(slideShow.getSlideId());
			slideViewModel.setImage(slideShow.getImage());
			slideViewModel.setUrl(slideShow.getUrl());
			slideViewModel.setDisplayDate(convertTimeStampToString(slideShow
					.getDisplayDate()));
			slideViewModel.setExpireDate(convertTimeStampToString(slideShow
					.getExpireDate()));
			slideShowViewModelList.add(slideViewModel);
			logger.debug("slideShowViewModelList  "
					+ slideShowViewModelList.size());
		}

		return slideShowViewModelList;

	}

	public SlideViewModel convertViewModel(SlideShow Slide) {
		SlideViewModel slideViewModel = new SlideViewModel();

		slideViewModel.setSlideId(Slide.getSlideId());
		slideViewModel.setImage(Slide.getImage());
		slideViewModel.setUrl(Slide.getUrl());
		slideViewModel.setDisplayDate(convertTimeStampToString(Slide
				.getDisplayDate()));
		slideViewModel.setExpireDate(convertTimeStampToString(Slide
				.getExpireDate()));

		return slideViewModel;

	}

	public String convertTimeStampToString(Timestamp timestamp) {
		logger.debug("timestamp " + timestamp);
		String timeString = new SimpleDateFormat("dd/MM/yyyy HH:mm")
				.format(timestamp);
		logger.debug("SimpleDateFormat " + timeString);
		return timeString;
	}

	public Timestamp convertStringToTimeStamp(String timeString) {
		Timestamp timestamp = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"dd/MM/yyyy HH:mm");
			java.util.Date parsedDate = dateFormat.parse(timeString);
			timestamp = new java.sql.Timestamp(parsedDate.getTime());
			logger.debug("SimpleDateFormat " + timestamp);
		} catch (Exception e) {
			logger.error("[ERROR] convertStringToTimeStamp :" + e.getMessage());
		}
		return timestamp;
	}


	public String setEncodindString(String data) {
		byte[] dataToUTF8;
		String changedData = null;
		try {
			dataToUTF8 = data.getBytes("ISO8859_1");
			changedData = new String(dataToUTF8, "UTF-8");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return changedData;

	}

//	public String getUser() throws NamingException, PumaException {
//		User user = applicationLogger.getPumaUtil().getCurrentUser();
//		Map<String, Object> values = applicationLogger.getPumaUtil()
//				.getUserAttributes(user);
//		String userLogin = values.get("cn").toString();
//		return userLogin;
//	}
	
//	private Attachment convertToAttachment(SlideShow slide) throws IOException {
//		
//		Set<SlideShowUpload> uploads = slide.getSlideSet();
//		
//		for(SlideShowUpload upload: uploads) {
//			
//			if("2".equals(upload.getUploadType())) {
//				
//				logger.debug("convert SlideShowUpload to Attachment");
//				
//				File file = new File(uploadDocPath + "/" + slide.getSlideId(), upload.getFileName());
//				return new Attachment(FileUtils.readFileToByteArray(file), file.getName());
//			}
//		}
//		
//		return null;
//	}

//	private List<Attachment> convertToAttachments(List<FileItem> items) {
//		
//		List<Attachment> attachs = new ArrayList<Attachment>();
//		
//		for(FileItem item: items) {
//			
//			Attachment attach = new Attachment(item.get(), item.getName());
//			attachs.add(attach);
//		}
//		
//		return attachs;
//	}
	
	public boolean removePreview(SlideShowPreview slideShowPreview) {

		boolean statusDelete = false;

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName(Long.toString(System.currentTimeMillis()));
		TransactionStatus status = txManager.getTransaction(def);
		try {

			slideShowPreview = slidePreviewDAO.getSlideShowPreviewById(slideShowPreview.getSlideId());

				// Remove upload file from DB
				List<Integer> slideUploadId = new ArrayList<Integer>();
				Set<SlideShowUploadPreview> setUpload = slideShowPreview
						.getSlideSet();
				Iterator iter = setUpload.iterator();
				while (iter.hasNext()) {
					SlideShowUploadPreview elem = (SlideShowUploadPreview) iter
							.next();
					slideUploadId.add(elem.getUploadID());
				}

				for (int i = 0; i < slideUploadId.size(); i++) {
					SlideShowUploadPreview slideUpload = getSlideShowUploadPreviewById(slideUploadId
							.get(i));
					slideUploadPreviewDAO.delete(slideUpload);
					logger.debug("delete upload id :" + slideUploadId.get(i)
							+ " Success.");
				}

				// Remove slide from db
				slidePreviewDAO.delete(slideShowPreview);
				logger.info("Remove preview slide from db success. ");

				// Remove slide image from folder
				uploadImgService
						.deleteImagePreviewFromDirectory(slideShowPreview);
				logger.info("Remove preview image from folder success. ");

				// Remove slide document from folder
				uploadImgService
						.deleteDocumentPreviewFromDirectory(slideShowPreview);
				logger.info("Remove preview document from folder success. ");

				txManager.commit(status);
				statusDelete = true;
		
			
		} catch (Exception e) {
			logger.error("Cannot remove preview ", e);
			txManager.rollback(status);
			e.printStackTrace();
		}
		return statusDelete;

	}
	
	public SlideShowUploadPreview getSlideShowUploadPreviewById(int slideUploadId)
			throws HibernateException, Exception {
		SlideShowUploadPreview slideShowUpload = slideUploadPreviewDAO.getSlideShowUploadPreviewById(slideUploadId);
		return slideShowUpload;

	}
	

	
	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public SlideDAO getSlideDAO() {
		return slideDAO;
	}

	public void setSlideDAO(SlideDAO slideDAO) {
		this.slideDAO = slideDAO;
	}

	public PaginationService getPaginationService() {
		return paginationService;
	}

	public void setPaginationService(PaginationService paginationService) {
		this.paginationService = paginationService;
	}

	public SlideUploadDAO getSlideUploadDAO() {
		return slideUploadDAO;
	}

	public void setSlideUploadDAO(SlideUploadDAO slideUploadDAO) {
		this.slideUploadDAO = slideUploadDAO;
	}


	public UploadByCommon getUploadImgService() {
		return uploadImgService;
	}

	public void setUploadImgService(UploadByCommon uploadImgService) {
		this.uploadImgService = uploadImgService;
	}

	public void setTxManager(PlatformTransactionManager txManager) {
		this.txManager = txManager;
	}
	

	public PlatformTransactionManager getTxManager() {
		return txManager;
	}

	public SlidePreviewDAO getSlidePreviewDAO() {
		return slidePreviewDAO;
	}

	public void setSlidePreviewDAO(SlidePreviewDAO slidePreviewDAO) {
		this.slidePreviewDAO = slidePreviewDAO;
	}

	public SlideUploadPreviewDAO getSlideUploadPreviewDAO() {
		return slideUploadPreviewDAO;
	}

	public void setSlideUploadPreviewDAO(SlideUploadPreviewDAO slideUploadPreviewDAO) {
		this.slideUploadPreviewDAO = slideUploadPreviewDAO;
	}
}
