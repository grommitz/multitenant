package com.grommitz.multitenant;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MultiTenantProvider implements MultiTenantConnectionProvider, ServiceRegistryAwareService {

	private static final Logger logger = Logger.getLogger(MultiTenantProvider.class.getSimpleName());
	private DataSource dataSource;
	private final String dataSourceJndiName = "java:global/myds";
	public static final String MASTERDB = "multitenantdb";

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

	@Override
	public void injectServices(ServiceRegistryImplementor serviceRegistry) {
		try {
			final Context init = new InitialContext();
			dataSource = (DataSource) init.lookup(dataSourceJndiName);
		} catch (final NamingException e) {
			throw new RuntimeException(e);
		}
	}
	@SuppressWarnings("rawtypes")
	@Override
	public boolean isUnwrappableAs(Class clazz) {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> clazz) {
		return null;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		final Connection connection = dataSource.getConnection();
		return connection;
	}

	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
		logger.info("getting connection...");
		final Connection connection = getAnyConnection();
		logger.info("USE " + tenantIdentifier);
		try {
			connection.createStatement().execute("USE '" + tenantIdentifier + "'");
		} catch (final SQLException e) {
			throw new HibernateException("Error trying to alter schema [" + tenantIdentifier + "]", e);
		}
		return connection;
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		try {
			connection.createStatement().execute("USE " + MASTERDB);
		} catch (final SQLException e) {
			throw new HibernateException("Error trying to alter schema [" + MASTERDB + "]", e);
		}
		connection.close();
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		releaseAnyConnection(connection);
	}
}
