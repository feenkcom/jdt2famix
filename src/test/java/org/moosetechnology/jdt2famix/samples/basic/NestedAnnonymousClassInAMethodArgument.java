package org.moosetechnology.jdt2famix.samples.basic;

public class NestedAnnonymousClassInAMethodArgument {
	public void methodWithAnonymousClass() {
		methodWithAParameter(new Object() {});
	}
	public void methodWithAParameter(Object param) {};
}
