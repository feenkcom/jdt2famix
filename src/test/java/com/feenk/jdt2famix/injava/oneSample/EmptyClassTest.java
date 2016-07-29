package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.model.famix.FileAnchor;
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
		assertTrue(type.getSourceAnchor() instanceof FileAnchor);
		assertEquals(3, ((FileAnchor) type.getSourceAnchor()).getStartLine());
		assertEquals(4, ((FileAnchor) type.getSourceAnchor()).getEndLine());
		assertFalse(((FileAnchor) type.getSourceAnchor()).getFileName().isEmpty());
	}
	
	@Test
	public void testNamespaceNesting() {
		assertNotNull(type.getContainer());
		assertEquals(Namespace.class, type.getContainer().getClass());
		assertEquals(Namespace.class, ((Namespace) type.getContainer()).getParentScope().getClass());
	}
	
}
