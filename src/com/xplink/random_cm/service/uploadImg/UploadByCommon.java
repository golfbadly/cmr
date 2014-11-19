package com.xplink.random_cm.service.uploadImg;

import org.apache.commons.fileupload.FileItem;
import org.hibernate.HibernateException;

import com.xplink.random_cm.datamodel.SlideShow;
import com.xplink.random_cm.datamodel.SlideShowPreview;
import com.xplink.random_cm.datamodel.SlideShowUpload;

public interface UploadByCommon {
	public void addImageToDirectory(FileItem fileItem, SlideShow slideShow,
			String imagePosition) throws HibernateException, Exception;

	public void addDocumentToDirectory(FileItem fileItem, SlideShow slideShow)
			throws HibernateException, Exception;

	public void deleteImageFromDirectory(SlideShow slideShow)
	throws HibernateException, Exception;

	public void deleteImageFromDirectoryById(SlideShowUpload slideShowUpload)
	throws HibernateException, Exception;

	public void deleteDocumentFromDirectory(SlideShow slideShow)
			throws HibernateException, Exception;
	
	public void deleteDocumentFromDirectoryById(SlideShowUpload slideShowUpload)
	throws HibernateException, Exception;
	
	public void addImagePreviewToDirectory(FileItem fileItem, SlideShowPreview slideShow,
			String imagePosition) throws HibernateException, Exception;

	public void addDocumentPreviewToDirectory(FileItem fileItem, SlideShowPreview slideShow)
			throws HibernateException, Exception;
	
	public void deleteImagePreviewFromDirectory(SlideShowPreview slideShow);
	public void deleteDocumentPreviewFromDirectory(SlideShowPreview slideShow)
	throws HibernateException, Exception;

}
