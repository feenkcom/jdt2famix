package com.feenk.jdt2famix.injava.multipleSamples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.feenk.jdt2famix.JavaFiles;
import com.feenk.jdt2famix.model.famix.AnnotationInstance;
import com.feenk.jdt2famix.model.famix.AnnotationInstanceAttribute;
import com.feenk.jdt2famix.model.famix.AnnotationType;
import com.feenk.jdt2famix.model.famix.NamedEntity;
import com.feenk.jdt2famix.model.famix.Type;
import com.feenk.jdt2famix.samples.basic.AnnotationTypeWithMultipleAttributesForType;
import com.feenk.jdt2famix.samples.basic.AnnotationTypeWithValueAttributeForType;
import com.feenk.jdt2famix.samples.basic.AnnotationTypeWithoutAttributesForType;
import com.feenk.jdt2famix.samples.basic.ClassWithAnnotationsForType;

public class ClassWithAnnotationForTypeTest extends
		MultipleSamplesTestCase {

	@Override
	protected void sampleClassesIn(JavaFiles javaFiles) {
		javaFiles.oneJavaFile(this.fileNameFor(ClassWithAnnotationsForType.class));
		javaFiles.oneJavaFile(this.fileNameFor(AnnotationTypeWithMultipleAttributesForType.class));
		javaFiles.oneJavaFile(this.fileNameFor(AnnotationTypeWithoutAttributesForType.class));
		javaFiles.oneJavaFile(this.fileNameFor(AnnotationTypeWithValueAttributeForType.class));
	}

	@Test
	public void testTypes() {
		assertEquals(4, importer.types().stream().filter(t -> ! t.getIsStub()).count());
	}
	
	@Test
	public void testAnnotationInstancesForType() {
		Type type = importer.types().named(ClassWithAnnotationsForType.class.getName());
		assertEquals(3, type.getAnnotationInstances().size());
	}

	@Test
	public void testAnnotationInstance() {
		AnnotationType annotationType = (AnnotationType) importer.types().named(AnnotationTypeWithMultipleAttributesForType.class.getName());
		assertEquals(1, annotationType.getInstances().size());
		AnnotationInstance annotationInstance = annotationType.getInstances().stream().findAny().get();
		assertEquals(5, annotationInstance.getAttributes().size());
		annotationInstance.getAttributes().forEach(a -> assertNotNull(a.getAnnotationTypeAttribute()));
	}
	
	@Test
	public void testStringAnnotationAttribute() {
		AnnotationInstanceAttribute stringAnnotationAttribute = annotationInstanceAttribute(AnnotationTypeWithMultipleAttributesForType.class, "stringAnnotationAttribute", importer.types().named(ClassWithAnnotationsForType.class.getName()));
		assertEquals("string", stringAnnotationAttribute.getValue());
	}

	@Test
	public void testBooleanAnnotationAttribute() {
		AnnotationInstanceAttribute booleanAnnotationAttribute = annotationInstanceAttribute(AnnotationTypeWithMultipleAttributesForType.class, "booleanAnnotationAttribute", importer.types().named(ClassWithAnnotationsForType.class.getName()));
		assertEquals("true", booleanAnnotationAttribute.getValue());
	}

	@Test
	public void testClassAnnotationAttribute() {
		AnnotationInstanceAttribute classAnnotationAttribute = annotationInstanceAttribute(AnnotationTypeWithMultipleAttributesForType.class, "classAnnotationAttribute", importer.types().named(ClassWithAnnotationsForType.class.getName()));
		assertEquals("Object.class", classAnnotationAttribute.getValue());
	}

	@Test
	public void testClassCollectionAnnotationAttribute() {
		AnnotationInstanceAttribute classCollectionAnnotationAttribute = annotationInstanceAttribute(AnnotationTypeWithMultipleAttributesForType.class, "classCollectionAnnotationAttribute", importer.types().named(ClassWithAnnotationsForType.class.getName()));
		assertEquals("{Object.class, String.class}", classCollectionAnnotationAttribute.getValue());
	}

	private AnnotationInstanceAttribute annotationInstanceAttribute(Class<?> annotationClass, String attributeNamed, NamedEntity annotatedEntity) {
		AnnotationType annotationType = (AnnotationType) importer.types().named(annotationClass.getName());
		AnnotationInstance annotationInstance = annotationType.getInstances()
				.stream()
				.filter(inst -> inst.getAnnotatedEntity().equals(annotatedEntity))
				.findAny()
				.get();
		return annotationInstance.getAttributes()
				.stream()
				.filter(a -> a.getAnnotationTypeAttribute().getName().equals(attributeNamed))
				.findAny()
				.get();
	}

}
