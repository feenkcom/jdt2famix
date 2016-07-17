package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.VariousConstructors;

public class VariousConstructorsTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return VariousConstructors.class;
	}

	@Test
	public void testConstructors() {
		assertEquals(4, type.getMethods().size());
		type.getMethods().stream().forEach(m -> m.getKind().equals("constructor"));
	}

}
