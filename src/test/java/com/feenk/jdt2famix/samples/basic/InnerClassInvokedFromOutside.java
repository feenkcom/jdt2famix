package com.feenk.jdt2famix.samples.basic;

public class InnerClassInvokedFromOutside {
	public static void method() {
		new OuterClass().new InnerClass().innerMethod();
	}
}

class OuterClass {
	public class InnerClass {
		public void innerMethod(){
			System.out.println("");
		}
	}
}