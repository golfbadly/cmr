package com.xplink.random_cm.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.xplink.random_cm.datamodel.SlideShowPreview;
import com.xplink.random_cm.datamodel.SlideShowUploadPreview;

public interface SlideUploadPreviewDAO extends CommonDAO {
	public List<SlideShowUploadPreview> getSlideShowUploadPreviewBySlideShow(
			SlideShowPreview slideRef) throws HibernateException, Exception;

	public SlideShowUploadPreview getSlideShowUploadPreviewById(int slideShowUploadId)
			throws HibernateException, Exception;

	public List<SlideShowUploadPreview> getSlideShowUploadPreviewBy(SlideShowPreview slideShow,
			String uploadType) throws HibernateException, Exception;
}
