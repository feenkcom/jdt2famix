package com.feenk.jdt2famix.samples.basic;

import java.util.Collection;

public class ClassWithInnerClassDefinedInLambda {
	public void methodWithInnerEmptyClassInSimpleLambda(Collection<String> list) {
		list.forEach(s -> new EmptyClass() {
			@Override
			public boolean equals(Object obj) {return true;};
		});
	}

	public void methodWithInnerEmptyClassInLambdaBlock(Collection<String> list) {
		list.forEach(s -> { 
			EmptyClass e = new EmptyClass() {
				@Override
				public boolean equals(Object obj) {return true;};
			};
			e.toString();
		});
	}

	public void methodWithInnerClassInLambdaBlock() {
		ParameterizableInterface<Boolean> c = value -> {
			EmptyClass e = new EmptyClass() {
				@Override
				public boolean equals(Object obj) {
					return true;
				};
			};
			return e.equals(value);
		};
		c.invoke("string");
	}
	
	public void methodWithInnerSameClassInLambdaBlock(Collection<String> list) {
		list.forEach(s -> { 
			ClassWithInnerClassDefinedInLambda e = new ClassWithInnerClassDefinedInLambda() {
				@Override
				public boolean equals(Object obj) {return true;};
			};
			e.toString();
		});
	}
}
