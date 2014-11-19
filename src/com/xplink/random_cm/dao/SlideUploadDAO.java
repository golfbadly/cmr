package com.xplink.random_cm.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.xplink.random_cm.datamodel.SlideShow;
import com.xplink.random_cm.datamodel.SlideShowUpload;

public interface SlideUploadDAO extends CommonDAO {
	public List<SlideShowUpload> getSlideShowUploadBySlideShow(
			SlideShow slideRef) throws HibernateException, Exception;

	public SlideShowUpload getSlideShowUploadById(int slideShowUploadId)
			throws HibernateException, Exception;
	
	public List<SlideShowUpload> getSlideShowUploadBy(SlideShow slideShow,
			String uploadType) throws HibernateException, Exception;
}
