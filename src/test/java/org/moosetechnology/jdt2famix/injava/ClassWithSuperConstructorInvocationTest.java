package org.moosetechnology.jdt2famix.injava;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.ClassWithSuperConstructorInvocation;

public class ClassWithSuperConstructorInvocationTest extends
		BasicSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithSuperConstructorInvocation.class;
	}

	@Test
	public void test() {
		assertEquals(1, type.getMethods().size());
		assertEquals("constructor", type.getMethods().stream().findAny().get().getKind());
	}
}
