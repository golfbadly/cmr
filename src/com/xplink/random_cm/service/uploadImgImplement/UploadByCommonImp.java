package com.xplink.random_cm.service.uploadImgImplement;

import java.io.File;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.xplink.random_cm.common.Constant;
import com.xplink.random_cm.datamodel.SlideShow;
import com.xplink.random_cm.datamodel.SlideShowPreview;
import com.xplink.random_cm.datamodel.SlideShowUpload;
import com.xplink.random_cm.service.RenderImageFile;
import com.xplink.random_cm.service.uploadImg.UploadByCommon;



public class UploadByCommonImp implements UploadByCommon {
	private static final Logger logger = Logger
			.getLogger(UploadByCommonImp.class);
	private String uploadImage;
	private String uploadDoc;
	private RenderImageFile renderImageFile;

	public void addImageToDirectory(FileItem fileItem, SlideShow slideShow,
			String imagePosition) throws HibernateException, Exception {
		try {
			logger.info("[IN] addImageToDirectory");
				
			String directory = uploadImage  + "/" +slideShow.getSlideId();
			String imgName = fileItem.getName();
			String fileType = imgName.substring(imgName.lastIndexOf(".") + 1);
			
			File file = new File(directory, fileItem.getName());
			FileUtils.writeByteArrayToFile(file,  fileItem.get());	

			renderImageFile.renderSizeImage(directory+"/"+imgName, fileType);
			
			
			logger.info("[OUT] addImageToDirectory");
		} catch (Exception e) {
			logger.error("[ERROR]" + e.getMessage(),e);

		}

	}

	public void addDocumentToDirectory(FileItem fileItem, SlideShow slideShow) {
		try {
			logger.info("[IN] addImageToDirectory");
			String directory = uploadDoc + "/" + slideShow.getSlideId();


			File file = new File(directory, fileItem.getName());
			FileUtils.writeByteArrayToFile(file,  fileItem.get());	

			logger.info("[OUT] addImageToDirectory");
		} catch (Exception e) {
			logger.error("[ERROR]" + e.getMessage());

		}
	}

	public void deleteImageFromDirectory(SlideShow slideShow) {
		try {
			File image = new File(uploadImage + "/" + slideShow.getSlideId());
			FileUtils.deleteDirectory(image);
		} catch (Exception e) {
			logger.error("DELETE DIR IMAGE :" + slideShow.getSlideId());
			logger.error("ERROR :" + e.getMessage());
		}

	}

	public void deleteDocumentFromDirectory(SlideShow slideShow)
			throws HibernateException, Exception {
		try {
			File doc = new File(uploadDoc + "/" + slideShow.getSlideId());
			FileUtils.deleteDirectory(doc);
		} catch (Exception e) {
			logger.error("DELETE DIR DOC :" + slideShow.getSlideId());
			logger.error("ERROR :" + e.getMessage());
		}

	}
	

	public void deleteDocumentFromDirectoryById(SlideShowUpload slideShowUpload)
			throws HibernateException, Exception {
		try {
			File doc = new File(uploadDoc + "/" + slideShowUpload.getSlideRef().getSlideId()+"/"+slideShowUpload.getFileName());
			FileUtils.forceDelete(doc);
			logger.info("COMPLETE delete");
		} catch (Exception e) {
			logger.error("DELETE FILE DOC :" + slideShowUpload.getFileName());
			logger.error("ERROR :", e);
		}
		
	}

	public void deleteImageFromDirectoryById(SlideShowUpload slideShowUpload)
			throws HibernateException, Exception {
		try {
			File img = new File(uploadImage + "/" + slideShowUpload.getSlideRef().getSlideId()+"/"+slideShowUpload.getFileName());
			FileUtils.forceDelete(img);
			logger.info("COMPLETE delete");
		} catch (Exception e) {
			logger.error("DELETE FILE IMG :" + slideShowUpload.getFileName());
			logger.error("ERROR :", e);
		}	
	}
	
	/*------------------- Preview-------------------*/
	public void addImagePreviewToDirectory(FileItem fileItem, SlideShowPreview slideShow,
			String imagePosition) throws HibernateException, Exception {
		try {
			logger.info("[IN] addImagePreviewToDirectory");
			String directory = uploadImage+"-"+Constant.PREVIEW + "/"+ slideShow.getSlideId() + "/"
					+ imagePosition;
		
			String imgName = fileItem.getName();
			String fileType = imgName.substring(imgName.lastIndexOf(".") + 1);
			
			
			
			
			File file = new File(directory, fileItem.getName());
			FileUtils.writeByteArrayToFile(file,  fileItem.get());	

			renderImageFile.renderSizeImage(directory+"/"+imgName, fileType);

			logger.info("[OUT] addImagePreviewToDirectory");
		} catch (Exception e) {
			logger.error("[ERROR]" + e.getMessage());

		}

	}

	public void addDocumentPreviewToDirectory(FileItem fileItem, SlideShowPreview slideShow) {
		try {
			logger.info("[IN] addDocumentPreviewToDirectory");
			String directory = uploadDoc+"-"+Constant.PREVIEW +"/" + slideShow.getSlideId();
			File file = new File(directory, fileItem.getName());
			FileUtils.writeByteArrayToFile(file,  fileItem.get());	

			logger.info("[OUT] addDocumentPreviewToDirectory");
		} catch (Exception e) {
			logger.error("[ERROR]" + e.getMessage());

		}
	}
	
	public void deleteImagePreviewFromDirectory(SlideShowPreview slideShow) {
		try {
			logger.debug("file image :"+uploadImage+"-"+Constant.PREVIEW  + "/" + slideShow.getSlideId());
			File image = new File(uploadImage+"-"+Constant.PREVIEW  + "/" + slideShow.getSlideId());
			FileUtils.deleteDirectory(image);
		} catch (Exception e) {
			logger.error("DELETE DIR IMAGE :" + slideShow.getSlideId());
			logger.error("ERROR :" + e.getMessage());
		}

	}
	
	public void deleteDocumentPreviewFromDirectory(SlideShowPreview slideShow)
			throws HibernateException, Exception {
		try {
			logger.debug("file document:"+uploadDoc+"-"+Constant.PREVIEW + "/" + slideShow.getSlideId());
			File doc = new File(uploadDoc+"-"+Constant.PREVIEW + "/" + slideShow.getSlideId());
			FileUtils.deleteDirectory(doc);
		} catch (Exception e) {
			logger.error("DELETE DIR DOC :" + slideShow.getSlideId());
			logger.error("ERROR :" + e.getMessage());
		}

	}

	public String getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(String uploadImage) {
		this.uploadImage = uploadImage;
	}

	public String getUploadDoc() {
		return uploadDoc;
	}

	public void setUploadDoc(String uploadDoc) {
		this.uploadDoc = uploadDoc;
	}

	public RenderImageFile getRenderImageFile() {
		return renderImageFile;
	}

	public void setRenderImageFile(RenderImageFile renderImageFile) {
		this.renderImageFile = renderImageFile;
	}

}
