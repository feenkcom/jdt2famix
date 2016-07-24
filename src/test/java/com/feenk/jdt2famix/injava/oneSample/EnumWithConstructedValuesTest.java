package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.feenk.jdt2famix.injava.InJavaImporter;
import com.feenk.jdt2famix.model.famix.Enum;
import com.feenk.jdt2famix.model.famix.EnumValue;
import com.feenk.jdt2famix.samples.basic.EnumWithConstructedValues;

public class EnumWithConstructedValuesTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return EnumWithConstructedValues.class;
	}

	@Test
	public void testTypeNotNull() {
		assertNotNull(type);
	}
	
	@Test
	public void testValues() {
		Collection<EnumValue> values = ((Enum) type).getValues();
		assertEquals(3, values.size());
		values.stream().anyMatch(v -> v.getName().equals("ONE"));
		values.stream().anyMatch(v -> v.getName().equals("TWO"));
		values.stream().anyMatch(v -> v.getName().equals("FOURTYTWO"));
	}
	
	@Test 
	public void testConstructor() {
		assertEquals(2, type.getMethods().size());
		assertEquals(InJavaImporter.CONSTRUCTOR_KIND, methodNamed(EnumWithConstructedValues.class.getSimpleName()).getKind());
		assertFalse(type.getMethods().stream().findAny().get().getIsStub());
	}
	
}
