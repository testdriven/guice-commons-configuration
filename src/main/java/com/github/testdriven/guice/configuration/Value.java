package com.github.testdriven.guice.configuration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation for specifying that this field shall be injected from configuration
 *
 * @author Matthias Hryniszak <padcom@gmail.com>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Value {
	/**
	 * Key to access the value
	 * @return value of the key
	 */
    String value();
}
