package com.feenk.jdt2famix.samples.basic;

public class VariousAttributeAccessesSubclass extends VariousAttributeAccesses {

	public VariousAttributeAccessesSubclass(String name) {
		super(name);
	}
	
	public void readAccessThroughArgumentInSuperInvocation() {
		super.writeAccessThroughAssignment(attribute);
	}

}