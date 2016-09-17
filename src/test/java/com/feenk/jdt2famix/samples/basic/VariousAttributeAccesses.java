package com.feenk.jdt2famix.samples.basic;

import java.util.List;

public class VariousAttributeAccesses {
	
	String attribute;
	String initializedAttribute = "initializedAttribute";
	boolean booleanAttribute = true;
	int intAttribute = 42;
	List<String> listAttribute;
	Integer integerAttribute = 42;
	
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

	public void writeAccessThroughThisAssignment(String argument) {
		this.attribute = argument;
	}
	
	public void readAccessThroughIfLeftCondition() {
		if (attribute == "attribute")
			return;
	}

	public void readAccessThroughIfRightCondition() {
		if ("attribute" == attribute)
			return;
	}

	public void readAccessThroughIfPlainCondition() {
		if (booleanAttribute)
			return;
	}

	public void readAccessThroughForInit() {
		for (int i = intAttribute; i < 42; i++) {}
	}

	public void readAccessThroughForCondition() {
		for (int i = 0; i < integerAttribute ; i++) {}
	}

	public void readAccessThroughExpandedForCondition() {
		for (String x : listAttribute) {}
	}

	public void readAccessThroughWhileLeftCondition() {
		while (attribute == "attribute") {}
	}

	public void readAccessThroughWhileRightCondition() {
		while ("attribute" == attribute) {}
	}

	public void readAccessThroughDoWhileLeftCondition() {
		do {} while (attribute == "attribute");
	}

	public void readAccessThroughDoWhileRightCondition() {
		do {} while ("attribute" == attribute);
	}
	
	public void readAccessThroughDoWhilePlainCondition() {
		do {} while (booleanAttribute);
	}

	public void readAccessThroughDoWhileExpandedCondition() {
		do {} while (true && booleanAttribute && (41 == 42) );
	}
	
	public void readAccessThroughSwitchVariable() {
		switch (intAttribute) {}
	}

	public void readAccessThroughConditionalOperatorCondition() {
		int x = (booleanAttribute) ? 42 : 42;
	}
	
	public String readAccessThroughReturn() {
		return attribute;
	}
	
	public void readAccessThroughSynchronized() {
		synchronized(attribute) {}
	}
	
}
