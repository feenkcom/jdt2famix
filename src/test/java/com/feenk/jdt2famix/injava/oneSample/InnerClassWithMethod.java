package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.feenk.jdt2famix.model.famix.Method;
import com.feenk.jdt2famix.model.famix.Type;
import com.feenk.jdt2famix.samples.basic.SimpleClassWithInnerClassInMethod;

public class InnerClassWithMethod extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return SimpleClassWithInnerClassInMethod.class;
	}

	@Test
	public void testMethodsWithNoNameShouldNotBeCreated() {
		for (Method method : importer.methods().get()) {
			assertNotEquals("", method.getName());
		}
	}

	@Test
	public void testClassesWithNoNameShouldNotBeCreated() {
		for (Type type : importer.types().get()) {
			assertNotEquals("", type.getName());
		}
	}

}
