package com.feenk.jdt2famix.injava;

import com.feenk.jdt2famix.injava.InJavaImporter;
import com.feenk.jdt2famix.model.famix.Method;

public class InJavaTestCase {

	protected InJavaImporter importer;

	protected Method methodNamed(String name) {
		return importer.methods()
				.stream()
	            .filter(m -> m.getName().equals(name))
	            .findAny()
	            .get();
	}
}
