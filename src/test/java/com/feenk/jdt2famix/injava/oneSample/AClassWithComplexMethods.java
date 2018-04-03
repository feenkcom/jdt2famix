package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.feenk.jdt2famix.model.famix.Method;
import com.feenk.jdt2famix.samples.basic.ClassWithComplexMethods;

/**
 * Test case for the cyclomatic complexity algorithm, 
 * 
 * @author rbonifacio
 */
public class AClassWithComplexMethods extends OneSampleTestCase {
	@Override
	protected Class<?> sampleClass() {
		return ClassWithComplexMethods.class;
	}

	@Test
	public void testComplexMethod1() {
		Method method = methodNamed("complexMethod1");
		assertEquals(1, method.getCyclomaticComplexity());
	}
	
	@Test
	public void testComplexMethod2() {
		Method method = methodNamed("complexMethod2");
		assertEquals(2, method.getCyclomaticComplexity());
	}
	
	@Test
	public void testComplexMethod3() {
		Method method = methodNamed("complexMethod3");
		assertEquals(3, method.getCyclomaticComplexity());
	}
	
	@Test
	public void testComplexMethod4() {
		Method method = methodNamed("complexMethod4");
		assertEquals(4, method.getCyclomaticComplexity());	
	}
	
	@Test 
	public void testComplexMethod5() {
		Method method = methodNamed("complexMethod5");
		assertEquals(5, method.getCyclomaticComplexity());		
	}
	
	@Test 
	public void testComplexMethod6() {
		Method method = methodNamed("complexMethod6");
		assertEquals(6, method.getCyclomaticComplexity());		
	}
}
