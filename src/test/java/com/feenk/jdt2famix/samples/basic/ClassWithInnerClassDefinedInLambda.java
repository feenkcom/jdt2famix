package com.feenk.jdt2famix.samples.basic;

import java.util.Collection;

public class ClassWithInnerClassDefinedInLambda {
	public void methodWithInnerClassInSimpleLambda(Collection<String> list) {
		list.forEach(s -> new EmptyClass() {
			@Override
			public boolean equals(Object obj) {return true;};
		});
	}

	public void methodWithInnerClassInLambdaBlock(Collection<String> list) {
		list.forEach(s -> { new EmptyClass() {
			@Override
			public boolean equals(Object obj) {return true;};
		};});
	}
}
