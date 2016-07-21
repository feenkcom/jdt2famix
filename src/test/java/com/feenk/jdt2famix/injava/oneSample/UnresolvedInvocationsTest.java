package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.EmptyClass;
import org.moosetechnology.jdt2famix.samples.basic.UnresolvedInvocations;

public class UnresolvedInvocationsTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return UnresolvedInvocations.class;
	}
	
	@Test
	public void unresolvedClassInstantiationArrayList() {
		assertEquals(1, methodNamed("unresolvedClassInstantiationArrayList").getOutgoingInvocations().size());
		assertNotNull(methodNamed("ArrayList"));
		assertNotNull(methodNamed("ArrayList").getParentType());
		assertEquals(importer.types().named(ArrayList.class.getName()), methodNamed("ArrayList").getParentType());
	}

	@Test
	public void unresolvedClassInstantiationToEmptyClass() {
		assertEquals(1, methodNamed("unresolvedClassInstantiationToEmptyClass").getOutgoingInvocations().size());
		assertNotNull(methodNamed("EmptyClass"));
		assertNotNull(methodNamed("EmptyClass").getParentType());
	}
	
}
