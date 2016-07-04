package com.tudorgirba.jdt2famix.injava;

import static org.junit.Assert.*;

import java.util.Observer;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.ClassImplementingObserver;
import org.moosetechnology.model.famix.Class;

public class ClassImplementingObserverTest extends BasicSampleTestCase {

	@Override
	protected String sampleFileName() {
		return ClassImplementingObserver.class.getSimpleName();
	}

	@Test
	public void test() {
		assertEquals(2, importer.getNamespaces().size());
		assertEquals(2, importer.getTypes().size());
		Class observable = (Class) importer.getTypes().get(Observer.class.getName());
		assertTrue(observable.getIsInterface());
		assertTrue(observable.getIsStub());
	}
}
