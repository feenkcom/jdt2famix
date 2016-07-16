package org.moosetechnology.jdt2famix.samples.basic;

public enum EnumWithConstructedValues {
	ONE(1), TWO(2), FOURTYTWO(42);
	int value;
	
	private EnumWithConstructedValues(int value) {
		this.value = value;
	}
}
