package com.feenk.jdt2famix.samples.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE})
public @interface AnnotationTypeWithMultipleAttributesForType {
	boolean booleanAnnotationAttribute() default true;
	String stringAnnotationAttribute() default "";
	Class <?> classAnnotationAttribute() default Object.class;
	Class <?>[] classCollectionAnnotationAttribute() default {};
	AnnotationTypeWithoutAttributesForAll annotationAnnotationAttribute() default @AnnotationTypeWithoutAttributesForAll;
}
