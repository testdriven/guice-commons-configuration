package com.github.testdriven.guice.configuration;

import org.apache.commons.configuration.Configuration;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

/**
 * Main module to include in Guice configuration to enable injection of &amp;Value annotated values
 *
 * @author Matthias Hryniszak
 */
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
