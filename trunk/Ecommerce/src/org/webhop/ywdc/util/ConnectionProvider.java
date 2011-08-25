package org.webhop.ywdc.util;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ConnectionProvider implements Provider<HibernateConnection> {

	private final HibernateUtil hibernateUtil;
	
	@Inject
	ConnectionProvider(HibernateUtil hibernateUtil)
	{
		this.hibernateUtil = hibernateUtil;
	}
	@Override
	public HibernateConnection get() {

		HibernateConnection connection = new HibernateConnection(hibernateUtil);
		return connection;
	}

}
