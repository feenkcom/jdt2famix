package com.feenk.jdt2famix.injava.multipleSamples;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.ClassWithAnnotationInstanceWithoutAttributes;
import org.moosetechnology.jdt2famix.samples.basic.SimpleAnnotationTypeForType;

import com.feenk.jdt2famix.JavaFiles;
import com.feenk.jdt2famix.model.famix.Type;

public class ClassWithAnnotationInstanceWithoutAttributesTest extends
		MultipleSamplesTestCase {

	@Override
	protected void sampleClassesIn(JavaFiles javaFiles) {
		javaFiles.oneJavaFile(this.fileNameFor(ClassWithAnnotationInstanceWithoutAttributes.class));
		javaFiles.oneJavaFile(this.fileNameFor(SimpleAnnotationTypeForType.class));
	}

	@Test
	public void testTypes() {
		assertEquals(2, importer.types().stream().filter(t -> ! t.getIsStub()).count());
	}
	
	@Test
	public void testAnnotationInstance() {
		Type type = importer.types().named(ClassWithAnnotationInstanceWithoutAttributes.class.getName());
		assertEquals(1, type.getAnnotationInstances().size());
	}
}
