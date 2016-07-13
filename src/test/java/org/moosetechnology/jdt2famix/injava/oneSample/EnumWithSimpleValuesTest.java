package org.moosetechnology.jdt2famix.injava.oneSample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.jdt2famix.samples.basic.EnumWithSimpleValues;
import org.moosetechnology.model.famix.Enum;

public class EnumWithSimpleValuesTest extends OneSampleTestCase {

	@Override
	protected Class<?> sampleClass() {
		return EnumWithSimpleValues.class;
	}

	@Test
	public void testEnumType() {
		assertNotNull(type);
		assertEquals(Enum.class, type.getClass());
	}
	
}
