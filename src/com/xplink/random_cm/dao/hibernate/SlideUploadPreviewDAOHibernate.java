package com.xplink.random_cm.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.xplink.random_cm.dao.CommonDAOImpl;
import com.xplink.random_cm.dao.SlideUploadPreviewDAO;
import com.xplink.random_cm.datamodel.SlideShowPreview;
import com.xplink.random_cm.datamodel.SlideShowUploadPreview;

public class SlideUploadPreviewDAOHibernate extends CommonDAOImpl implements
		SlideUploadPreviewDAO {

	public List<SlideShowUploadPreview> getSlideShowUploadPreviewBy(
			SlideShowPreview slideShow, String uploadType)
			throws HibernateException, Exception {

		Session session = getSession();
		Criteria cri = (Criteria) session
				.createCriteria(SlideShowUploadPreview.class);
		Criterion eq1 = (Criterion) Restrictions.eq("slideRef", slideShow);
		Criterion eq2 = (Criterion) Restrictions.eq("uploadType", uploadType);
		cri.add(eq1);
		cri.add(eq2);
		List<SlideShowUploadPreview> listUpload = (List<SlideShowUploadPreview>) cri
				.list();
		return listUpload;
	}

	public SlideShowUploadPreview getSlideShowUploadPreviewById(
			int slideShowUploadId) throws HibernateException, Exception {
		Session session = getSession();
		Criteria cri = (Criteria) session
				.createCriteria(SlideShowUploadPreview.class);
		Criterion eq = (Criterion) Restrictions.eq("uploadID",
				slideShowUploadId);
		cri.add(eq);
		SlideShowUploadPreview slideShowUpload = (SlideShowUploadPreview) cri
				.uniqueResult();

		return slideShowUpload;
	}

	public List<SlideShowUploadPreview> getSlideShowUploadPreviewBySlideShow(
			SlideShowPreview slideRef) throws HibernateException, Exception {
		Session session = getSession();
		Criteria cri = (Criteria) session
				.createCriteria(SlideShowUploadPreview.class);
		Criterion eq = (Criterion) Restrictions.eq("slideRef", slideRef);
		cri.add(eq);
		List<SlideShowUploadPreview> slideShow = (List<SlideShowUploadPreview>) cri.list();

		return slideShow;
	}

}
