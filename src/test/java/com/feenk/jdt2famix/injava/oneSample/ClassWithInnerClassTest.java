package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.ClassWithInnerClass;

import com.feenk.jdt2famix.model.famix.Type;

public class ClassWithInnerClassTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithInnerClass.class;
	}

	@Test
	public void testTypes() {
		assertEquals(2, importer.types().stream().filter(t -> !t.getIsStub()).count());
	}

	@Test
	public void testTopClass() {
		assertEquals(1, type.getTypes().size());
	}

	@Test
	public void testInnerClass() {
		Type innerClass = importer.types().stream().filter(t -> t.getName().equals("InnerClass")).findAny().get();
		assertEquals(type, innerClass.getContainer());
	}
	
}
