package com.github.testdriven.guice.configuration;

import org.apache.commons.configuration.Configuration;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class CommonsConfigurationModule extends AbstractModule {
	private final Configuration configuration;

	public CommonsConfigurationModule(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	protected void configure() {
		bindListener(Matchers.any(), new ValueListener(configuration));
	}
}
