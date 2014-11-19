package com.xplink.util.database.hibernate;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author XP-LINK - Anurak Sirivoravit
 */
public class AutoFlushOpenSessionInViewFilter extends OpenSessionInViewFilter {
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {

		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			logger.debug("Access to:" + httpRequest.getRequestURI());
		}

		SessionFactory sessionFactory = lookupSessionFactory(request);
		boolean participate = false;

		if (isSingleSession()) {
			// single session mode
			if (TransactionSynchronizationManager.hasResource(sessionFactory)) {
				// Do not modify the Session: just set the participate flag.
				participate = true;
			}
			else {
				logger.debug("Opening single Hibernate Session in OpenSessionInViewFilter");
				Session session = getSession(sessionFactory);
				TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(
						session));
			}
		}
		else {
			// deferred close mode
			if (SessionFactoryUtils.isDeferredCloseActive(sessionFactory)) {
				// Do not modify deferred close: just set the participate flag.
				participate = true;
			}
			else {
				SessionFactoryUtils.initDeferredClose(sessionFactory);
			}
		}

		try {
			long before = System.currentTimeMillis();
			filterChain.doFilter(request, response);
			long after = System.currentTimeMillis();
			double processTime = ((double) (after - before)) / 1000;
			logger.debug("process time:" + processTime + " second(s)");

		}
		finally {
			if (!participate) {
				if (isSingleSession()) {
					// single session mode
					SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager
							.unbindResource(sessionFactory);
					logger.debug("Closing single Hibernate Session in OpenSessionInViewFilter");
					closeSession(sessionHolder.getSession(), sessionFactory);
				}
				else {
					// deferred close mode
					SessionFactoryUtils.processDeferredClose(sessionFactory);
				}
			}
		}
	}

	/**
	 * @see org.springframework.orm.hibernate.support.OpenSess
	 *      ionInViewFilter#getSession(net.sf.hibernate.Sessio nFactory)
	 */
	protected Session getSession(SessionFactory sessionFactory)
			throws DataAccessResourceFailureException {
		Session session = super.getSession(sessionFactory);

		session.setFlushMode(FlushMode.AUTO);

		return session;
	}

	@Override
	protected void closeSession(Session session, SessionFactory sessionFactory) {
		Transaction transaction = session.getTransaction();
		if (transaction.isActive()) {
			transaction.commit();
		}
		super.closeSession(session, sessionFactory);
	}
}
