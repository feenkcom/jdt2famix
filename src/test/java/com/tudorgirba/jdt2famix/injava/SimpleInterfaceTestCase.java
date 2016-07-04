package com.tudorgirba.jdt2famix.injava;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.SimpleInterface;
import org.moosetechnology.model.famix.*;
import org.moosetechnology.model.famix.Class;

public class SimpleInterfaceTestCase extends BasicSampleTestCase {

	@Override
	protected String sampleFileName() {
		return SimpleInterface.class.getSimpleName();
	}

	@Test
	public void test() {
		assertEquals(1, importer.getNamespaces().size());
		assertTrue(importer.getNamespaces().containsKey(SimpleInterface.class.getPackage().getName()));

		assertEquals(1, importer.getTypes().size());
		assertTrue(importer.getTypes().containsKey(SimpleInterface.class.getName()));
		
		Class type = (Class) importer.getTypes().get(SimpleInterface.class.getName());
		Namespace namespace = (Namespace) importer.getNamespaces().get(SimpleInterface.class.getPackage().getName());
		assertTrue(type.getIsInterface());
		assertEquals(namespace, type.getContainer());
	}
}
