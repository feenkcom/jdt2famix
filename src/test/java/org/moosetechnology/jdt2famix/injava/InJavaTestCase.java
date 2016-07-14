package org.moosetechnology.jdt2famix.injava;

import java.util.stream.Stream;

import org.moosetechnology.model.famix.Method;

public class InJavaTestCase {

	protected InJavaImporter importer;

	protected Method methodNamed(String name) {
		return methods()
	            .filter(m -> m.getName().equals(name))
	            .findAny()
	            .get();
	}

	private Stream<Method> methods() {
		return importer
				.getMethods()
				.entrySet()
				.stream()
				.map(p -> p.getValue());
	}

}
