package org.moosetechnology.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.VariousAttributeAccesses;

public class VariousAttributeAccessesTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return VariousAttributeAccesses.class;
	}

	@Test
	public void testReadAccessThroughMethodInvocationReceiver() {
		assertEquals(1, methodNamed("readAccessThroughMethodInvocationReceiver").getAccesses().size());
	}
	
	@Test
	public void testReadAccessThroughConstructorInvocationReceiver() {
		assertEquals(1, methodNamed("VariousAttributeAccesses").getAccesses().size());		
	}
	
	@Test
	public void testReadAccessThroughArgument() {
		assertEquals(1, methodNamed("readAccessThroughArgumentInMethodInvocation").getAccesses().size());		
	}

	@Test
	public void testReadAccessThroughArgumentInConstructorInvocation() {
		assertEquals(1, methodNamed("readAccessThroughArgumentInConstructorInvocation").getAccesses().size());		
	}

	
}
