package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.VariousLocalVariables;

public class VariousLocalVariablesTest extends OneSampleTestCase {
	
	@Override
	protected Class<?> sampleClass() {
		return VariousLocalVariables.class;
	}
	
	@Test
	public void testLocalVariables() {
		assertEquals(3, methodNamed("method").getLocalVariables().size());
	}
}
