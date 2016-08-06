package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.samples.basic.ClassWithVariousAnnotations;

public class ClassWithVariousAnnotationsTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithVariousAnnotations.class;
	}

	@Test
	public void testNoAnnotationsForTypeDueAnnotationsNotBeingResolvable() {
		assertEquals(0, type.getAnnotationInstances().size());
	}
	
}
