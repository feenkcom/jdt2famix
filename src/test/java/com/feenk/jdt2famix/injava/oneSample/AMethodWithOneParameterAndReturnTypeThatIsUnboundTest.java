package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.AMethodWithOneParameterAndReturnTypeThatIsUnbound;

import com.feenk.jdt2famix.model.famix.Method;

public class AMethodWithOneParameterAndReturnTypeThatIsUnboundTest extends
		OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return AMethodWithOneParameterAndReturnTypeThatIsUnbound.class;
	}

	@Test
	public void testMethodDeclaredType() {
		Method method = methodNamed("withVariousAttributesParameterAndVariousConstructorsReturn");
		assertNotEquals(importer.unknownType(), method.getDeclaredType());
		assertEquals("VariousConstructors", method.getDeclaredType().getName());
		assertEquals(importer.unknownNamespace(), method.getDeclaredType().getContainer());
	}
	
	@Test
	public void testParameter() {
		Method method = methodNamed("withVariousAttributesParameterAndVariousConstructorsReturn");
		assertEquals(1, method.getParameters().size());
		assertNotEquals(importer.unknownType(), method.getParameters().stream().findAny().get().getDeclaredType());
		assertEquals("VariousAttributes", method.getParameters().stream().findAny().get().getDeclaredType().getName());
	}
	
}
