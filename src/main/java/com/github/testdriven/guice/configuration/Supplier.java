package com.github.testdriven.guice.configuration;

import org.apache.commons.configuration.Configuration;

/**
 * Proxy class for accessing values that may change during the application is running
 *
 * @author Matthias Hryniszak
 * @param <T> type of the contained value
 */
public class Supplier<T> {
	private final Class<T> clazz;
	private final String key;
	private final Configuration configuration;
	private final T defaultValue;

	public static <T> Supplier<T> of(T value) {
		return new Supplier(value.getClass(), null, null, value);
	}

	public Supplier(Class<T> clazz, String key, Configuration configuration, T defaultValue) {
		this.clazz = clazz;
		this.key = key;
		this.configuration = configuration;
		this.defaultValue = defaultValue;
	}

	/**
	 * Returns the current value of the configuration option
	 * @return current value of the configuration option
	 */
	public T get() {
		if (configuration.containsKey(key)) {
			if (clazz == String.class) {
				return (T) configuration.getString(key);
			} else if (clazz == Integer.class) {
				return (T) configuration.getInteger(key, null);
			} else if (clazz == Long.class) {
				return (T) configuration.getLong(key, null);
			} else if (clazz == Double.class) {
				return (T) configuration.getDouble(key, null);
			} else {
				throw new RuntimeException("Don't know how to convert a string to " + clazz);
			}
		} else {
			return defaultValue;
		}
	}

	Class<?> getClazz() {
		return this.clazz;
	}

	/**
	 * Accessor for the default value
	 * @return default value given in declaration
	 */
	public T getDefaultValue() {
		return this.defaultValue;
	}
}
