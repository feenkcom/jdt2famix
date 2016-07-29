package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.model.famix.Namespace;
import com.feenk.jdt2famix.model.famix.Class;
import com.feenk.jdt2famix.samples.basic.SimpleInterface;

public class SimpleInterfaceTestCase extends OneSampleTestCase {

	@Override
	protected java.lang.Class<?> sampleClass() {
		return SimpleInterface.class;
	}

	@Test
	public void testNamespaces() {
		assertTrue(importer.namespaces().has(SimpleInterface.class.getPackage().getName()));
		assertNotNull(importer.namespaces().named(SimpleInterface.class.getPackage().getName()).getParentScope());
	}
	@Test
	public void testTypes() {
		assertEquals(1, importer.types().size());
		assertTrue(importer.types().has(SimpleInterface.class.getName()));
	}
	
	@Test
	public void testInterface() {
		Class clazz = (Class) type;
		Namespace namespace = (Namespace) importer.namespaces().named(SimpleInterface.class.getPackage().getName());
		assertFalse(namespace.getIsStub());
		assertTrue(clazz.getIsInterface());
		assertEquals(namespace, clazz.getContainer());
		assertFalse(clazz.getIsStub());
	}
}
