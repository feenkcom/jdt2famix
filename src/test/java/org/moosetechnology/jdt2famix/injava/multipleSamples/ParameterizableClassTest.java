package org.moosetechnology.jdt2famix.injava.multipleSamples;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.JavaFiles;
import org.moosetechnology.jdt2famix.samples.basic.SimpleParameterizableClass;
import org.moosetechnology.jdt2famix.samples.basic.SimpleSubclassOfParameterizedType;
import org.moosetechnology.model.famix.Class;
import org.moosetechnology.model.famix.ParameterizableClass;

public class ParameterizableClassTest extends MultipleSamplesTestCase {

	@Override
	protected void sampleClassesIn(JavaFiles javaFiles) {
		javaFiles.oneJavaFile(fileNameFor(SimpleParameterizableClass.class));
		javaFiles.oneJavaFile(fileNameFor(SimpleSubclassOfParameterizedType.class));
	}
	
	@Test
	public void test2Types() {
		assertEquals(4, importer.getTypes().size());
		assertTrue(importer.getTypes().get(SimpleSubclassOfParameterizedType.class.getName()) instanceof Class);
		assertTrue(importer.getTypes().get(SimpleParameterizableClass.class.getName()) instanceof ParameterizableClass);
		
	}

}
