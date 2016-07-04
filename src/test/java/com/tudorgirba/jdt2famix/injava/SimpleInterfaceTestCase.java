package com.tudorgirba.jdt2famix.injava;

import static org.junit.Assert.*;
import org.junit.Test;

public class SimpleInterfaceTestCase extends BasicSampleTestCase {

	@Override
	protected String sampleFile() {
		return "SimpleInterface.java";
	}

	@Test
	public void test() {
		assertEquals(1, importer.getNamespaces().size());
		assertEquals(1, importer.getTypes().size());
	}
	
}
