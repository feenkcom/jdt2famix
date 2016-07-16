package org.moosetechnology.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.injava.InJavaImporter;
import org.moosetechnology.jdt2famix.samples.basic.VariousAttributeInitializations;

public class VariousAttributeInitializationsTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return VariousAttributeInitializations.class;
	}

	@Test
	public void testOnlyInitializer() {
		assertEquals(1, type.getMethods().size());
		assertEquals(2, importer.methods().size());
	}

	@Test
	public void test() {
		assertEquals(5, methodNamed(InJavaImporter.INITIALIZER_NAME).getAccesses().size());
	}
}
