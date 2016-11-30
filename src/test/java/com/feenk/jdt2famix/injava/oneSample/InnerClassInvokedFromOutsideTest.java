package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.model.famix.Method;
import com.feenk.jdt2famix.samples.basic.InnerClassInvokedFromOutside;

public class InnerClassInvokedFromOutsideTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return InnerClassInvokedFromOutside.class;
	}

	@Test
	public void testInnerConstructor() {
		Method innerConstructor = methodNamed("InnerClass");
		assertNotNull(innerConstructor);
		assertEquals(2, innerConstructor.getIncomingInvocations().size());
		assertTrue(innerConstructor.getIsStub());
	}

	@Test
	public void testOuterConstructor() {
		Method innerConstructor = methodNamed("OuterClass");
		assertNotNull(innerConstructor);
		assertEquals(2, innerConstructor.getIncomingInvocations().size());
		assertTrue(innerConstructor.getIsStub());
	}

	@Test
	public void testInnerMethod() {
		Method innerConstructor = methodNamed("innerMethod");
		assertNotNull(innerConstructor);
		assertEquals(2, innerConstructor.getIncomingInvocations().size());
		assertFalse(innerConstructor.getIsStub());
	}
	
	@Test
	public void testMethod() {
		Method method = methodNamed("method");
		assertEquals(3, method.getOutgoingInvocations().size());
	}

	@Test
	public void testMethodCallingThroughLocalVariable() {
		Method method = methodNamed("methodCallingThroughLocalVariable");
		assertEquals(3, method.getOutgoingInvocations().size());
	}
	
}
