package com.xplink.random_cm.dao;


import org.hibernate.HibernateException;

/**
 * An interface for common data access method.
 * @author XP-LINK - jedsada@xp-link.com
 */
public interface CommonDAO {
	
	/**
	 * Insert record in DB.
	 * @param obj to insert
	 * @return Object.
	 * @throws HibernateException
	 * @throws Exception
	 */
	public Object create(Object obj) throws HibernateException, Exception;

	/**
	 * Update record in DB.
	 * @param obj to update
	 * @return Object.
	 * @throws HibernateException
	 * @throws Exception
	 */
	public Object update(Object obj) throws HibernateException, Exception;

	/**
	 * Delete record in DB.
	 * @param obj to delete
	 * @return Object.
	 * @throws HibernateException
	 * @throws Exception
	 */
	public Object delete(Object obj) throws HibernateException, Exception;

	
	
}
