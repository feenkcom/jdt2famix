package org.moosetechnology.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.Famix;
import org.moosetechnology.jdt2famix.samples.basic.EmptyClass;
import org.moosetechnology.jdt2famix.samples.basic.VariousAnnonymousClasses;
import org.moosetechnology.model.famix.Type;

public class VariousAnnonymousClassesTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return VariousAnnonymousClasses.class;
	}

	@Test
	public void test() {
		assertEquals(1, methodNamed("methodWithEmptyClass").getTypes().size());
		Type annonymous = methodNamed("methodWithEmptyClass").getTypes().stream().findAny().get();
		assertEquals("$1", annonymous.getName());
		assertEquals(EmptyClass.class.getSimpleName(), Famix.superclassOf(annonymous).getName());
	}
	
}
