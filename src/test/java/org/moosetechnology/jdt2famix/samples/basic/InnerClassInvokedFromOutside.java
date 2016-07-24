package org.moosetechnology.jdt2famix.samples.basic;

public class InnerClassInvokedFromOutside {
	public static void method() {
		OuterClass outer = new OuterClass();
		outer.new InnerClass().innerMethod();
	}
}

class OuterClass {
	public class InnerClass {
		public void innerMethod(){
			System.out.println("");
		}
	}
}