package com.feenk.jdt2famix.samples.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation Javadoc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER })
public @interface AnnotationWithComments {
	
	/**
	 * Attribute javadoc
	 */
	String stringAnnotationAttribute();
}
