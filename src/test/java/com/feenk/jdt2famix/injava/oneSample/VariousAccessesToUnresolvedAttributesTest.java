package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.VariousAccessesToUnresolvedAttributes;

public class VariousAccessesToUnresolvedAttributesTest extends
		OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return VariousAccessesToUnresolvedAttributes.class;
	}

	@Test
	public void test() {
		assertEquals(1, type.getAttributes().size());
	}
	
}
