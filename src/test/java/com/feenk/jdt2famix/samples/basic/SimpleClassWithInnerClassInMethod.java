package com.feenk.jdt2famix.samples.basic;

import java.util.Comparator;

public class SimpleClassWithInnerClassInMethod {

	public static void method(String name) {
		Comparator<String> comparator = new Comparator<String>() {
			@Override
			public int compare(String string1, String string2) {

				return string1.toString().compareTo(string2.toString());
			}
		};
	}
}
