package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.feenk.jdt2famix.samples.basic.ClassWithExceptions;

public class ClassWithExceptionsTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithExceptions.class;
	}

	@Test
	public void testThrownExceptionsFromMethod() {
		assertEquals(1, methodNamed("method").getThrownExceptions().size());
		assertEquals(RuntimeException.class.getSimpleName(), methodNamed("method").getThrownExceptions().stream().findAny().get().getExceptionClass().getName());
	}

	@Test
	public void testMethodThrowingInstantiatedException() {
		assertEquals(1, methodNamed("methodThrowingInstantiatedException").getThrownExceptions().size());
		assertEquals(RuntimeException.class.getSimpleName(), methodNamed("methodThrowingInstantiatedException").getThrownExceptions().stream().findAny().get().getExceptionClass().getName());
	}

	@Test
	public void testMethodThrowingExceptionReturnedFromAnotherMethod() {
		assertEquals(1, methodNamed("methodThrowingExceptionReturnedFromAnotherMethod").getThrownExceptions().size());
		assertEquals(RuntimeException.class.getSimpleName(), methodNamed("methodThrowingExceptionReturnedFromAnotherMethod").getThrownExceptions().stream().findAny().get().getExceptionClass().getName());
	}

	@Test
	public void testCaughtExceptions() {
		assertEquals(1, methodNamed("method").getCaughtExceptions().size());
	}

	@Test
	public void testDeclaredExceptions() {
		assertEquals(1, methodNamed("method").getDeclaredExceptions().size());
	}

}
