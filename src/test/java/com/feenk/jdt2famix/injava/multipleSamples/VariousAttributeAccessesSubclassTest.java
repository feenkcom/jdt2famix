package com.feenk.jdt2famix.injava.multipleSamples;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.JavaFiles;
import com.feenk.jdt2famix.samples.basic.VariousAttributeAccesses;
import com.feenk.jdt2famix.samples.basic.VariousAttributeAccessesSubclass;

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
