package org.moosetechnology.jdt2famix.injava;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.VoidMethodsWithNoParameters;
import org.moosetechnology.model.famix.Method;

public class VoidMethodsWithNoParametersTest extends BasicSampleTestCase {
	@Override
	protected Class<?> sampleClass() {
		return VoidMethodsWithNoParameters.class;
	}

	@Test
	public void test() {
		assertEquals(8, type.getMethods().size());
		Method publicStaticVoidWithNoParameters = type.getMethods().stream().filter(m-> m.getName().equals("publicStaticVoidWithNoParameters")).findAny().get();
		assertEquals(2, publicStaticVoidWithNoParameters.getModifiers().size());		
	}
}
