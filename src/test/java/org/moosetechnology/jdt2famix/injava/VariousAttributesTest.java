package org.moosetechnology.jdt2famix.injava;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.VariousAttributes;
import org.moosetechnology.model.famix.Attribute;
import org.moosetechnology.model.famix.PrimitiveType;

public class VariousAttributesTest extends BasicSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return VariousAttributes.class;
	}

	@Test
	public void testAttributes() {
		assertEquals(8, type.getAttributes().size());
		
		Attribute publicStaticIntAttribute = attributeNamed("publicStaticIntAttribute");
		assertEquals(2, publicStaticIntAttribute.getModifiers().size());
		assertTrue(publicStaticIntAttribute.getModifiers().stream().anyMatch(m -> m.equals("public")));
		assertTrue(publicStaticIntAttribute.getModifiers().stream().anyMatch(m -> m.equals("static")));
		assertNotNull(publicStaticIntAttribute.getDeclaredType());
		assertEquals("int", publicStaticIntAttribute.getDeclaredType().getName());
		assertEquals(PrimitiveType.class, publicStaticIntAttribute.getDeclaredType().getClass());

		Attribute protectedStaticFloatAttribute = attributeNamed("protectedStaticFloatAttribute");
		assertEquals(2, protectedStaticFloatAttribute.getModifiers().size());
		assertTrue(protectedStaticFloatAttribute.getModifiers().stream().anyMatch(m -> m.equals("protected")));
		assertTrue(protectedStaticFloatAttribute.getModifiers().stream().anyMatch(m -> m.equals("static")));
		assertNotNull(protectedStaticFloatAttribute.getDeclaredType());
		assertEquals("float", protectedStaticFloatAttribute.getDeclaredType().getName());
		assertEquals(PrimitiveType.class, protectedStaticFloatAttribute.getDeclaredType().getClass());

		Attribute publicStringAttribute = attributeNamed("publicStringAttribute");
		assertEquals(1, publicStringAttribute.getModifiers().size());
		assertTrue(publicStringAttribute.getModifiers().stream().anyMatch(m -> m.equals("public")));
		assertNotNull(publicStringAttribute.getDeclaredType());
		assertEquals("String", publicStringAttribute.getDeclaredType().getName());
		assertEquals(org.moosetechnology.model.famix.Class.class, publicStringAttribute.getDeclaredType().getClass());

		Attribute protectedVariousMethodsAttribute = attributeNamed("protectedVariousMethodsAttribute");
		assertNotEquals(importer.unknownType(), protectedVariousMethodsAttribute.getDeclaredType());
		assertEquals(importer.unknownNamespace(), protectedVariousMethodsAttribute.getDeclaredType().getContainer());

	}
}
