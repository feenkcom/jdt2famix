package org.moosetechnology.jdt2famix.injava;

import static org.junit.Assert.*;

import java.util.Observer;
import java.util.Optional;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.ClassImplementingObserver;
import org.moosetechnology.model.famix.Class;
import org.moosetechnology.model.famix.Method;

public class ClassImplementingObserverTest extends BasicSampleTestCase {

	@Override
	protected java.lang.Class<?> sampleClass() {
		return ClassImplementingObserver.class;
	}

	@Test
	public void testModelSize() {
		assertEquals(3, importer.getNamespaces().size());
		assertEquals(4, importer.getTypes().size());
	}
	
	@Test
	public void testInterface() {	
		Class observerClass = (Class) type;
		assertTrue(observerClass.getIsInterface());
		assertTrue(observerClass.getIsStub());
		assertEquals(observerClass.getContainer(), 
					 importer.getNamespaces().get(Observer.class.getPackage().getName()));
	}
	
	@Test
	public void testClass() {	
		Class observerClass = (Class) type;
		assertFalse(observerClass.getIsInterface());
		assertFalse(observerClass.getIsStub());
		assertEquals(observerClass.getContainer(), 
				     importer.getNamespaces().get(ClassImplementingObserver.class.getPackage().getName()));
	}
	
	@Test
	public void testMethod() {
		assertEquals(1, type.getMethods().size());
		Method method = type.getMethods().stream().findAny().get();
		assertEquals(2, method.getParameters().size());
	}
	
}
