package com.feenk.jdt2famix.samples.basic;

public class VariousAttributeQualifiedInitializations {
	static SampleEnum attributeInitializedWithEnumValue = SampleEnum.ONE;
	SampleEnum attributeInitializedWithQualifiedConstant = VariousAttributeQualifiedInitializations.attributeInitializedWithEnumValue;
}

enum SampleEnum {ONE, TWO, FOURTYTWO}