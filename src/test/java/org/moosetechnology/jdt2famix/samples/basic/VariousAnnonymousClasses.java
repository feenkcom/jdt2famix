package org.moosetechnology.jdt2famix.samples.basic;

public class VariousAnnonymousClasses {
	
	public void methodWithEmptyClass() {
		new EmptyClass() {};
	}

	public void methodWithParameterizedType() {
		new SimpleParameterizableClass<String>() {};
	}

}
