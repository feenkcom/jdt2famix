package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.feenk.jdt2famix.samples.basic.EnumWithComments;
import com.feenk.jdt2famix.model.famix.Enum;

public class EnumWithCommentsTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return EnumWithComments.class;
	}

	@Test
	public void testEnumJavadoc() {
		assertEquals(1, type.getComments().size());
	}

	@Test
	public void testEnumValueJavadoc() {
		Enum famixEnum = (Enum) type;
		assertEquals(1, famixEnum.getValues().stream().findAny().get().getComments().size());
	}
}
