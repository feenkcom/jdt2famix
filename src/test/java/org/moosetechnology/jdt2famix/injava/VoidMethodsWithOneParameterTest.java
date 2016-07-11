package org.moosetechnology.jdt2famix.injava;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.VoidMethodsWithOneParameter;

public class VoidMethodsWithOneParameterTest extends BasicSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return VoidMethodsWithOneParameter.class;
	}

	@Test
	public void test() {
		assertEquals(1, methodNamed("publicStaticVoidWithStringParameter").getParameters().size());
	}
}
