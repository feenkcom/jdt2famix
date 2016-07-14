package org.moosetechnology.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import java.util.stream.Stream;

import org.junit.Test;
import org.moosetechnology.jdt2famix.Famix;
import org.moosetechnology.jdt2famix.samples.basic.VariousAttributeAccesses;
import org.moosetechnology.model.famix.Method;
import org.moosetechnology.model.famix.Parameter;

public class VariousAttributeAccessesTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return VariousAttributeAccesses.class;
	}

	@Test
	public void testReadAccessThroughReceiverOfMethodInvocation() {
		assertEquals(1, methodNamed("readAccessThroughReceiverOfMethodInvocation").getAccesses().size());
	}
	
	@Test
	public void testReadAccessThroughArgumentInMethodInvocation() {
		assertEquals(1, methodNamed("readAccessThroughArgumentInMethodInvocation").getAccesses().size());		
	}
	
	@Test
	public void testReadAccessThroughConstructorInvocationReceiver() {
		Method constructorWithOneArgument = Famix.constructorsIn(type).filter(m -> m.getParameters().size() == 1).findAny().get();
		assertEquals(1, constructorWithOneArgument.getAccesses().size());
		assertNotNull(constructorWithOneArgument.getAccesses().stream().findAny().get().getVariable());
		assertTrue(constructorWithOneArgument.getAccesses().stream().findAny().get().getVariable() instanceof Parameter);
	}

	@Test
	public void testReadAccessThroughArgumentInConstructorInvocation() {
		assertEquals(1, methodNamed("readAccessThroughArgumentInConstructorInvocation").getAccesses().size());		
	}

	
}
