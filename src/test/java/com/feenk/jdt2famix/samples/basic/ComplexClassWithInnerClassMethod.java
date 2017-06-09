package com.feenk.jdt2famix.samples.basic;

import java.util.Collection;
import java.util.Set;

public class ComplexClassWithInnerClassMethod {

	public static InnerClassTemplate withTests(Set<Class> argUpperMetho) {
		return new InnerClassTemplate() {
			@Override
			public Set<Class> methodOfInnerClass(Collection<String> arg) {
				// Set<Class> testsToRun = new HashSet<Class>();
				// for (Class<?> each : argUpperMetho) {
				// System.out.println(each);
				// }
				return argUpperMetho;
			}
		};
	}

}
