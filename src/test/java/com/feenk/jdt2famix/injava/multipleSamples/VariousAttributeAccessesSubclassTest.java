package com.feenk.jdt2famix.injava.multipleSamples;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.VariousAttributeAccesses;
import org.moosetechnology.jdt2famix.samples.basic.VariousAttributeAccessesSubclass;

import com.feenk.jdt2famix.JavaFiles;

public class VariousAttributeAccessesSubclassTest extends
		MultipleSamplesTestCase {

	@Override
	protected void sampleClassesIn(JavaFiles javaFiles) {
		javaFiles.oneJavaFile(fileNameFor(VariousAttributeAccessesSubclass.class));
		javaFiles.oneJavaFile(fileNameFor(VariousAttributeAccesses.class));
	}

	@Test
	public void testAttributeReadAccessThroughParameterInSuperConstructorInvocation() {
		assertEquals(1, methodNamed("VariousAttributeAccessesSubclass").getAccesses().size());
	}

	@Test
	public void testAttributeReadAccessThroughArgumentInSuperInvocation() {
		assertEquals(1, methodNamed("readAccessThroughArgumentInSuperInvocation").getAccesses().size());
	}
	
}
