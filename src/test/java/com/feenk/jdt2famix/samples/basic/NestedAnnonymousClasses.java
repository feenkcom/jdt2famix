package com.feenk.jdt2famix.samples.basic;

public class NestedAnnonymousClasses {
	public void topMethod() {
		new Object() {
			public void nestedMethod() {
				new Object() {};
			}
		};
		new Object() {};
	}
}
