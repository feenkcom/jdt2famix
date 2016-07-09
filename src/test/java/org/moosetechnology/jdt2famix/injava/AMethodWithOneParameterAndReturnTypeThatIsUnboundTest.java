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
		assertEquals(importer.unknownType(), method.getDeclaredType());
	}
	
}
