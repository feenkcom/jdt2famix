package org.moosetechnology.jdt2famix.samples.basic;

@AnnotationTypeWithoutAttributesForType
public class ClassWithVariousAnnotations {
	
	@AnnotationTypeWithoutAttributesForMethodAttribute
	public String stringAttribute;

	@AnnotationTypeWithoutAttributesForMethodAttribute
	public int intAttribute;
	
	@AnnotationTypeWithoutAttributesForMethodAttribute
	public void method() {}
	
	@AnnotationTypeWithOneAttributeForMethodAttribute(stringAnnotationAttribute = AnnotationTypeWithOneAttributeForMethodAttribute.DEFAULT)
	public void methodWithNullAnnotationValue() {}
	
}
