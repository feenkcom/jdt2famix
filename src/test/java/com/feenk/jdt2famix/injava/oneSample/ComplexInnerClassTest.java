package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.feenk.jdt2famix.model.famix.Access;
import com.feenk.jdt2famix.model.famix.Method;
import com.feenk.jdt2famix.model.famix.Type;
import com.feenk.jdt2famix.samples.basic.ComplexClassWithInnerClassMethod;

public class ComplexInnerClassTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ComplexClassWithInnerClassMethod.class;
	}

	@Test
	public void testAccessFromInnerClass() {
		assertEquals(1, type.getMethods().size());
		Method method = (Method) type.getMethods().toArray()[0];
		Type innerClass = (Type) method.getTypes().toArray()[0];
		Method innerMethod = (Method) innerClass.getMethods().toArray()[0];
		assertEquals(1, innerMethod.getAccesses().size());
		Access access = (Access) innerMethod.getAccesses().toArray()[0];
		assertNotNull(access.getVariable());
		assertNotNull(access.getAccessor());
	}
}
