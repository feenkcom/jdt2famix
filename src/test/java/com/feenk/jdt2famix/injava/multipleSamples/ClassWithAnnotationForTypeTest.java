package com.feenk.jdt2famix.injava.multipleSamples;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.ClassWithAnnotationsForType;
import org.moosetechnology.jdt2famix.samples.basic.AnnotationTypeWithTwoAttributesForType;

import com.feenk.jdt2famix.JavaFiles;
import com.feenk.jdt2famix.model.famix.Type;

public class ClassWithAnnotationForTypeTest extends
		MultipleSamplesTestCase {

	@Override
	protected void sampleClassesIn(JavaFiles javaFiles) {
		javaFiles.oneJavaFile(this.fileNameFor(ClassWithAnnotationsForType.class));
		javaFiles.oneJavaFile(this.fileNameFor(AnnotationTypeWithTwoAttributesForType.class));
	}

	@Test
	public void testTypes() {
		assertEquals(2, importer.types().stream().filter(t -> ! t.getIsStub()).count());
	}
	
	@Test
	public void testAnnotationInstance() {
		Type type = importer.types().named(ClassWithAnnotationsForType.class.getName());
		assertEquals(1, type.getAnnotationInstances().size());
	}
}
