package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.Famix;
import com.feenk.jdt2famix.model.famix.Type;
import com.feenk.jdt2famix.samples.basic.ClassWithInnerClass;

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
		assertEquals(innerClass, importer.types().named(Famix.qualifiedNameOf(innerClass)));
	}

	@Test
	public void testInnerClassSourceAnchor() {
		Type innerClass = importer.types().stream().filter(t -> t.getName().equals("InnerClass")).findAny().get();
		assertNotNull(innerClass.getSourceAnchor());
	}
	
}
