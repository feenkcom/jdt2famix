package com.feenk.jdt2famix.injava.multipleSamples;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.EmptyClass;
import org.moosetechnology.jdt2famix.samples.basic.SimpleParameterizableClass;
import org.moosetechnology.jdt2famix.samples.basic.VariousAnnonymousClasses;

import com.feenk.jdt2famix.Famix;
import com.feenk.jdt2famix.JavaFiles;
import com.feenk.jdt2famix.model.famix.Type;

public class VariousAnnonymousClassesTest extends MultipleSamplesTestCase {

	@Override
	protected void sampleClassesIn(JavaFiles javaFiles) {
		javaFiles.oneJavaFile(fileNameFor(VariousAnnonymousClasses.class));
		javaFiles.oneJavaFile(fileNameFor(SimpleParameterizableClass.class));
		javaFiles.oneJavaFile(fileNameFor(EmptyClass.class));
	}

	@Test
	public void testMethodWithEmptyClass() {
		assertEquals(1, methodNamed("methodWithEmptyClass").getTypes().size());
		Type annonymous = methodNamed("methodWithEmptyClass").getTypes().stream().findAny().get();
		assertEquals("$1", annonymous.getName());
		assertEquals(EmptyClass.class.getName(), Famix.qualifiedNameOf(Famix.superclassOf(annonymous)));
	}

	@Test
	public void testMethodWithParameterizedType() {
		assertEquals(1, methodNamed("methodWithParameterizedType").getTypes().size());
		Type annonymous = methodNamed("methodWithParameterizedType").getTypes().stream().findAny().get();
		assertEquals("$1", annonymous.getName());
		assertEquals(SimpleParameterizableClass.class.getSimpleName() + "<String>", Famix.superclassOf(annonymous).getName());
		assertEquals(SimpleParameterizableClass.class.getName() + "<String>", Famix.qualifiedNameOf(Famix.superclassOf(annonymous)));
	}
	
}
