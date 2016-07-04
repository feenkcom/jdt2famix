package com.tudorgirba.jdt2famix.injava;

import org.junit.Before;
import org.moosetechnology.jdt2famix.injava.InJavaImporter;

public abstract class BasicSampleTestCase {
	
	protected InJavaImporter importer;

	@Before
	public void setUp() {
		importer = new InJavaImporter();
		importer.runOne("src/test/java/org/moosetechnology/jdt2famix/samples/basic/" + sampleFileName() + ".java");
	}
	
	protected abstract String sampleFileName();
	
}
