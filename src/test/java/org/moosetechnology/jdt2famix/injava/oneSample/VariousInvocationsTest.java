package org.moosetechnology.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.VariousInvocations;
import org.moosetechnology.model.famix.Invocation;

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
}
