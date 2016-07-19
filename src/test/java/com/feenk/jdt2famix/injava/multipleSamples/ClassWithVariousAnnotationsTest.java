package com.feenk.jdt2famix.injava.multipleSamples;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.AnnotationTypeWithValueAttributeForType;
import org.moosetechnology.jdt2famix.samples.basic.AnnotationTypeWithoutAttributesForMethodAttribute;
import org.moosetechnology.jdt2famix.samples.basic.AnnotationTypeWithoutAttributesForType;
import org.moosetechnology.jdt2famix.samples.basic.ClassWithAnnotationsForType;
import org.moosetechnology.jdt2famix.samples.basic.AnnotationTypeWithTwoAttributesForType;
import org.moosetechnology.jdt2famix.samples.basic.ClassWithVariousAnnotations;

import com.feenk.jdt2famix.JavaFiles;
import com.feenk.jdt2famix.model.famix.Type;

public class ClassWithVariousAnnotationsTest extends
		MultipleSamplesTestCase {

	@Override
	protected void sampleClassesIn(JavaFiles javaFiles) {
		javaFiles.oneJavaFile(this.fileNameFor(ClassWithVariousAnnotations.class));
		javaFiles.oneJavaFile(this.fileNameFor(AnnotationTypeWithoutAttributesForType.class));
		javaFiles.oneJavaFile(this.fileNameFor(AnnotationTypeWithoutAttributesForMethodAttribute.class));
	}

	@Test
	public void testTypes() {
		assertEquals(3, importer.types().stream().filter(t -> ! t.getIsStub()).count());
	}
	
	@Test
	public void testAnnotationInstanceOnType() {
		Type type = importer.types().named(ClassWithVariousAnnotations.class.getName());
		assertEquals(1, type.getAnnotationInstances().size());
	}

	@Test
	public void testAnnotationInstanceOnAttributes() {
		importer.attributes().stream().forEach(a -> assertEquals(1, a.getAnnotationInstances().size()));
	}

	@Test
	public void testAnnotationInstanceOnMethods() {
		importer.methods().stream().forEach(m -> assertEquals(1, m.getAnnotationInstances().size()));
	}
}
