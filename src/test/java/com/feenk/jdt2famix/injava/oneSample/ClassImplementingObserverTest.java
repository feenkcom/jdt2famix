package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Observer;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.ClassImplementingObserver;

import com.feenk.jdt2famix.model.famix.Class;
import com.feenk.jdt2famix.model.famix.Method;

public class ClassImplementingObserverTest extends OneSampleTestCase {

	@Override
	protected java.lang.Class<?> sampleClass() {
		return ClassImplementingObserver.class;
	}

	@Test
	public void testModelSize() {
		assertEquals(3, importer.namespaces().size());
		assertEquals(4, importer.types().size());
	}
	
	@Test
	public void testInterface() {	
		Class observerClass = (Class) importer.types().named(Observer.class.getName());
		assertTrue(observerClass.getIsInterface());
		assertTrue(observerClass.getIsStub());
		assertEquals(observerClass.getContainer(), 
					 importer.namespaces().named(Observer.class.getPackage().getName()));
	}
	
	@Test
	public void testClass() {	
		Class observerClass = (Class) type;
		assertFalse(observerClass.getIsInterface());
		assertFalse(observerClass.getIsStub());
		assertEquals(observerClass.getContainer(), 
				     importer.namespaces().named(ClassImplementingObserver.class.getPackage().getName()));
	}
	
	@Test
	public void testMethod() {
		assertEquals(1, type.getMethods().size());
		Method method = type.getMethods().stream().findAny().get();
		assertEquals(2, method.getParameters().size());
	}
	
}
