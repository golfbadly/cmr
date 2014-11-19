package com.xplink.random_cm.dao.hibernate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.xplink.random_cm.dao.CommonDAOImpl;
import com.xplink.random_cm.dao.SlideDAO;
import com.xplink.random_cm.datamodel.SlideShow;
import com.xplink.random_cm.util.DateUtil;

public class SlideDAOHibernate extends CommonDAOImpl implements SlideDAO {
	private static final Logger logger = Logger
	.getLogger(SlideDAOHibernate.class);
	public List<SlideShow> getAllSlideShow() throws HibernateException,
			Exception {
		Session session = getSession();
		Date todayDate = DateUtil.getTodayDate();
		Criteria cri = (Criteria) session.createCriteria(SlideShow.class);
		cri.addOrder(Order.desc("slideId"));
		Criterion eq = (Criterion) Restrictions.eq("deleteFlag", 'N');
		cri.add(eq);
		cri.add(Restrictions.lt("displayDate", todayDate));
		cri.add(Restrictions.gt("expireDate", todayDate));
		cri.addOrder(Order.desc("displayDate"));
		List<SlideShow> object = (List<SlideShow>) cri.list();
		return object;
	}

	public List<SlideShow> getAllSlideShowByDisPlayDate()
			throws HibernateException, Exception {
		Session session = getSession();
		Criteria cri = (Criteria) session.createCriteria(SlideShow.class);
		cri.addOrder(Order.desc("slideId"));
		List<SlideShow> object = (List<SlideShow>) cri.list();
		return object;
	}

	public List<SlideShow> getAllSlideShowByPagination(int pageSize,
			int currentPage) throws HibernateException, Exception {
		Session session = getSession();
		Criteria cri = (Criteria) session.createCriteria(SlideShow.class);
		cri.addOrder(Order.desc("slideId"));
		Criterion eq = (Criterion) Restrictions.eq("deleteFlag", 'N');
		cri.add(eq);

		cri.setFirstResult(pageSize * (currentPage - 1));
		cri.setMaxResults(pageSize);

		List<SlideShow> object = (List<SlideShow>) cri.list();
		return object;
	}

	public SlideShow getSlideShowByObject(SlideShow slideShow)
			throws HibernateException, Exception {
		Session session = getSession();
		SlideShow slideShowObject = new SlideShow();
		try {

			String url = slideShow.getUrl();
			Timestamp createdDate = slideShow.getCreatedDate();
			String createdBy = slideShow.getCreatedBy();

			Conjunction conjunction = Restrictions.conjunction();
			Criteria crit = session.createCriteria(SlideShow.class);
			if (url != null) {
				Criterion criURL = Restrictions.eq("url", url);
				conjunction.add(criURL);
			}
			if (createdDate != null) {
				Criterion criCreatedDate = Restrictions.eq("createdDate", createdDate);
				conjunction.add(criCreatedDate);
			}
			if (createdBy != null) {
				Criterion criCreatedBy = Restrictions.eq("createdBy", createdBy);
				conjunction.add(criCreatedBy);
			}

			crit.add(conjunction);

			slideShowObject = (SlideShow) crit.uniqueResult();
			logger.debug("slideShowObject   :"+slideShowObject);

		} catch (Exception e) {
			logger.error("Exception ERROR : "+e.getMessage());
		}
		return slideShowObject;
	}

	public SlideShow getSlideShowById(int slideId) throws HibernateException,
			Exception {
		Session session = getSession();
		Criteria cri = (Criteria) session.createCriteria(SlideShow.class);
		Criterion eq = (Criterion) Restrictions.eq("slideId", slideId);
		cri.add(eq);
		SlideShow slideShow = (SlideShow) cri.uniqueResult();

		return slideShow;
	}

}
