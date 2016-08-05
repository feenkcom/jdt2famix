package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.samples.basic.ClassWithInnerClassDefinedInLambda;

public class ClassWithInnerClassDefinedInLambdaTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithInnerClassDefinedInLambda.class;
	}
	
	@Test
	public void testMethodWithInnerClassInSimpleLambda() {
		assertEquals(1, methodNamed("methodWithInnerEmptyClassInSimpleLambda").getTypes().size());
	}

	@Test
	public void testMethodWithInnerClassInLambdaBlock() {
		assertEquals(1, methodNamed("methodWithInnerEmptyClassInLambdaBlock").getTypes().size());
	}

	@Test
	public void testMethodWithInnerSameClassInLambdaBlock() {
		assertEquals(1, methodNamed("methodWithInnerSameClassInLambdaBlock").getTypes().size());
	}
	
}
