package com.feenk.jdt2famix.injava.multipleSamples;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.SimpleParameterizableClass;
import org.moosetechnology.jdt2famix.samples.basic.SimpleSubclassOfParameterizedType;

import com.feenk.jdt2famix.Famix;
import com.feenk.jdt2famix.JavaFiles;
import com.feenk.jdt2famix.model.famix.Class;
import com.feenk.jdt2famix.model.famix.ParameterizableClass;
import com.feenk.jdt2famix.model.famix.ParameterizedType;
import com.feenk.jdt2famix.model.famix.Type;

public class ParameterizableClassTest extends MultipleSamplesTestCase {

	private Type parameterizableClass;
	private Type parameterizedType;
	private Type subclass;


	@Override
	protected void sampleClassesIn(JavaFiles javaFiles) {
		javaFiles.oneJavaFile(fileNameFor(SimpleParameterizableClass.class));
		javaFiles.oneJavaFile(fileNameFor(SimpleSubclassOfParameterizedType.class));
	}
	
	@Override
	protected void setUp() {
		parameterizableClass = importer.types().named(SimpleParameterizableClass.class.getName());
		parameterizedType = Famix.superclassOf(importer.types().named(SimpleSubclassOfParameterizedType.class.getName()));
		subclass = importer.types().named(SimpleSubclassOfParameterizedType.class.getName());
	}
	
	@Test
	public void types() {
		assertEquals(9, importer.types().size());
		assertTrue(subclass instanceof Class);
		assertTrue(parameterizableClass instanceof ParameterizableClass);
		assertTrue(parameterizedType instanceof ParameterizedType);
	}
	
	@Test
	public void parameterizedTypeName() {
		assertEquals(SimpleParameterizableClass.class.getSimpleName() + "<String>", parameterizedType.getName());
	}

	@Test
	public void parameterizableClassName() {
		assertEquals(SimpleParameterizableClass.class.getSimpleName(), parameterizableClass.getName());
	}

	@Test
	public void connections() {
		assertEquals(parameterizableClass, ((ParameterizedType) parameterizedType).getParameterizableClass());
		assertEquals(parameterizedType, ((ParameterizableClass) parameterizableClass).getParameterizedTypes().stream().findAny().get());
	}

	@Test
	public void parameterizableClassParameters() {
		assertEquals(1, ((ParameterizableClass) parameterizableClass).getTypes().size());
		assertEquals("T", ((ParameterizableClass) parameterizableClass).getTypes().stream().findAny().get().getName());
	}

	@Test
	public void parameterizedTypeArguments() {
		assertEquals(1, ((ParameterizedType) parameterizedType).getArguments().size());
	}
	
}
