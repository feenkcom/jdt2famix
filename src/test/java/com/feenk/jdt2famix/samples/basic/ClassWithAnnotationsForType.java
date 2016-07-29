package com.feenk.jdt2famix.samples.basic;

@AnnotationTypeWithoutAttributesForType
@AnnotationTypeWithValueAttributeForType("string")
@AnnotationTypeWithMultipleAttributesForType(booleanAnnotationAttribute=true, stringAnnotationAttribute="string", classCollectionAnnotationAttribute={Object.class, String.class})
public class ClassWithAnnotationsForType {}
