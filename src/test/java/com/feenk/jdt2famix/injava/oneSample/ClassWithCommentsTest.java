package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

	@Test
	public void testMethodWithOneLineComment() {
		assertEquals(1, methodNamed("methodWithOneLineComment").getComments().size());
	}

	@Test
	public void testMethodWithOneLineCommentIncludingALink() {
		assertEquals(1, methodNamed("methodWithOneLineCommentIncludingALink").getComments().size());
		assertEquals("//Method one line comment and link: http://feenk.com",
				methodNamed("methodWithOneLineCommentIncludingALink").getComments().stream().findAny().get()
						.getContent());
	}

	@Test
	public void testMethodWithMultiLineComment() {
		assertEquals(1, methodNamed("methodWithMultiLineComment").getComments().size());
	}

	@Test
	public void testMethodWithMultiLineCommentIncludingALink() {
		assertEquals(1, methodNamed("methodWithMultiLineCommentIncludingALink").getComments().size());
		assertTrue(methodNamed("methodWithMultiLineCommentIncludingALink").getComments().stream().findAny().get()
				.getContent().contains("feenk.com"));
	}
}
