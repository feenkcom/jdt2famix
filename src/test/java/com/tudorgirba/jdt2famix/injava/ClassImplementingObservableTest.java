package com.tudorgirba.jdt2famix.injava;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.ClassImplementingObservable;

public class ClassImplementingObservableTest extends BasicSampleTestCase {

	@Override
	protected String sampleFileName() {
		return ClassImplementingObservable.class.getSimpleName();
	}

	@Test
	public void test() {
		assertEquals(2, importer.getTypes().size());
	}
}
