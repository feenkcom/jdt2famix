package com.feenk.jdt2famix.samples.basic;

@AnnotationTypeWithoutAttributesForType
public class ClassWithVariousAnnotations {
	
	@AnnotationTypeWithoutAttributesForAll
	public String stringAttribute;

	@AnnotationTypeWithoutAttributesForAll
	public int intAttribute;
	
	@AnnotationTypeWithoutAttributesForAll
	public void method() {}
	
	@AnnotationTypeWithOneAttributeForAll(stringAnnotationAttribute = AnnotationTypeWithOneAttributeForAll.DEFAULT)
	public void methodWithNullAnnotationValue() {}
	
	@AnnotationTypeWithoutAttributesForAll
	public void methodWithAnnotationForParameter(
			@AnnotationTypeWithoutAttributesForAll
			String parameter) {}

	@AnnotationTypeWithoutAttributesForAll
	public void methodInvokingMethodWithAnnotation() {
		method();
	}

}
