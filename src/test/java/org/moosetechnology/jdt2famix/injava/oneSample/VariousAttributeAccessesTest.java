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
		assertEquals(1, methodNamed("readAccessThroughArgumentInClassInitialization").getAccesses().size());		
		assertFalse(methodNamed("readAccessThroughArgumentInClassInitialization").getAccesses().stream().anyMatch(a -> a.getIsWrite()));
	}

	@Test
	public void testWriteAccessThroughAssignment() {
		assertEquals(2, methodNamed("writeAccessThroughAssignment").getAccesses().size());
		assertTrue(methodNamed("writeAccessThroughAssignment").getAccesses().stream().anyMatch(a -> a.getIsWrite()));
		assertTrue(methodNamed("writeAccessThroughAssignment").getAccesses().stream().anyMatch(a -> !a.getIsWrite()));
	}

	@Test
	public void testReadAccessThroughReturn() {
		assertEquals(1, methodNamed("readAccessThroughReturn").getAccesses().size());
	}
	
	@Test 
	public void readAccessThroughDoWhileLeftCondition() {
		assertEquals(1, methodNamed("readAccessThroughDoWhileLeftCondition").getAccesses().size());
	}

	@Test 
	public void readAccessThroughDoWhileRightCondition() {
		assertEquals(1, methodNamed("readAccessThroughDoWhileRightCondition").getAccesses().size());
	}

	@Test 
	public void readAccessThroughDoWhilePlainCondition() {
		assertEquals(1, methodNamed("readAccessThroughDoWhilePlainCondition").getAccesses().size());
	}

	@Test 
	public void readAccessThroughDoWhileExpandedCondition() {
		assertEquals(1, methodNamed("readAccessThroughDoWhileExpandedCondition").getAccesses().size());
	}

	@Test 
	public void readAccessThroughIfRightCondition() {
		assertEquals(1, methodNamed("readAccessThroughIfRightCondition").getAccesses().size());
	}

	@Test 
	public void readAccessThroughIfLeftCondition() {
		assertEquals(1, methodNamed("readAccessThroughIfLeftCondition").getAccesses().size());
	}

	@Test 
	public void readAccessThroughIfPlainCondition() {
		assertEquals(1, methodNamed("readAccessThroughIfPlainCondition").getAccesses().size());
	}

	@Test 
	public void readAccessThroughSwitchVariable() {
		assertEquals(1, methodNamed("readAccessThroughSwitchVariable").getAccesses().size());
	}

	@Test 
	public void readAccessThroughForInit() {
		assertEquals(1, methodNamed("readAccessThroughForInit").getAccesses().size());
	}
	
	@Test 
	public void readAccessThroughForCondition() {
		assertEquals(1, methodNamed("readAccessThroughForCondition").getAccesses().size());
	}

	@Test 
	public void readAccessThroughExpandedForCondition() {
		assertEquals(1, methodNamed("readAccessThroughExpandedForCondition").getAccesses().size());
	}

	@Test 
	public void readAccessThroughConditionalOperatorCondition() {
		assertEquals(1, methodNamed("readAccessThroughConditionalOperatorCondition").getAccesses().size());
	}
}
