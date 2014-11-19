package com.xplink.util.database.hibernate;

import org.hibernate.SessionFactory;

/**
 * @author XP-LINK - Anurak Sirivoravit
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory;

	/**
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 */
	public static void setSessionFactory(SessionFactory sessionFactory) {
		HibernateUtil.sessionFactory = sessionFactory;
	}

}
