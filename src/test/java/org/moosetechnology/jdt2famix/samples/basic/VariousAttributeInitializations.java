package org.moosetechnology.jdt2famix.samples.basic;

public class VariousAttributeInitializations {
	static String CONSTANT = "constant"; 
	String singleInitializedAttribute = new String();
	Boolean booleanAttribute = true;
	boolean booleanPrimitiveAttribute = false;
	String fragment1InitializedAttribute, fragment2InitializedAttribute = new String();
	String attributeInitializedWithConstant = CONSTANT;

}
