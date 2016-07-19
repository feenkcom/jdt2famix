package org.moosetechnology.jdt2famix.samples.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE})
public @interface SimpleAnnotationTypeForType {
	boolean booleanAnnotationAttribute() default true;
	String stringAnnotationAttribute() default "";
}
