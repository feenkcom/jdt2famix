package com.feenk.jdt2famix.injava;

import static org.junit.Assert.*;

import org.junit.Test;

import com.feenk.jdt2famix.injava.InJavaImporter;
import com.feenk.jdt2famix.model.famix.JavaSourceLanguage;
import com.feenk.jdt2famix.model.famix.Namespace;

public class InJavaImporterTest {

	@Test
	public void testRepositoryMetaModel() {
		InJavaImporter importer = new InJavaImporter();
		assertNotNull(importer.repository().getMetamodel().getDescription(Namespace.class));
	}

	@Test
	public void testJavaLanguageInRepository() {
		InJavaImporter importer = new InJavaImporter();
		assertEquals(1, importer.repository().size());
		assertEquals(JavaSourceLanguage.class, importer.repository().getElements().stream().findAny().get().getClass());
	}

}
