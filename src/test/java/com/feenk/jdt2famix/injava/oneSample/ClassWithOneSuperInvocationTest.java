package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.ClassWithOneSuperInvocation;

import com.feenk.jdt2famix.model.famix.Method;

public class ClassWithOneSuperInvocationTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithOneSuperInvocation.class;
	}
	
	@Test
	public void testInvocation() {
		Method toStringMethod = type.getMethods().stream().findAny().get();
		assertEquals(1, toStringMethod.getOutgoingInvocations().size());
	}

}
