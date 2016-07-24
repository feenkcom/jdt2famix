package com.feenk.jdt2famix.samples.basic;

public class ClassWithExceptions {
	public void method() throws Exception {
		try {}
		catch(RuntimeException e) {
			throw e;
		}
	}
}
