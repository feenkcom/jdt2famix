package org.moosetechnology.jdt2famix.injava.oneSample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.NestedAnnonymousClasses;
import org.moosetechnology.model.famix.Method;
import org.moosetechnology.model.famix.Type;

public class NestedAnnonymousClassesTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return NestedAnnonymousClasses.class;
	}

	@Test
	public void testTypes() {
		assertEquals(5, importer.getTypes().size());
	}
	
	@Test
	public void testTopMethod() {
		assertEquals(2, methodNamed("topMethod").getTypes().size());
		assertTrue(methodNamed("topMethod").getTypes().stream().anyMatch(t -> t.getName().equals("$1")));
		assertTrue(methodNamed("topMethod").getTypes().stream().anyMatch(t -> t.getName().equals("$2")));
	}

	@Test
	public void testNestedMethod() {
		Method nestedMethod = methodNamed("nestedMethod");
		assertEquals(1, nestedMethod.getTypes().size());
		Type typeInNestedMethod = nestedMethod.getTypes().stream().findAny().get();
		assertTrue(typeInNestedMethod.getContainer() instanceof Method);
		assertEquals(nestedMethod, (typeInNestedMethod.getContainer()));
		assertEquals("$1", nestedMethod.getParentType().getName());
//		assertEquals(NestedAnnonymousClasses.class.getName() + ".topMethod().$1.nestedMethod().$1", Famix.qualifiedNameOf(typeInNestedMethod));
	}
}
