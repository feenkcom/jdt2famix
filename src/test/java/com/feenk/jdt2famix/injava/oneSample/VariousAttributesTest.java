package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.model.famix.AbstractFileAnchor;
import com.feenk.jdt2famix.model.famix.Attribute;
import com.feenk.jdt2famix.model.famix.FileAnchor;
import com.feenk.jdt2famix.model.famix.PrimitiveType;
import com.feenk.jdt2famix.samples.basic.VariousAttributes;

public class VariousAttributesTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return VariousAttributes.class;
	}

	@Test
	public void testAttributes() {
		assertEquals(8, type.getAttributes().size());
	}
	
	@Test
	public void testPublicStaticIntAttribute() {
		Attribute publicStaticIntAttribute = attributeNamed("publicStaticIntAttribute");
		assertEquals(1, publicStaticIntAttribute.getModifiers().size());
		assertTrue(publicStaticIntAttribute.getModifiers().stream().anyMatch(m -> m.equals("public")));
		assertTrue(publicStaticIntAttribute.getHasClassScope());
		assertNotNull(publicStaticIntAttribute.getDeclaredType());
		assertEquals("int", publicStaticIntAttribute.getDeclaredType().getName());
		assertEquals(PrimitiveType.class, publicStaticIntAttribute.getDeclaredType().getClass());
	}
	
	@Test
	public void testProtectedStaticFloatAttribute() {
		Attribute protectedStaticFloatAttribute = attributeNamed("protectedStaticFloatAttribute");
		assertEquals(1, protectedStaticFloatAttribute.getModifiers().size());
		assertTrue(protectedStaticFloatAttribute.getModifiers().stream().anyMatch(m -> m.equals("protected")));
		assertTrue(protectedStaticFloatAttribute.getHasClassScope());
		assertNotNull(protectedStaticFloatAttribute.getDeclaredType());
		assertEquals("float", protectedStaticFloatAttribute.getDeclaredType().getName());
		assertEquals(PrimitiveType.class, protectedStaticFloatAttribute.getDeclaredType().getClass());
	}
	
	@Test
	public void testPublicStringAttribute() {
		Attribute publicStringAttribute = attributeNamed("publicStringAttribute");
		assertEquals(1, publicStringAttribute.getModifiers().size());
		assertTrue(publicStringAttribute.getModifiers().stream().anyMatch(m -> m.equals("public")));
		assertNotNull(publicStringAttribute.getDeclaredType());
		assertEquals("String", publicStringAttribute.getDeclaredType().getName());
		assertEquals(com.feenk.jdt2famix.model.famix.Class.class, publicStringAttribute.getDeclaredType().getClass());
	}
	
	@Test
	public void testProtectedVariousMethodsAttribute() {
		Attribute protectedVariousMethodsAttribute = attributeNamed("protectedVariousMethodsAttribute");
		assertNotEquals(importer.unknownType(), protectedVariousMethodsAttribute.getDeclaredType());
		assertEquals(importer.unknownNamespace(), protectedVariousMethodsAttribute.getDeclaredType().getContainer());
	}
	
	@Test
	public void testAttributeSourceAnchorFileNameIsLikeForItsClass() {
		AbstractFileAnchor fileAnchor = (AbstractFileAnchor) type.getSourceAnchor();
		type.getAttributes().stream()
			.forEach(a -> assertEquals(fileAnchor.getFileName(), ((AbstractFileAnchor) a.getSourceAnchor()).getFileName())); 
	}

}
