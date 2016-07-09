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
	public void testMethodModifiers() {
		assertEquals(8, type.getMethods().size());
		Method publicStaticVoidWithNoParameters = type.getMethods().stream().filter(m-> m.getName().equals("publicStaticVoidWithNoParameters")).findAny().get();
		assertEquals(2, publicStaticVoidWithNoParameters.getModifiers().size());
		assertTrue(publicStaticVoidWithNoParameters.getModifiers().stream().anyMatch(m -> m.equals("public")));
		assertTrue(publicStaticVoidWithNoParameters.getModifiers().stream().anyMatch(m -> m.equals("static")));
		assertNull(publicStaticVoidWithNoParameters.getDeclaredType());
		
		Method protectedStaticVoidWithNoParameters = type.getMethods().stream().filter(m-> m.getName().equals("protectedStaticVoidWithNoParameters")).findAny().get();
		assertEquals(2, protectedStaticVoidWithNoParameters.getModifiers().size());
		assertTrue(protectedStaticVoidWithNoParameters.getModifiers().stream().anyMatch(m -> m.equals("protected")));
		assertTrue(protectedStaticVoidWithNoParameters.getModifiers().stream().anyMatch(m -> m.equals("static")));
		
		Method privateStaticVoidWithNoParameters = type.getMethods().stream().filter(m-> m.getName().equals("privateStaticVoidWithNoParameters")).findAny().get();
		assertEquals(2, privateStaticVoidWithNoParameters.getModifiers().size());
		assertTrue(privateStaticVoidWithNoParameters.getModifiers().stream().anyMatch(m -> m.equals("private")));
		assertTrue(privateStaticVoidWithNoParameters.getModifiers().stream().anyMatch(m -> m.equals("static")));

		Method packageStaticVoidWithNoParameters = type.getMethods().stream().filter(m-> m.getName().equals("packageStaticVoidWithNoParameters")).findAny().get();
		assertEquals(2, packageStaticVoidWithNoParameters.getModifiers().size());
		assertTrue(packageStaticVoidWithNoParameters.getModifiers().stream().anyMatch(m -> m.equals("package")));
		assertTrue(packageStaticVoidWithNoParameters.getModifiers().stream().anyMatch(m -> m.equals("static")));

		Method packageVoidWithNoParameters = type.getMethods().stream().filter(m-> m.getName().equals("packageVoidWithNoParameters")).findAny().get();
		assertEquals(1, packageVoidWithNoParameters.getModifiers().size());
		assertTrue(packageVoidWithNoParameters.getModifiers().stream().anyMatch(m -> m.equals("package")));
	}

	@Test
	public void testClassModifiers() {
		assertEquals(1, type.getModifiers().size());
		assertTrue(type.getModifiers().stream().anyMatch(m -> m.equals("public")));
	}
}
