package com.feenk.jdt2famix.samples.basic;

@AnnotationTypeWithValueAttributeForType(AnnotationTypeWithValueAttributeForType.DEFAUlT_FOR_TYPE + "type")
public class ClassWithVariousAnnotations {
	
	@AnnotationTypeWithoutAttributesForAll
	public static String staticStringAttribute;

	@AnnotationTypeWithoutAttributesForAll
	public static String staticStringMethod() { return "staticMethod"; }
	
	@AnnotationTypeWithoutAttributesForAll
	public String stringAttribute;

	@AnnotationTypeWithoutAttributesForAll
	public int intAttribute;

	@AnnotationTypeWithOneAttributeForAll(stringAnnotationAttribute = "attribute" + AnnotationTypeWithOneAttributeForAll.DEFAULT)
	public int intAttributeWithExpressionAnnotationValue;
	
	@AnnotationTypeWithoutAttributesForAll
	public void method() {}
	
	@AnnotationTypeWithOneAttributeForAll(stringAnnotationAttribute = AnnotationTypeWithOneAttributeForAll.DEFAULT)
	public void methodWithConstantAnnotationValue() {}

	@AnnotationTypeWithOneAttributeForAll(stringAnnotationAttribute = "method" + AnnotationTypeWithOneAttributeForAll.DEFAULT)
	public void methodWithExpressionAnnotationValue() {}

	@AnnotationTypeWithoutAttributesForAll
	public void methodWithAnnotationForParameter(
			@AnnotationTypeWithoutAttributesForAll
			String parameter) {}

	@AnnotationTypeWithoutAttributesForAll
	public void methodInvokingMethodWithAnnotation() {
		method();
	}
	
	

}
