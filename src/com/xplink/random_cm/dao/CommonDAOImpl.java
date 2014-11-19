package com.xplink.random_cm.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.SessionFactoryUtils;




/**
 * Provide an implementation of {@link CommonDAO}
 * @author XP-LINK - jedsada@xp-link.com
 */
public class CommonDAOImpl implements CommonDAO {

	private static final Logger logger = LogManager.getLogger(CommonDAOImpl.class);

	protected SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Get session.
	 * @return Session
	 */
	public Session getSession() {
		return SessionFactoryUtils.getSession(getSessionFactory(), false);
	}

	public Object create(Object obj) throws HibernateException, Exception {
		logger.debug("IN:create: " + obj);

		Session session = getSession();
		//Transaction tx = session.beginTransaction();

		session.save(obj);

		//tx.commit();

		logger.debug("OUT:create:" + obj);
		return obj;
	}

	public Object delete(Object obj) throws HibernateException, Exception {
		logger.debug("IN:delete: " + obj);

		Session session = getSession();
		//Transaction tx = session.beginTransaction();

		session.delete(obj);

		//tx.commit();

		logger.debug("OUT:delete:");
		return obj;
	}

	public Object update(Object obj) throws HibernateException, Exception {
		logger.debug("IN:update: " + obj);

		Session session = getSession();
		//Transaction tx = session.beginTransaction();

		session.update(obj);

		//tx.commit();

		logger.debug("OUT:update:"+obj);
		return obj;
	}

	

	
 }
