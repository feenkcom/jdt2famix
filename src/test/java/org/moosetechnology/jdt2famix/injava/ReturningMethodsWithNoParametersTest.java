package org.moosetechnology.jdt2famix.injava;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.ReturningMethodsWithNoParameters;
import org.moosetechnology.model.famix.Method;

public class ReturningMethodsWithNoParametersTest extends BasicSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ReturningMethodsWithNoParameters.class;
	}
	
	@Test
	public void testDeclaredType() {
		assertEquals(4, type.getMethods().size());
		type.getMethods().stream().forEach(m -> assertNotNull(m.getDeclaredType()));

		Method publicString = type.getMethods().stream().filter(m-> m.getName().equals("publicString")).findAny().get();
		assertNotNull(publicString.getDeclaredType());
		assertEquals("String", publicString.getDeclaredType().getName());

		Method publicInt = type.getMethods().stream().filter(m-> m.getName().equals("publicInt")).findAny().get();
		assertNotNull(publicInt.getDeclaredType());
		assertEquals("int", publicInt.getDeclaredType().getName());
	}


}
