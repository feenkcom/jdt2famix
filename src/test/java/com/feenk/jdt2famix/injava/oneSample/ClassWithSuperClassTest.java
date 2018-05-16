package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.feenk.jdt2famix.samples.basic.ClassWithSuperClass;

public class ClassWithSuperClassTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithSuperClass.class;
	}

	@Test
	public void testSuperConstructorInvocation() {
		assertEquals(1, type.getSuperInheritances().size());
		org.junit.Assert.assertNotNull(type.getSuperInheritances().stream().findFirst().get().getSourceAnchor());
	}
}
