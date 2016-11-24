package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.feenk.jdt2famix.model.famix.Invocation;
import com.feenk.jdt2famix.model.famix.Method;
import com.feenk.jdt2famix.samples.basic.ClassWithOneAttrubuteWithoutBindingAndOneAccess;
import com.feenk.jdt2famix.samples.basic.ClassWithOneClassInstantiation;

public class ClassWithOneAttrubuteWithoutBindingAndOneAccessTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithOneAttrubuteWithoutBindingAndOneAccess.class;
	}
	
	@Test
	public void testAttribute() {
		assertEquals(1, methodNamed("ClassWithOneAttrubuteWithoutBindingAndOneAccess").getAccesses().size());
//		Invocation invocation = methodNamed("method").getOutgoingInvocations().stream().findAny().get();
//		Method defaultConstructor = (Method) invocation.getCandidates().stream().findAny().get();
//		assertNotNull(defaultConstructor);
//		assertEquals("constructor", defaultConstructor.getKind());
//		assertTrue(defaultConstructor.getIsStub());
//		assertEquals(ClassWithOneClassInstantiation.class.getSimpleName(), defaultConstructor.getName());
//		assertEquals(type, defaultConstructor.getParentType());
	}

}
