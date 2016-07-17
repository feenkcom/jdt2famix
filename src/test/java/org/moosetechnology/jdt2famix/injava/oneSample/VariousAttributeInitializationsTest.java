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
		assertEquals(9, type.getAttributes().size());
	}

	@Test
	public void testAccesses() {
		assertEquals(11, methodNamed(InJavaImporter.INITIALIZER_NAME).getAccesses().size());
	}
	
	@Test
	public void testAccessToConstant() {
		assertEquals(3, attributeNamed("CONSTANT").getIncomingAccesses().size());
		assertTrue(attributeNamed("CONSTANT").getIncomingAccesses().stream().anyMatch(a -> !(a.getIsWrite())));
		assertTrue(attributeNamed("CONSTANT").getIncomingAccesses().stream().anyMatch(a -> a.getIsWrite()));
	}

	@Test
	public void testAccessToEnumValue() {
		Enum sampleEnum = (Enum) importer.types().stream().filter(t -> t instanceof Enum).findAny().get();
		EnumValue enumValue = sampleEnum.getValues().stream().filter(v -> v.getName().equals("ONE")).findAny().get();
		assertEquals(1, enumValue.getIncomingAccesses().size());
	}
}
