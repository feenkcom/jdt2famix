package org.moosetechnology.jdt2famix.injava.oneSample;

import org.junit.Before;
import org.moosetechnology.jdt2famix.injava.InJavaImporter;
import org.moosetechnology.jdt2famix.injava.InJavaTestCase;
import org.moosetechnology.model.famix.Attribute;
import org.moosetechnology.model.famix.Type;


public abstract class OneSampleTestCase extends InJavaTestCase {
	
	protected Type type;
	
	@Before
	public void setUp() {
		importer = new InJavaImporter();
		importer.runOne("src/test/java/org/moosetechnology/jdt2famix/samples/basic/" + sampleClass().getSimpleName() + ".java");
		type = importer.types().named(sampleClass().getName());
	}

	protected abstract Class<?> sampleClass();

	protected Attribute attributeNamed(String name) {
		return type.getAttributes().stream().filter(m-> m.getName().equals(name)).findAny().get();
	}
	
}
