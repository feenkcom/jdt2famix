package com.feenk.jdt2famix.samples.basic;

public enum EnumWithInvocationsInConstructedAndOverridingMethodValues {

	ONE(new String("1")) {
		@Override
		void hook() {
			string.toString();
		}
	};
	
	String string;

	EnumWithInvocationsInConstructedAndOverridingMethodValues(String s) {
		this.string = s;
	}
	
	abstract void hook();
}
