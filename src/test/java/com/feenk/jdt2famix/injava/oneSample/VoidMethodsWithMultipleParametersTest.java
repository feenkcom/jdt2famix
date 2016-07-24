package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.samples.basic.VoidMethodsWithMultipleParameters;

public class VoidMethodsWithMultipleParametersTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return VoidMethodsWithMultipleParameters.class;
	}

	@Test
	public void testMethodWithTwoParameters() {
		assertEquals(2, methodNamed("methodWithTwoParameters").getParameters().size());
	}
	
	@Test
	public void testMethodWithTwoParametersSignature() {
		assertEquals("methodWithTwoParameters(int, boolean)", methodNamed("methodWithTwoParameters").getSignature());
	}
}
