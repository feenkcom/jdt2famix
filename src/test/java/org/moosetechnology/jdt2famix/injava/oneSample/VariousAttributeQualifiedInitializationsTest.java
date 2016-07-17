package org.moosetechnology.jdt2famix.injava.oneSample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.moosetechnology.jdt2famix.injava.InJavaImporter;
import org.moosetechnology.jdt2famix.samples.basic.VariousAttributeQualifiedInitializations;
import org.moosetechnology.model.famix.Enum;
import org.moosetechnology.model.famix.EnumValue;

public class VariousAttributeQualifiedInitializationsTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return VariousAttributeQualifiedInitializations.class;
	}

	@Test
	public void testOnlyInitializer() {
		assertEquals(1, type.getMethods().size());
		assertEquals(1, importer.methods().size());
	}
	
	@Test
	public void testAttributes() {
		assertEquals(2, type.getAttributes().size());
	}

	@Test
	public void testAccesses() {
		assertEquals(4, methodNamed(InJavaImporter.INITIALIZER_NAME).getAccesses().size());
	}
	
	@Test
	public void testAccessToConstant() {
		assertEquals(2, attributeNamed("attributeInitializedWithEnumValue").getIncomingAccesses().size());
		assertTrue(attributeNamed("attributeInitializedWithEnumValue").getIncomingAccesses().stream().anyMatch(a -> !(a.getIsWrite())));
		assertTrue(attributeNamed("attributeInitializedWithEnumValue").getIncomingAccesses().stream().anyMatch(a -> a.getIsWrite()));
	}

	@Test
	public void testAccessToEnumValue() {
		Enum sampleEnum = (Enum) importer.types().stream().filter(t -> t instanceof Enum).findAny().get();
		EnumValue enumValue = sampleEnum.getValues().stream().filter(v -> v.getName().equals("ONE")).findAny().get();
		assertEquals(1, enumValue.getIncomingAccesses().size());
	}
}
