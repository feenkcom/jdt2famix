package org.moosetechnology.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.ClassWithOneNew;
import org.moosetechnology.model.famix.BehaviouralEntity;
import org.moosetechnology.model.famix.Invocation;
import org.moosetechnology.model.famix.Method;

public class ClassWithOneNewTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithOneNew.class;
	}
	
	@Test
	public void testInvocation() {
		assertEquals(1, methodNamed("method").getOutgoingInvocations().size());
		Invocation invocation = methodNamed("method").getOutgoingInvocations().stream().findAny().get();
		Method defaultConstructor = (Method) invocation.getCandidates().stream().findAny().get();
		assertNotNull(defaultConstructor);
		assertEquals("constructor", defaultConstructor.getKind());
		assertTrue(defaultConstructor.getIsStub());
		assertEquals(ClassWithOneNew.class.getSimpleName(), defaultConstructor.getName());
		assertEquals(type, defaultConstructor.getParentType());
	}

}
