package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.samples.basic.AnnotationTypeWithMultipleAttributesForType;

public class SimpleAnnotationTypeForTypeTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return AnnotationTypeWithMultipleAttributesForType.class;
	}

	@Test
	public void testTypes() {
		assertEquals(1, importer.types().stream().filter(t -> !t.getIsStub()).count());
	}

	@Test
	public void testAttributes() {
		assertEquals(4, type.getAttributes().size());
	}
	
	@Test
	public void testBooleanAnnotationAttribute() {
		assertEquals("boolean", attributeNamed("booleanAnnotationAttribute").getDeclaredType().getName());
	}

}
