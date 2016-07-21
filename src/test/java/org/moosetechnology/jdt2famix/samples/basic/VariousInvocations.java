package org.moosetechnology.jdt2famix.samples.basic;

public class VariousInvocations {
	public String attribute;

	public void invocationWithAttributeReceiver() {
		attribute.toString();
	}

	public void invocationWithLocalVariableReceiver() {
		String localVariable = "string";
		localVariable.toString();
	}

	public void invocationWithParameterReceiver(String parameter) {
		parameter.toString();
	}

	public void twoInvocationWithTheSameParameterReceiver(String parameter) {
		parameter.toString();
		parameter.length();
	}
}
