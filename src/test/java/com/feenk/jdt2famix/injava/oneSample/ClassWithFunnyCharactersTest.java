package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.samples.basic.ClassWithFunnyCharacters;

public class ClassWithFunnyCharactersTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return ClassWithFunnyCharacters.class;
	}

	@Test
	public void test() {
		assertEquals(1, type.getMethods().size());
	}
}
