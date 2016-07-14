package org.moosetechnology.jdt2famix.samples.basic;

public class VariousAttributeAccesses {
	
	String attribute;
	String initializedAttribute = "initializedAttribute";
	
	public VariousAttributeAccesses(String attribute) {
		this(attribute, "somethingElse");
	}

	public VariousAttributeAccesses(String attribute, String somethingElse) {
	}

	public void readAccessThroughAssignment() {
		String localVariable = attribute;
	}

	public void readAccessThroughReceiverOfMethodInvocation() {
		attribute.toString();
	}
	
	public void readAccessThroughArgumentInClassInitialization() {
		new String(attribute);
	}

	public void readAccessThroughArgumentInMethodInvocation() {
		"attribute".equals(attribute);
	}
	
	public void writeAccessThroughAssignment(String argument) {
		attribute = argument;
	}
	
	public void readAccessThroughIfLeftCondition() {
		if (attribute == "attribute")
			return;
	}

	public void readAccessThroughIfRightCondition() {
		if ("attribute" == attribute)
			return;
	}

	public void readAccessThroughSwitch() {
		//TODO
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

	
	public String readAccessThroughReturn() {
		return attribute;
	}
	
}
