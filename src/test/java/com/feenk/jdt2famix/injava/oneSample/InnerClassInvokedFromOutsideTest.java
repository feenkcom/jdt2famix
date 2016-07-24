package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.InnerClassInvokedFromOutside;

import com.feenk.jdt2famix.model.famix.Method;

public class InnerClassInvokedFromOutsideTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return InnerClassInvokedFromOutside.class;
	}

	@Test
	public void testInnerConstructor() {
		Method innerConstructor = methodNamed("InnerClass");
		assertNotNull(innerConstructor);
		assertEquals(1, innerConstructor.getIncomingInvocations().size());
		assertTrue(innerConstructor.getIsStub());
	}

	@Test
	public void testOuterConstructor() {
		Method innerConstructor = methodNamed("OuterClass");
		assertNotNull(innerConstructor);
		assertEquals(1, innerConstructor.getIncomingInvocations().size());
		assertTrue(innerConstructor.getIsStub());
	}

	@Test
	public void testInnerMethod() {
		Method innerConstructor = methodNamed("innerMethod");
		assertNotNull(innerConstructor);
		assertEquals(1, innerConstructor.getIncomingInvocations().size());
		assertFalse(innerConstructor.getIsStub());
	}
	
	@Test
	public void testMethod() {
		Method method = methodNamed("method");
		assertEquals(3, method.getOutgoingInvocations().size());
	}
	
}
