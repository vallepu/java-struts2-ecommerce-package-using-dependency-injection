package org.webhop.ywdc.util;



import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.webhop.ywdc.exceptions.InfrastructureException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.naming.*;

/**
 * Large Hibernate helper class, handles SessionFactory, Session and Transaction.
 *
 * Uses a  initializer for the initial SessionFactory creation
 * and holds Session and Transactions in thread local variables. All
 * exceptions are wrapped in an unchecked InfrastructureException.
 *
 * @version 1.2
 * @author Siegfried Bolz (www.jdevelop.eu)
 */
public class HibernateUtil 
{
        /** Logger. */
	private  Log log = LogFactory.getLog(HibernateUtil.class);

	/** configuration. */
	private  Configuration configuration;

	/** Session Factory */
	private  SessionFactory sessionFactory;

	/** JNDI name of sessionfactory. */
	private  final String JNDI_SESSIONFACTORY = "java:hibernate/HibernateFactory";

	/** If running unit tests set to true. */
	private  boolean offlineMode = true;

	/** threadlocal. */
	private  final ThreadLocal threadSession = new ThreadLocal();

	/** threadlocal. */
	private  final ThreadLocal threadTransaction = new ThreadLocal();

	/** threadlocal. */
	private  final ThreadLocal threadInterceptor = new ThreadLocal();

	/** Interceptor class */
	private  final String INTERCEPTOR_CLASS = "hibernate.util.interceptor_class";

	/**
	 * Create the initial SessionFactory from hibernate.xml.cfg or JNDI).
	 * #### Use this Function to initialize Hibernate! ####
	 *
	 * @param offlineMode true=hibernate.cfg.xml , false=JNDI
	 */
	public  void Configure(boolean offlineMode) {
		log.debug("HibernateUtil.Configure() - Trying to initialize Hibernate.");
		try {
			// Use hibernate.cfg.xml (true) or JNDI (false)
			setOfflineMode(offlineMode);
			sessionFactory = getSessionFactory();

		} catch (Throwable x) {
			// We have to catch Throwable, otherwise we will miss
			// NoClassDefFoundError and other subclasses of Error
			log.error("HibernateUtil.Configure() - Building SessionFactory failed.", x);
			throw new ExceptionInInitializerError(x);
		}
	}

    /**
     * Use hibernate.cfg.xml (true) to create sessionfactory or bound
     * sessionfactory to JNDI (false)
     */
    public  void setOfflineMode(boolean mode)
    {
    	if (mode==true)
    	    log.debug("HibernateUtil.setOfflineMode() - Setting mode to hibernate.cfg.xml .");
    	else
    		log.debug("HibernateUtil.setOfflineMode() - Setting mode to JNDI.");

        offlineMode = mode;
    }

        /**
	 * Returns the SessionFactory used for this  class. If offlineMode has
	 * been set then we use hibernate.cfg.xml to create sessionfactory, if not
	 * then we use sessionfactory bound to JNDI.
	 *
	 * @return SessionFactory
	 */
	public  SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			if (offlineMode == true) {
				log.debug("HibernateUtil.getSessionFactory() - Using hibernate.cfg.xml to create a SessionFactory");
				try {
					configuration = new Configuration();
					sessionFactory = configuration.configure().buildSessionFactory();
				} catch (HibernateException x) {
					throw new InfrastructureException("HibernateUtil.getSessionFactory() - Error creating SessionFactory with hibernate.cfg.xml .",x);
				}

			} else {
				log.debug("HibernateUtil.getSessionFactory() - Using JDNI to create a SessionFactory");
				try {
					Context ctx = new InitialContext();
					sessionFactory = (SessionFactory) ctx.lookup(JNDI_SESSIONFACTORY);
				} catch (NamingException x) {
					throw new InfrastructureException("HibernateUtil.getSessionFactory() - Error creating JNDI-SessionFactory.",x);
				}
			}
		}

		if (sessionFactory == null) {
			throw new IllegalStateException("HibernateUtil.getSessionFactory() - SessionFactory not available.");
		}
		return sessionFactory;
	}

	/**
	 * Sets the given SessionFactory.
	 *
	 * @param sessionFactory
	 */
	public  void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

        /**
	 * Returns the original Hibernate configuration.
	 *
	 * @return Configuration
	 */
	public  Configuration getConfiguration() {
		return configuration;
	}

        /**
	 * Rebuild the SessionFactory with the  Configuration.
	 *
	 */
	public  void rebuildSessionFactory() throws InfrastructureException {
		log.debug("HibernateUtil.rebuildSessionFactory() - Rebuilding the SessionFactory with the  Configuration.");
		synchronized (sessionFactory) {
			try {
				sessionFactory = getConfiguration().buildSessionFactory();
			} catch (Exception x) {
				throw new InfrastructureException("HibernateUtil.rebuildSessionFactory() - Error rebuilding SessionFactory with the  Configuration",x);
			}
		}
	}

        /**
	 * Rebuild the SessionFactory with the given Hibernate Configuration.
	 *
	 * @param cfg
	 */
	public  void rebuildSessionFactory(Configuration cfg) throws InfrastructureException {
		log.debug("HibernateUtil.rebuildSessionFactory() - Rebuilding the SessionFactory from the given Hibernate Configuration.");
		synchronized (sessionFactory) {
			try {
				if (sessionFactory != null && !sessionFactory.isClosed())
					sessionFactory.close();

				sessionFactory = cfg.buildSessionFactory();
				configuration = cfg;
			} catch (Exception x) {
				throw new InfrastructureException("HibernateUtil.rebuildSessionFactory() - Error rebuilding the SessionFactory with the given Hibernate Configuration",x);
			}
		}
	}

        /**
	 * Destroy this SessionFactory and release all resources (caches, connection
	 * pools, etc).
	 *
	 * @author Siegfried Bolz
	 * @param cfg
	 */
	public  void closeSessionFactory() throws InfrastructureException {
		synchronized (sessionFactory) {
			try {
				log.debug("HibernateUtil.closeSessionFactory() - Destroy the current SessionFactory.");
				sessionFactory.close();
				// Clear  variables
				configuration = null;
				sessionFactory = null;
			} catch (Exception x) {
				throw new InfrastructureException("HibernateUtil.closeSessionFactory() - Error destroying the current SessionFactory",x);
			}
		}
	}

        /**
	 * Retrieves the current Session local to the thread. <p/>
	 *
	 * If no Session is open, opens a new Session for the running thread.
	 *
	 * @return Session
	 */
	public  Session getSession() throws InfrastructureException {
		Session s = (Session) threadSession.get();
		try {
			if (s == null) {
				log.debug("HibernateUtil.getSession() - Opening new Session for this thread.");
				if (getInterceptor() != null) {
					log.debug("HibernateUtil.getSession() - Using interceptor: "+ getInterceptor().getClass());
					s = getSessionFactory().openSession(getInterceptor());
				} else {
					s = getSessionFactory().openSession();
				}
				threadSession.set(s);
			}
		} catch (HibernateException x) {
			throw new InfrastructureException("HibernateUtil.getSession() - Error retrieving/creating a session.",x);
		}
		return s;
	}

        /**
	 * Closes the Session local to the thread.
	 */
	public  void closeSession() throws InfrastructureException {
		
		
		try {
			Session s = (Session) threadSession.get();
			
			threadSession.set(null);
			if (s != null && s.isOpen()) {
				log.debug("HibernateUtil.closeSession() - Closing Session of this thread.");
				s.close();
			}
		} catch (HibernateException x) {
			throw new InfrastructureException("HibernateUtil.closeSession() - Error closing the session.",x);
		}
	}

        /**
	 * Start a new database transaction.
	 */
	public  void beginTransaction() throws InfrastructureException {
		Transaction tx = (Transaction) threadTransaction.get();
		try {
			if (tx == null) {
				log.debug("HibernateUtil.beginTransaction() - Starting new database transaction in this thread.");
				tx = getSession().beginTransaction();
				threadTransaction.set(tx);
			}
		} catch (HibernateException x) {
			throw new InfrastructureException("HibernateUtil.beginTransaction() - Error starting a new database transaction.",x);
		}
	}

        /**
	 * Commit the database transaction.
	 */
	public  void commitTransaction() throws InfrastructureException {
		Transaction tx = (Transaction) threadTransaction.get();
		try {
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				log.debug("HibernateUtil.commitTransaction() - Committing database transaction of this thread.");
				tx.commit();
			}
			threadTransaction.set(null);
		} catch (HibernateException x) {
			rollbackTransaction();
			throw new InfrastructureException("HibernateUtil.commitTransaction() - Error commiting the database transaction.",x);
		}
	}

        /**
	 * Rollback the database transaction.
	 */
	public  void rollbackTransaction() throws InfrastructureException {
		Transaction tx = (Transaction) threadTransaction.get();
		try {
			threadTransaction.set(null);
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				log.debug("HibernateUtil.rollbackTransaction() - Tyring to rollback database transaction of this thread.");
				tx.rollback();
			}
		} catch (HibernateException x) {
			throw new InfrastructureException("HibernateUtil.rollbackTransaction() - Error rolling back the database transaction.",x);
		} finally {
			closeSession();
		}
	}

        /**
	 * Reconnects a Hibernate Session to the current Thread.
	 *
	 * @param session The Hibernate Session to be reconnected.
	 */
	public  void reconnect(Session session) throws InfrastructureException {
		log.debug("HibernateUtil.reconnect() - Reconnecting Hibernate Session to the current Thread.");
		try {
			session.reconnect(session.connection());
			threadSession.set(session);
		} catch (HibernateException x) {
			throw new InfrastructureException("HibernateUtil.reconnect() - Error reconnectin to the given session.",x);
		}
	}

       /**
	 * Disconnect and return Session from current Thread.
	 *
	 * @return Session the disconnected Session
	 */
	public  Session disconnectSession() throws InfrastructureException {
		log.debug("HibernateUtil.disconnectSession() - Disconnecting Session from current Thread.");
		Session session = getSession();
		try {
			threadSession.set(null);
			if (session.isConnected() && session.isOpen()) {
				session.disconnect();
			}
		} catch (HibernateException x) {
			throw new InfrastructureException("HibernateUtil.disconnectSession() - Error disconnecting session from current thread.",x);
		}
		return session;
	}

       /**
	 * Register a Hibernate interceptor with the current thread.
	 *
	 * Every Session opened is opened with this interceptor after registration.
	 * Has no effect if the current Session of the thread is already open,
	 * effective on next close()/getSession().
	 */
	public  void registerInterceptor(Interceptor interceptor) {
		threadInterceptor.set(interceptor);
	}

        /**
	 * Get Hibernate interceptor.
	 *
	 * @return Interceptor
	 */
	private  Interceptor getInterceptor() {
		Interceptor interceptor = (Interceptor) threadInterceptor.get();
		return interceptor;
	}

        /**
	 * Resets global interceptor to default state.
	 */
	public  void resetInterceptor() {
		log.debug("HibernateUtil.resetInterceptor() - Resetting global interceptor to configuration setting");
		setInterceptor(configuration, null);
	}

        /**
	 * Either sets the given interceptor on the configuration or looks it up
	 * from configuration if null.
	 */
	private  void setInterceptor(Configuration configuration, Interceptor interceptor) {
		String interceptorName = configuration.getProperty(INTERCEPTOR_CLASS);
		if (interceptor == null && interceptorName != null) {
			try {
				log.debug("HibernateUtil.setInterceptor() - Configuring interceptor.");
				Class interceptorClass = HibernateUtil.class.getClassLoader().loadClass(interceptorName);
				interceptor = (Interceptor) interceptorClass.newInstance();
			} catch (Exception x) {
				throw new RuntimeException("HibernateUtil.setInterceptor() - Error, could not configure interceptor: " + interceptorName, x);
			}
		}
		if (interceptor != null) {
			configuration.setInterceptor(interceptor);
		} else {
			configuration.setInterceptor(EmptyInterceptor.INSTANCE);
		}
	}

} // .EOF
