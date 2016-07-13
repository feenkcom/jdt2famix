package org.moosetechnology.jdt2famix.injava;

import org.moosetechnology.model.famix.Method;

public class InJavaTestCase {

	protected InJavaImporter importer;

	protected Method methodNamed(String name) {
		return importer
				.getTypes()
				.entrySet()
				.stream()
				.map(p -> p.getValue().getMethods())
				.flatMap(l -> l.stream())
	            .filter(m -> m.getName().equals(name))
	            .findAny()
	            .get();
	}

}
