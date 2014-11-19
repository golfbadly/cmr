package com.xplink.random_cm.dao;

import java.util.List;
import org.hibernate.HibernateException;

import com.xplink.random_cm.datamodel.SlideShow;

public interface SlideDAO extends CommonDAO {
	public List<SlideShow> getAllSlideShow() throws HibernateException,
			Exception;

	public SlideShow getSlideShowByObject(SlideShow slideShow)
			throws HibernateException, Exception;

	public SlideShow getSlideShowById(int slideId) throws HibernateException,
			Exception;

	public List<SlideShow> getAllSlideShowByPagination(int pageSize,
			int currentPage) throws HibernateException, Exception;
}
