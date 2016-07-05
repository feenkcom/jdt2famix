package org.moosetechnology.jdt2famix.injava;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.ClassImplementingObserver;
import org.moosetechnology.jdt2famix.samples.basic.SimpleInterface;
import org.moosetechnology.model.famix.*;
import org.moosetechnology.model.famix.Class;

public class SimpleInterfaceTestCase extends BasicSampleTestCase {

	@Override
	protected java.lang.Class<?> sampleClass() {
		return SimpleInterface.class;
	}

	@Test
	public void test() {
		assertEquals(1, importer.getNamespaces().size());
		assertTrue(importer.getNamespaces().containsKey(SimpleInterface.class.getPackage().getName()));

		assertEquals(1, importer.getTypes().size());
		assertTrue(importer.getTypes().containsKey(SimpleInterface.class.getName()));
		
		Class clazz = (Class) type;
		Namespace namespace = (Namespace) importer.getNamespaces().get(SimpleInterface.class.getPackage().getName());
		assertFalse(namespace.getIsStub());
		assertTrue(clazz.getIsInterface());
		assertEquals(namespace, clazz.getContainer());
		assertFalse(clazz.getIsStub());
	}
}
