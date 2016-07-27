package com.feenk.jdt2famix.samples.basic;

public class ClassWithExceptions {
	public void method() throws Exception {
		try {}
		catch(RuntimeException e) {
			throw e;
		}
	}

	public void methodThrowingInstantiatedException() throws Exception {
		throw new RuntimeException();
	}

	public void methodThrowingExceptionReturnedFromAnotherMethod() throws Exception {
		throw exception();
	}

	private RuntimeException exception() {
		return null;
	}
		
}
