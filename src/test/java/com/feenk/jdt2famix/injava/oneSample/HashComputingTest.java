package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.feenk.jdt2famix.samples.basic.ClassWithInnerClassDefinedInLambda;

public class HashComputingTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithInnerClassDefinedInLambda.class;
	}

	@Test
	public void testComputeHashOfMethod() {
		assertEquals("3bf3690ec9050c5068f3f3742ac9b515", methodNamed("methodWithInnerEmptyClassInSimpleLambda").getBodyHash());

	}
}
