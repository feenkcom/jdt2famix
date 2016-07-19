package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.AnnotationTypeWithTwoAttributesForType;

public class SimpleAnnotationTypeForTypeTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return AnnotationTypeWithTwoAttributesForType.class;
	}

	@Test
	public void testTypes() {
		assertEquals(1, importer.types().stream().filter(t -> !t.getIsStub()).count());
	}

	@Test
	public void testAttributes() {
		assertEquals(2, importer.attributes().size());
	}
	
}
