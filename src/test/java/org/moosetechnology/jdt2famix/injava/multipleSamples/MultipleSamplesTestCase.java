package org.moosetechnology.jdt2famix.injava.multipleSamples;

import org.junit.Before;
import org.junit.BeforeClass;
import org.moosetechnology.jdt2famix.JavaFiles;
import org.moosetechnology.jdt2famix.injava.InJavaImporter;
import org.moosetechnology.model.famix.Attribute;
import org.moosetechnology.model.famix.Method;
import org.moosetechnology.model.famix.Type;

public abstract class MultipleSamplesTestCase {
	
	protected InJavaImporter importer;
	
	@Before
	public void before() {
		importer = new InJavaImporter();
		JavaFiles javaFiles = new JavaFiles();
		this.sampleClassesIn(javaFiles);
		importer.run(javaFiles);
		setUp();
	}
	
	protected void setUp() {
		// override this one if you need custom set up  
	}

	protected abstract void sampleClassesIn(JavaFiles javaFiles);
	protected String fileNameFor(Class<?> clazz) {
		return "src/test/java/org/moosetechnology/jdt2famix/samples/basic/" + clazz.getSimpleName() + ".java";
	}
	
}