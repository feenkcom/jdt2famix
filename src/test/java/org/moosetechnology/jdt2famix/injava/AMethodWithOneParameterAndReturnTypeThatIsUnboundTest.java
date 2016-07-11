package org.moosetechnology.jdt2famix.injava;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.AMethodWithOneParameterAndReturnTypeThatIsUnbound;
import org.moosetechnology.model.famix.Method;

public class AMethodWithOneParameterAndReturnTypeThatIsUnboundTest extends
		BasicSampleTestCase {

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
