package com.feenk.jdt2famix.modelgenerator;

import static ch.akuhn.util.Out.puts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import ch.akuhn.fame.MetaRepository;
import ch.akuhn.fame.Repository;
import ch.akuhn.fame.codegen.CodeGeneration;
import ch.akuhn.fame.parser.Importer;
import ch.akuhn.fame.parser.InputSource;

/**
 * This is a utility class that generates the code of FAMIX out of a specification serialized in an MSE
 * that is typically obtained from the Pharo implementation
 */
public class FamixGenerator {
	public static void main(String... args) {
		File mseFile = new File("src/main/java/com/feenk/jdt2famix/modelgenerator/famix30.fm3.mse");
	    InputStream in;
		try {
			in = new FileInputStream(mseFile);
			InputSource input = InputSource.fromInputStream(in);
			MetaRepository fm3 = MetaRepository.createFM3();
			Importer builder = new Importer(fm3);
			builder.readFrom(input);
			Repository famix = builder.getResult();
			CodeGeneration gen = new CodeGeneration("com.feenk.jdt2famix.model", "gen", "");
			gen.accept(famix);
			puts("done");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
