package com.feenk.jdt2famix.injava.multipleSamples;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.feenk.jdt2famix.JavaFiles;
import com.feenk.jdt2famix.model.famix.AnnotationInstance;
import com.feenk.jdt2famix.model.famix.AnnotationInstanceAttribute;
import com.feenk.jdt2famix.model.famix.AnnotationType;
import com.feenk.jdt2famix.model.famix.Type;
import com.feenk.jdt2famix.samples.basic.AnnotationTypeWithTwoAttributesForType;
import com.feenk.jdt2famix.samples.basic.AnnotationTypeWithValueAttributeForType;
import com.feenk.jdt2famix.samples.basic.AnnotationTypeWithoutAttributesForType;
import com.feenk.jdt2famix.samples.basic.ClassWithAnnotationsForType;

public class ClassWithAnnotationForTypeTest extends
		MultipleSamplesTestCase {

	@Override
	protected void sampleClassesIn(JavaFiles javaFiles) {
		javaFiles.oneJavaFile(this.fileNameFor(ClassWithAnnotationsForType.class));
		javaFiles.oneJavaFile(this.fileNameFor(AnnotationTypeWithTwoAttributesForType.class));
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
		AnnotationType annotationType = (AnnotationType) importer.types().named(AnnotationTypeWithTwoAttributesForType.class.getName());
		assertEquals(1, annotationType.getInstances().size());
		AnnotationInstance annotationInstance = annotationType.getInstances().stream().findAny().get();
		assertEquals(2, annotationInstance.getAttributes().size());
		annotationInstance.getAttributes().forEach(a -> assertNotNull(a.getAnnotationTypeAttribute()));
		AnnotationInstanceAttribute annotationInstanceAttribute = annotationInstance.getAttributes()
					.stream()
					.filter(a -> a.getAnnotationTypeAttribute().getName().equals("stringAnnotationAttribute"))
					.findAny()
					.get();
		assertNotNull(annotationInstanceAttribute.getValue());
	}
}
