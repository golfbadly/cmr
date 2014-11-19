package com.xplink.random_cm.dao.hibernate;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.xplink.random_cm.dao.CommonDAOImpl;
import com.xplink.random_cm.dao.SlidePreviewDAO;
import com.xplink.random_cm.datamodel.SlideShowPreview;

public class SlidePreviewDAOHibernate extends CommonDAOImpl implements
		SlidePreviewDAO {

	public SlideShowPreview getSlideShowPreviewById(String slideId) throws HibernateException,
			Exception {
		Session session = getSession();
		Criteria cri = (Criteria) session.createCriteria(SlideShowPreview.class);
		Criterion eq = (Criterion) Restrictions.eq("slideId", slideId);
		cri.add(eq);
		SlideShowPreview slideShow = (SlideShowPreview) cri.uniqueResult();

		return slideShow;
	}

}
