package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.SimpleClassWithAnnonymousClass;

import com.feenk.jdt2famix.Famix;
import com.feenk.jdt2famix.model.famix.Type;

public class SimpleClassWithAnnonymousClassTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return SimpleClassWithAnnonymousClass.class;
	}

	@Test
	public void testAnonymousClass() {
		assertEquals(1, methodNamed("method").getTypes().size());
	}
	
	@Test
	public void testSuperclassOfAnonymousClass() {
		Type superclass = Famix.superclassOf(methodNamed("method").getTypes().stream().findAny().get());
		assertEquals("java.lang.Object", Famix.qualifiedNameOf(superclass));
	}
}
