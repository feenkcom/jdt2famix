package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.samples.basic.AnnotationWithComments;

public class AnnotationWithCommentsTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return AnnotationWithComments.class;
	}

	@Test
	public void testTypeSourceAnchor() {
		assertEquals(1, type.getComments().size());
	}

	@Test
	public void testAttributeSourceAnchor() {
		assertEquals(1, type.getAttributes().stream().findAny().get().getComments().size());
	}
}
