package org.moosetechnology.jdt2famix.samples.basic;

public class NestedAnnonymousClasses {
	public void method() {
		new Object() {
			public void method() {
				new Object() {
				};
			}
		};
	}
}
