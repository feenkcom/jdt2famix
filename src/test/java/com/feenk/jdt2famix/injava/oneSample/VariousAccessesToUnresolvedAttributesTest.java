package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.model.famix.Attribute;
import com.feenk.jdt2famix.samples.basic.VariousAccessesToUnresolvedAttributes;

public class VariousAccessesToUnresolvedAttributesTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return VariousAccessesToUnresolvedAttributes.class;
	}

	@Test
	public void test() {
		assertEquals(2, type.getAttributes().size());
		for (Attribute attr : type.getAttributes()) {
			assertEquals(type, attr.getParentType());
		}
	}

}
