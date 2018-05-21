package com.feenk.jdt2famix.injava.multipleSamples;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.JavaFiles;
import com.feenk.jdt2famix.model.famix.FileAnchor;
import com.feenk.jdt2famix.model.famix.IndexedFileAnchor;
import com.feenk.jdt2famix.model.famix.Type;
import com.feenk.jdt2famix.samples.basic.EmptyClass;

public class AllBasicSamples extends MultipleSamplesTestCase {

	@Override
	protected void sampleClassesIn(JavaFiles javaFiles) {
		javaFiles.deepJavaFiles(basicSamplesPath());
	}
	
	@Test
	public void testSourceAnchorIgnoresRoot() {
		Type type = importer.types().named(EmptyClass.class.getName());
		assertTrue(((IndexedFileAnchor) type.getSourceAnchor()).getFileName().equals(EmptyClass.class.getSimpleName()+".java"));
	}
}