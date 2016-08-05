package com.feenk.jdt2famix.injava.multipleSamples;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.JavaFiles;
import com.feenk.jdt2famix.samples.basic.ClassWithAnnotationsForType;
import com.feenk.jdt2famix.samples.basic.ClassWithInnerClassDefinedInLambda;
import com.feenk.jdt2famix.samples.basic.ParameterizableInterface;

public class ClassWithInnerClassDefinedInLambdaTest extends MultipleSamplesTestCase {

	@Override
	protected void sampleClassesIn(JavaFiles javaFiles) {
		javaFiles.oneJavaFile(this.fileNameFor(ClassWithInnerClassDefinedInLambda.class));
		javaFiles.oneJavaFile(this.fileNameFor(ParameterizableInterface.class));
	}

	@Test
	public void testMethodWithInnerClassInLambdaBlock() {
		assertEquals(1, methodNamed("methodWithInnerClassInLambdaBlock").getTypes().size());
	}
	
}
