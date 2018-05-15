package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.assertEquals;
import com.feenk.jdt2famix.samples.basic.ClassWithOneMethodAndOneCall;

import org.junit.Test;

public class ClassWithOneMethodAndOneCallTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithOneMethodAndOneCall.class;
	}
	
	@Test
	public void testInvocation() {
		assertEquals(1, methodNamed("m").getOutgoingInvocations().size());
	}

}
