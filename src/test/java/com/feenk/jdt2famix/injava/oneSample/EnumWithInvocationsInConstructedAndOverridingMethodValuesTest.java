package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import java.util.stream.Stream;

import org.junit.Test;

import com.feenk.jdt2famix.Famix;
import com.feenk.jdt2famix.injava.InJavaImporter;
import com.feenk.jdt2famix.model.famix.Method;
import com.feenk.jdt2famix.model.famix.Type;
import com.feenk.jdt2famix.samples.basic.EnumWithInvocationsInConstructedAndOverridingMethodValues;

public class EnumWithInvocationsInConstructedAndOverridingMethodValuesTest extends
		OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return EnumWithInvocationsInConstructedAndOverridingMethodValues.class;
	}

	@Test
	public void testInitializerInvocation() {
		Method initializer = methodNamed(InJavaImporter.INITIALIZER_NAME);
		assertNotNull(initializer);
		assertEquals(1, initializer.getOutgoingInvocations().size());
	}
	
	@Test
	public void testInitializerAnonymousClass() {
		Method initializer = methodNamed(InJavaImporter.INITIALIZER_NAME);
		assertEquals(1, initializer.getTypes().size());
		Type anonymous = initializer.getTypes().stream().findAny().get();
		assertNotNull(Famix.superclassOf(anonymous));
	}
	
}
