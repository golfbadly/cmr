package com.xplink.random_cm.dao;

import org.hibernate.HibernateException;

import com.xplink.random_cm.datamodel.SlideShowPreview;

public interface SlidePreviewDAO extends CommonDAO{
	public SlideShowPreview getSlideShowPreviewById(String slideId) throws HibernateException,
	Exception;
}
