package org.moosetechnology.jdt2famix.samples.basic;

public class ClassWithInvocationFromStaticInitializer {
	static {
		method();
	}
	
	public static void method() { }
}
