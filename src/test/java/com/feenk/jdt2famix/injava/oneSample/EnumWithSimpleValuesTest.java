package com.feenk.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.feenk.jdt2famix.injava.InJavaImporter;
import com.feenk.jdt2famix.model.famix.Enum;
import com.feenk.jdt2famix.model.famix.EnumValue;
import com.feenk.jdt2famix.samples.basic.EnumWithSimpleValues;

public class EnumWithSimpleValuesTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {return EnumWithSimpleValues.class;}
	
//	@Before
//	public void setUp() {
//		importer = new InJavaImporter();
//		importer.runOne("src/test/java/org/moosetechnology/jdt2famix/samples/basic/" + "EnumWithSimpleValues" + ".java");
//	}

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
}
