package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.samples.basic.ClassWithJavadoc;

public class ClassWithJavadocTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithJavadoc.class;
	}

	@Test
	public void testClassJavadoc() {
		assertEquals(1, type.getComments().size());
	}
}
