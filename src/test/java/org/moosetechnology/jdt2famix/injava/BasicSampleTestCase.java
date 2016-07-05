package org.moosetechnology.jdt2famix.injava;

import org.junit.Before;
import org.moosetechnology.jdt2famix.injava.InJavaImporter;
import org.moosetechnology.model.famix.Type;

public abstract class BasicSampleTestCase {
	
	protected InJavaImporter importer;
	protected Type type;
	
	@Before
	public void setUp() {
		importer = new InJavaImporter();
		importer.runOne("src/test/java/org/moosetechnology/jdt2famix/samples/basic/" + sampleClass().getSimpleName() + ".java");
		type = importer.getTypes().get(sampleClass().getName());
	}
	
	protected abstract Class<?> sampleClass();
	
}
