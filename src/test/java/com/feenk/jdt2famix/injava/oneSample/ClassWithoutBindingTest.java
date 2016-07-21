package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.ClassWithoutBinding;


public class ClassWithoutBindingTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithoutBinding.class;
	}
	
	@Test
	public void test() {
		assertEquals(1, importer.types().stream().filter(t -> !t.getIsStub()).toArray().length);
	}
}
