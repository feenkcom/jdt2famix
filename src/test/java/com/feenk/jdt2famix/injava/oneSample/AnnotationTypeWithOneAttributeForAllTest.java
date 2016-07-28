package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.samples.basic.AnnotationTypeWithOneAttributeForAll;

public class AnnotationTypeWithOneAttributeForAllTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return AnnotationTypeWithOneAttributeForAll.class;
	}

	@Test
	public void testAttributes() {
		assertEquals(2, type.getAttributes().size());
	}
}
