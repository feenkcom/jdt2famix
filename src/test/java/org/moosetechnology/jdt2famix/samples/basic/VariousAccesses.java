package org.moosetechnology.jdt2famix.samples.basic;

public class VariousAccesses {
	
	String attribute;
	String initializedAttribute = "initializedAttribute";

	public VariousAccesses() {
		attribute = "atttribute";
	}
	
	public void readAccessThroughInvocation() {
		attribute.toString();
	}

	public void readAccessThroughAssignment() {
		String localVariable = attribute;
	}

	public void readAccessThroughArgument() {
		new String(attribute);
	}
	
	public void writeAccessThroughAssignment() {
		attribute = "attribute";
	}
	
	public void readAccessThroughIf() {
		if (attribute == "attribute")
			return;
	}

	public void readAccessThroughSwitch() {
		if (attribute == "attribute")
			return;
	}

	public void readAccessThroughFor() {
		//TODO
	}

	public void readAccessThroughDo() {
		//TODO
	}

	public void readAccessThroughWhile() {
		//TODO
	}

	
	public String returnAccess() {
		return attribute;
	}
	
}
