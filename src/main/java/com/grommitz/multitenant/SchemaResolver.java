package com.grommitz.multitenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Logger;

public class SchemaResolver implements CurrentTenantIdentifierResolver {

	final String[] SCHEMAS = new String[]{"client1db", "client2db", "client3db"};

	private String tenantIdentifier = "client1";
	private Random random = new SecureRandom();
	private static final Logger logger = Logger.getLogger(SchemaResolver.class.getSimpleName());

	@Override
	public String resolveCurrentTenantIdentifier() {
		int num =  random.nextInt(SCHEMAS.length) + 1;
		String schema = "client" + num + "db";
		logger.info("using schema = " + schema);
		return schema;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return false;
	}

	public void setTenantIdentifier(String tenantIdentifier) {
		logger.info("setting the tenant identifier to " + tenantIdentifier);
		this.tenantIdentifier = tenantIdentifier;
	}
}