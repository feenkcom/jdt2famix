package org.moosetechnology.jdt2famix.injava;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.VariousAttributes;

public class VariousAttributesTest extends BasicSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return VariousAttributes.class;
	}

	@Test
	public void testAttributes() {
		assertEquals(8, type.getAttributes().size()); 
	}
}
