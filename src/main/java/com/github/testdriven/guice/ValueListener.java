package com.github.testdriven.guice;

import java.lang.reflect.Field;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

class ValueListener implements TypeListener {
	private static final Logger log = LoggerFactory.getLogger(ValueListener.class);

	private final Configuration configuration;

	public ValueListener(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
		for (Field field : type.getRawType().getDeclaredFields()) {
			if (field.isAnnotationPresent(Value.class)) {
				Value annotation = field.getDeclaredAnnotation(Value.class);
				if (log.isDebugEnabled()) {
					log.debug("ValueListener on " + field.getName() + " of " + field.getDeclaringClass().getName() + " - ACCEPTED");
				}
				encounter.register(new ValueMemberInjector(field, annotation.value(), configuration));
			} else if (log.isDebugEnabled()) {
				log.debug("ValueListener on " + field.getName() + " of " + field.getDeclaringClass().getName() + " - SKIPPED");
			}
		}
	}
}
