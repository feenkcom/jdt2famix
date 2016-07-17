package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.VoidMethodsWithNoParameters;

import com.feenk.jdt2famix.model.famix.Method;

public class VoidMethodsWithNoParametersTest extends OneSampleTestCase {
	@Override
	protected Class<?> sampleClass() {
		return VoidMethodsWithNoParameters.class;
	}

	@Test
	public void testMethodsAreNotStub() {
		type.getMethods().stream().forEach(m -> assertFalse(m.getIsStub()));
	}
	
	@Test
	public void testPublicStaticVoidWithNoParameters() {
		assertEquals(8, type.getMethods().size());
		Method publicStaticVoidWithNoParameters = type.getMethods().stream().filter(m-> m.getName().equals("publicStaticVoidWithNoParameters")).findAny().get();
		assertTrue(publicStaticVoidWithNoParameters.getHasClassScope());
		assertEquals(1, publicStaticVoidWithNoParameters.getModifiers().size());
		assertEquals("public", publicStaticVoidWithNoParameters.getModifiers().stream().findAny().get());
		assertNull(publicStaticVoidWithNoParameters.getDeclaredType());
	}
	
	@Test
	public void testProtectedStaticVoidWithNoParameters() {		
		Method protectedStaticVoidWithNoParameters = type.getMethods().stream().filter(m-> m.getName().equals("protectedStaticVoidWithNoParameters")).findAny().get();
		assertEquals(1, protectedStaticVoidWithNoParameters.getModifiers().size());
		assertTrue(protectedStaticVoidWithNoParameters.getModifiers().stream().anyMatch(m -> m.equals("protected")));
		assertTrue(protectedStaticVoidWithNoParameters.getHasClassScope());
	}

	@Test
	public void testPrivateStaticVoidWithNoParameters() {
		Method privateStaticVoidWithNoParameters = type.getMethods().stream().filter(m-> m.getName().equals("privateStaticVoidWithNoParameters")).findAny().get();
		assertEquals(1, privateStaticVoidWithNoParameters.getModifiers().size());
		assertTrue(privateStaticVoidWithNoParameters.getModifiers().stream().anyMatch(m -> m.equals("private")));
		assertTrue(privateStaticVoidWithNoParameters.getHasClassScope());
	}
		
	@Test
	public void testPackageStaticVoidWithNoParameters() {
		Method packageStaticVoidWithNoParameters = type.getMethods().stream().filter(m-> m.getName().equals("packageStaticVoidWithNoParameters")).findAny().get();
		assertEquals(1, packageStaticVoidWithNoParameters.getModifiers().size());
		assertTrue(packageStaticVoidWithNoParameters.getModifiers().stream().anyMatch(m -> m.equals("package")));
		assertTrue(packageStaticVoidWithNoParameters.getHasClassScope());
	}
	
	@Test
	public void testPackageVoidWithNoParameters() {
		Method packageVoidWithNoParameters = type.getMethods().stream().filter(m-> m.getName().equals("packageVoidWithNoParameters")).findAny().get();
		assertEquals(1, packageVoidWithNoParameters.getModifiers().size());
		assertTrue(packageVoidWithNoParameters.getModifiers().stream().anyMatch(m -> m.equals("package")));
		assertNull(packageVoidWithNoParameters.getHasClassScope());		
	}

	@Test
	public void testClassModifiers() {
		assertEquals(1, type.getModifiers().size());
		assertTrue(type.getModifiers().stream().anyMatch(m -> m.equals("public")));
	}
}
