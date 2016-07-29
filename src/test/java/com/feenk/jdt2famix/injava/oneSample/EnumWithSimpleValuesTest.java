package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;

import com.feenk.jdt2famix.model.famix.Enum;
import com.feenk.jdt2famix.model.famix.EnumValue;
import com.feenk.jdt2famix.model.famix.FileAnchor;
import com.feenk.jdt2famix.samples.basic.EnumWithSimpleValues;

public class EnumWithSimpleValuesTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {return EnumWithSimpleValues.class;}
	
	@Test
	public void testEnumType() {
		assertNotNull(type);
		assertEquals(Enum.class, type.getClass());
	}

	@Test
	public void testEnumValues() {
		Collection<EnumValue> values = ((Enum) type).getValues();
		assertEquals(3, values.size());
		values.stream().anyMatch(v -> v.getName().equals("ONE"));
		values.stream().anyMatch(v -> v.getName().equals("TWO"));
		values.stream().anyMatch(v -> v.getName().equals("THREE"));
	}
	
	@Test
	public void testEnumSourceAnchor() {
		assertNotNull(type.getSourceAnchor());
	}

	@Test
	public void testEnumValueSourceAnchor() {
		Collection<EnumValue> values = ((Enum) type).getValues();
		FileAnchor typeAnchor = (FileAnchor) type.getSourceAnchor(); 
		values.stream().forEach(value -> assertNotNull(value.getSourceAnchor()));
		values.stream().forEach(value -> assertEquals(typeAnchor.getFileName(), ((FileAnchor) value.getSourceAnchor()).getFileName()));
	}
}
