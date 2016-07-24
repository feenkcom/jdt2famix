package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.Famix;
import com.feenk.jdt2famix.model.famix.Type;
import com.feenk.jdt2famix.samples.basic.EmptyClass;
import com.feenk.jdt2famix.samples.basic.SimpleParameterizableClass;
import com.feenk.jdt2famix.samples.basic.VariousAnnonymousClasses;

public class VariousAnnonymousClassesTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return VariousAnnonymousClasses.class;
	}

	@Test
	public void testMethodWithEmptyClass() {
		assertEquals(1, methodNamed("methodWithEmptyClass").getTypes().size());
		Type annonymous = methodNamed("methodWithEmptyClass").getTypes().stream().findAny().get();
		assertEquals("$1", annonymous.getName());
		assertEquals(EmptyClass.class.getSimpleName(), Famix.superclassOf(annonymous).getName());
	}
	
	@Test
	public void testMethodWithParameterizedType() {
		assertEquals(1, methodNamed("methodWithParameterizedType").getTypes().size());
		Type annonymous = methodNamed("methodWithParameterizedType").getTypes().stream().findAny().get();
		assertEquals("$1", annonymous.getName());
		assertEquals(SimpleParameterizableClass.class.getSimpleName(), Famix.superclassOf(annonymous).getName());
	}
	
}
