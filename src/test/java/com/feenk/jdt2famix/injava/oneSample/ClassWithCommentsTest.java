package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.injava.InJavaImporter;
import com.feenk.jdt2famix.samples.basic.ClassWithComments;

public class ClassWithCommentsTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithComments.class;
	}

	@Test
	public void testClassJavadoc() {
		assertEquals(1, type.getComments().size());
	}

	@Test
	public void testMethodWithJavadoc() {
		assertEquals(1, methodNamed("methodWithJavadoc").getComments().size());
	}

	@Test
	public void testAttributeWithJavadoc() {
		assertEquals(1, attributeNamed("attributeWithJavadoc").getComments().size());
	}

	@Test
	public void testInitializerWithJavadoc() {
		assertEquals(1, methodNamed(InJavaImporter.INITIALIZER_NAME).getComments().size());
	}
}
