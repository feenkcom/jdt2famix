package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.ClassWithExceptions;

public class ClassWithExceptionsTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithExceptions.class;
	}

	@Ignore
	@Test
	public void testThrownExceptions() {
		assertEquals(1, methodNamed("method").getThrownExceptions().size());
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
