package com.feenk.jdt2famix.injava;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.injava.InJavaImporter;
import com.feenk.jdt2famix.model.famix.Namespace;

public class InJavaImporterTest {

	@Test
	public void test() {
		InJavaImporter importer = new InJavaImporter();
		assertNotNull(importer.repository().getMetamodel().getDescription(Namespace.class));
	}

}
