/**
 * 
 */
package com.xplink.util.database.hibernate;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * @author Sorawit Laosinchai
 */
public class AutoReconnectDatabaseFilter extends DelegatingFilterProxy {

	private static Logger logger = LogManager.getLogger(AutoReconnectDatabaseFilter.class);

	private long timeout;

	private String sql;

	private long lastTime = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.filter.DelegatingFilterProxy#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		long currentTime = System.currentTimeMillis();

		if (currentTime - lastTime >= getTimeout()) {
			try {
				Session session = SessionFactoryUtils.getSession(HibernateUtil.getSessionFactory(),
						true);
				SQLQuery query = session.createSQLQuery(getSql());
				query.uniqueResult();
			}
			catch (Exception e) {
				logger.error("Cannot excute test query", e);
			}
		}

		lastTime = currentTime;

		filterChain.doFilter(request, response);
	}

	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param sql the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * @return the timeout
	 */
	public long getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

}
