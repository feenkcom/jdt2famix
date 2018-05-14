package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import com.feenk.jdt2famix.Famix;
import com.feenk.jdt2famix.model.famix.AbstractFileAnchor;
import com.feenk.jdt2famix.model.famix.FileAnchor;
import com.feenk.jdt2famix.model.famix.Method;
import com.feenk.jdt2famix.model.famix.Type;
import com.feenk.jdt2famix.samples.basic.NestedAnnonymousClasses;

public class NestedAnnonymousClassesTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return NestedAnnonymousClasses.class;
	}

	@Test
	@Ignore
	public void testTypes() {
		assertEquals(5, importer.types().size());
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
		assertEquals(NestedAnnonymousClasses.class.getName() + ".topMethod().$1.nestedMethod().$1", Famix.qualifiedNameOf(typeInNestedMethod));
	}
	
	@Test
	public void testAllNonStubTypesHaveSourceAnchor() {
		AbstractFileAnchor typeAnchor = (AbstractFileAnchor) type.getSourceAnchor(); 		
		importer.types().stream()
			.filter(t -> ! t.getIsStub())
			.forEach( t -> assertEquals(typeAnchor.getFileName(), ((AbstractFileAnchor) t.getSourceAnchor()).getFileName()));
	}
}
