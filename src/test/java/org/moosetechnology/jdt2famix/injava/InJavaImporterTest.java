package org.moosetechnology.jdt2famix.injava;

import static org.junit.Assert.*;

import org.junit.Test;
import org.moosetechnology.model.famix.Namespace;

public class InJavaImporterTest {

	@Test
	public void test() {
		InJavaImporter importer = new InJavaImporter();
		assertNotNull(importer.repository().getMetamodel().getDescription(Namespace.class));
	}

}
