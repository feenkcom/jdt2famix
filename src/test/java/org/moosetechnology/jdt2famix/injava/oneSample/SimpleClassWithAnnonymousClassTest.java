package org.moosetechnology.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.SimpleClassWithAnnonymousClass;

public class SimpleClassWithAnnonymousClassTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return SimpleClassWithAnnonymousClass.class;
	}

	@Test
	public void test() {
		assertEquals(1, methodNamed("method").getTypes().size());
	}
}
