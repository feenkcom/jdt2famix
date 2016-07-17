package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.ClassWithOneNestedMethodInvocation;

import com.feenk.jdt2famix.model.famix.Invocation;

public class ClassWithOneNestedMethodInvocationTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithOneNestedMethodInvocation.class;
	}

	@Test
	public void testMethods() {
		assertEquals(2, type.getMethods().size());
	}
	
	@Test
	public void testInvocation() {
		assertEquals(1, methodNamed("invokingMethod").getOutgoingInvocations().size());
		Invocation invocation = methodNamed("invokingMethod").getOutgoingInvocations().stream().findAny().get();
		assertEquals(methodNamed("invokedMethod"), invocation.getCandidates().stream().findAny().get());
	}
	
}
