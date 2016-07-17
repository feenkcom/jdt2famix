package org.moosetechnology.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.injava.InJavaImporter;
import org.moosetechnology.jdt2famix.samples.basic.VariousAttributeInitializations;
import org.moosetechnology.model.famix.Enum;
import org.moosetechnology.model.famix.EnumValue;

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
	public void testInvocationsToStringConstructor() {
		assertEquals(2, methodNamed("String").getIncomingInvocations().size());
	}
	
	@Test
	public void testAttributes() {
		assertEquals(7, type.getAttributes().size());
	}

	@Test
	public void testAccesses() {
		assertEquals(8, methodNamed(InJavaImporter.INITIALIZER_NAME).getAccesses().size());
	}
	
	@Test
	public void testAccessToConstant() {
		assertEquals(2, attributeNamed("CONSTANT").getIncomingAccesses().size());
		assertTrue(attributeNamed("CONSTANT").getIncomingAccesses().stream().anyMatch(a -> !(a.getIsWrite())));
		assertTrue(attributeNamed("CONSTANT").getIncomingAccesses().stream().anyMatch(a -> a.getIsWrite()));
	}

}
