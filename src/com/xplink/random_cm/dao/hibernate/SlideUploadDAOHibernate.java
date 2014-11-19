package com.xplink.random_cm.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.xplink.random_cm.dao.CommonDAOImpl;
import com.xplink.random_cm.dao.SlideUploadDAO;
import com.xplink.random_cm.datamodel.SlideShow;
import com.xplink.random_cm.datamodel.SlideShowUpload;

public class SlideUploadDAOHibernate extends CommonDAOImpl implements
		SlideUploadDAO {
	public List<SlideShowUpload> getSlideShowUploadBySlideShow(
			SlideShow slideRef) throws HibernateException, Exception {
		Session session = getSession();
		Criteria cri = (Criteria) session.createCriteria(SlideShowUpload.class);
		Criterion eq = (Criterion) Restrictions.eq("slideRef", slideRef);
		cri.add(eq);
		List<SlideShowUpload> slideShow = (List<SlideShowUpload>) cri.list();

		return slideShow;
	}

	public SlideShowUpload getSlideShowUploadById(int slideShowUploadId)
			throws HibernateException, Exception {
		Session session = getSession();
		Criteria cri = (Criteria) session.createCriteria(SlideShowUpload.class);
		Criterion eq = (Criterion) Restrictions.eq("uploadID",
				slideShowUploadId);
		cri.add(eq);
		SlideShowUpload slideShowUpload = (SlideShowUpload) cri.uniqueResult();

		return slideShowUpload;
	}

	public List<SlideShowUpload> getSlideShowUploadBy(SlideShow slideShow,
			String uploadType) throws HibernateException, Exception {

		Session session = getSession();
		Criteria cri = (Criteria) session.createCriteria(SlideShowUpload.class);
		Criterion eq1 = (Criterion) Restrictions.eq("slideRef", slideShow);
		Criterion eq2 = (Criterion) Restrictions.eq("uploadType", uploadType);
		cri.add(eq1);
		cri.add(eq2);
		List<SlideShowUpload> listUpload = (List<SlideShowUpload>) cri.list();
		return listUpload;

	}
}
