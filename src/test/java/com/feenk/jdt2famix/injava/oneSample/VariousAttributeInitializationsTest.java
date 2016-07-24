package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.injava.InJavaImporter;
import com.feenk.jdt2famix.model.famix.Enum;
import com.feenk.jdt2famix.model.famix.EnumValue;
import com.feenk.jdt2famix.samples.basic.VariousAttributeInitializations;

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
