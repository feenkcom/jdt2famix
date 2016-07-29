package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.feenk.jdt2famix.model.famix.Invocation;
import com.feenk.jdt2famix.model.famix.Method;
import com.feenk.jdt2famix.model.famix.Parameter;
import com.feenk.jdt2famix.samples.basic.VariousInvocations;

public class VariousInvocationsTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return VariousInvocations.class;
	}

	@Test
	public void testInvocationWithAttributeReceiver() {
		assertEquals(1, methodNamed("invocationWithAttributeReceiver").getOutgoingInvocations().size());
		Invocation invocation = methodNamed("invocationWithAttributeReceiver").getOutgoingInvocations().stream().findAny().get();
		assertNotNull(invocation.getReceiver());
		assertEquals(attributeNamed("attribute"), invocation.getReceiver());
	}
	
	@Test
	public void invocationWithLocalVariableReceiver() {
		assertEquals(1, methodNamed("invocationWithLocalVariableReceiver").getOutgoingInvocations().size());
		Invocation invocation = methodNamed("invocationWithLocalVariableReceiver").getOutgoingInvocations().stream().findAny().get();
		assertNull(invocation.getReceiver());
	}
	
	@Test
	public void invocationWithParameterReceiver() {
		assertEquals(1, methodNamed("invocationWithParameterReceiver").getOutgoingInvocations().size());
		Invocation invocation = methodNamed("invocationWithParameterReceiver").getOutgoingInvocations().stream().findAny().get();
		assertNotNull(invocation.getReceiver());
		assertTrue(invocation.getReceiver() instanceof Parameter);		
	}

	@Test
	public void twoInvocationWithTheSameParameterReceiver() {
		Method method = methodNamed("twoInvocationWithTheSameParameterReceiver");
		assertEquals(2, method.getOutgoingInvocations().size());
		method.getOutgoingInvocations().stream()
			.forEach(invocation -> assertTrue(invocation.getReceiver() == method.getParameters().stream().findAny().get()));
				
	}
}
