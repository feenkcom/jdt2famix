package com.feenk.jdt2famix.injava.oneSample;

import org.junit.Test;

public class InnerClassInvokedFromOutside extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return InnerClassInvokedFromOutside.class;
	}

	@Test
	public void test() {
		//TODO: check the instantiation and caller
	}
	
}
