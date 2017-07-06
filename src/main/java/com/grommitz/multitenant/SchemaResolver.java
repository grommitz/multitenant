package com.grommitz.multitenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Logger;

public class SchemaResolver implements CurrentTenantIdentifierResolver {

	private String tenantIdentifier = "client1";
	private Random random = new SecureRandom();
	private static final Logger logger = Logger.getLogger(SchemaResolver.class.getSimpleName());

	@Override
	public String resolveCurrentTenantIdentifier() {
		String schema =  (random.nextInt(2) == 1 ? "client1" : "client2");
		logger.info("using schema = " + schema);
		return schema;
	}
	@Override
	public boolean validateExistingCurrentSessions() {
		return false;
	}
	public void setTenantIdentifier(String tenantIdentifier) {
		this.tenantIdentifier = tenantIdentifier;
	}
}