package com.feenk.jdt2famix.samples.basic;

import com.feenk.notExistingPackage.Module;
//It is expected that this class contains compilation errors 
public class ClassWithOneAttrubuteWithoutBindingAndOneAccess {

	private final Module module;

	public void ClassWithOneAttrubuteWithoutBindingAndOneAccess(Module module) {
		this.module = module;
	}
}
