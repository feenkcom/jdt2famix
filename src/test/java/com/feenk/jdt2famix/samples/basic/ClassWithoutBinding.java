package com.feenk.jdt2famix.samples.basic;

final public class ClassWithoutBinding {
//
//	  static CharMatcher precomputeCharMatcher(  CharMatcher matcher){
//	    return matcher;
//	  }
//	  static long systemNanoTime(){
//	    return TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
//	  }
	  static <T extends Enum<T>> T getEnumIfPresent(T x){
	      return x;
	  }
//	  static String formatCompact4Digits(  double value){
//	    return "" + ((Number)(Object)value).toPrecision(4);
//	  }
//	@JsType(isNative=true,name="Number",namespace=GLOBAL) private static class Number {
//	    public native double toPrecision(    int precision);
//	  }

	private ClassWithoutBinding(){}
	
}
