package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.UnresolvedInvocations;

public class UnresolvedInvocationsTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return UnresolvedInvocations.class;
	}
	
	@Test
	public void unresolvedClassInstantiation() {
		assertEquals(1, methodNamed("unresolvedClassInstantiation").getOutgoingInvocations().size());
		assertNotNull(methodNamed("ArrayList"));
		assertNotNull(methodNamed("ArrayList").getParentType());
		assertEquals(importer.types().named(ArrayList.class.getName()), methodNamed("ArrayList").getParentType());
	}
	
}
