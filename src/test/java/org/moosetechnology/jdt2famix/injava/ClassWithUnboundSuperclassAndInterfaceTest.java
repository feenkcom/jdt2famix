package org.moosetechnology.jdt2famix.injava;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.ClassWithUnboundSuperclassAndInterface;

public class ClassWithUnboundSuperclassAndInterfaceTest extends
		BasicSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithUnboundSuperclassAndInterface.class;
	}

	@Test
	public void testSuperclass() {
		assertEquals(2, type.getSuperInheritances().size());
	}
	
}
