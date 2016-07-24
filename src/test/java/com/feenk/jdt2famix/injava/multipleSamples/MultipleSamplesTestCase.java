package com.feenk.jdt2famix.injava.multipleSamples;

import java.nio.file.Paths;

import org.junit.Before;

import com.feenk.jdt2famix.JavaFiles;
import com.feenk.jdt2famix.injava.InJavaImporter;
import com.feenk.jdt2famix.injava.InJavaTestCase;

public abstract class MultipleSamplesTestCase extends InJavaTestCase {
	
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
		return basicSamplesPath() + "/" + clazz.getSimpleName() + ".java";
	}
	protected String basicSamplesPath() {
		String relativePath = "src/test/java/org/moosetechnology/jdt2famix/samples/basic/";
		return Paths.get(relativePath).toAbsolutePath().normalize().toString();
	}

}