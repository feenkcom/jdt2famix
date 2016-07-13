package org.moosetechnology.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.VariousAccesses;

public class VariousAccessesTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return VariousAccesses.class;
	}

	@Test
	public void testReadAccessMethod() {
		assertEquals(1, methodNamed("returnAccess").getAccesses().size());
	}
	
}
