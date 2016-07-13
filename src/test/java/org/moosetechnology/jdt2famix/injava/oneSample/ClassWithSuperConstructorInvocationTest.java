package org.moosetechnology.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.ClassWithSuperConstructorInvocation;

public class ClassWithSuperConstructorInvocationTest extends
		OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithSuperConstructorInvocation.class;
	}

	@Test
	public void testConstructor() {
		assertEquals(1, type.getMethods().size());
		assertFalse(type.getIsStub());
		assertEquals("constructor", type.getMethods().stream().findAny().get().getKind());
	}
	
	@Test
	public void testSuperConstructorInvocation() {
		assertEquals(1, type.getMethods().stream().findAny().get().getOutgoingInvocations().size());
	}
}
