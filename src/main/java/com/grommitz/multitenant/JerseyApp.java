package com.grommitz.multitenant;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by martin on 23/06/17.
 */
@ApplicationPath("rest")
public class JerseyApp extends ResourceConfig {
	public JerseyApp() {
		super();
		packages(true, "com.grommitz.multitenant");
	}

}
