package com.github.testdriven.guice;

import java.lang.reflect.Field;

import org.apache.commons.configuration.Configuration;

import com.google.inject.MembersInjector;

class ValueMemberInjector<T> implements MembersInjector<T> {
	private final Field field;
	private final String key;
	private final Configuration configuration;

	public ValueMemberInjector(Field field, String key, Configuration configuration) {
		this.field = field;
		this.key = key;
		this.configuration = configuration;
		field.setAccessible(true);
	}

	@Override
	public void injectMembers(T target) {
		try {
			if (configuration.containsKey(key)) {
				if (field.getType().equals(String.class)) {
					field.set(target, configuration.getString(key));
				} else if (field.getType() == Integer.class) {
					field.set(target, configuration.getInteger(key, null));
				} else if (field.getType() == int.class) {
					field.set(target, configuration.getInt(key, 0));
				} else if (field.getType() == Long.class) {
					field.set(target, configuration.getLong(key, null));
				} else if (field.getType() == long.class) {
					field.set(target, configuration.getLong(key, 0));
				} else if (field.getType() == Double.class) {
					field.set(target, configuration.getDouble(key));
				} else if (field.getType() == double.class) {
					field.set(target, configuration.getDouble(key, null));
				} else if (field.getType() == Float.class) {
					field.set(target, configuration.getFloat(key));
				} else if (field.getType() == float.class) {
					field.set(target, configuration.getFloat(key, null));
				} else {
					throw new RuntimeException("Don't know how to convert a string to " + field.getType() + " on " + field.getDeclaringClass().getName() + "." + field.getName());
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
