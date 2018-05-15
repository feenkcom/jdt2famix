package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.feenk.jdt2famix.model.famix.IndexedFileAnchor;
import com.feenk.jdt2famix.model.famix.Namespace;
import com.feenk.jdt2famix.samples.basic.EmptyClass;

public class EmptyClassTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return EmptyClass.class;
	}

	@Test
	public void testSourceAnchor() {
		assertNotNull(type.getSourceAnchor());
		assertTrue(type.getSourceAnchor() instanceof IndexedFileAnchor);
		assertEquals(45, ((IndexedFileAnchor) type.getSourceAnchor()).getStartPos());
		assertEquals(71, ((IndexedFileAnchor) type.getSourceAnchor()).getEndPos());
		assertFalse(((IndexedFileAnchor) type.getSourceAnchor()).getFileName().isEmpty());
	}
	
	@Test
	public void testNamespaceNesting() {
		assertNotNull(type.getContainer());
		assertEquals(Namespace.class, type.getContainer().getClass());
		assertEquals(Namespace.class, ((Namespace) type.getContainer()).getParentScope().getClass());
	}
	
}
