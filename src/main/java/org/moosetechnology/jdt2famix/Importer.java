package org.moosetechnology.jdt2famix;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

public class Importer {	

	public Importer() {
	}
	
	/**
	 * Primary method to trigger the importer after having set the 
	 * (1) {@link JavaFiles} with files to be parsed, and 
	 * (2) {@link Classpath} with dependencies
	 */
	public void run(JavaFiles javaFiles, Classpath classpath) {
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setResolveBindings(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setEnvironment(classpath.paths(), new String[]{}, new String[]{}, true);
		AstRequestor astRequestor = new AstRequestor(); 
		parser.createASTs(javaFiles.paths(), null, new String[0], astRequestor, null);
	}

	public void runOne(String oneFilePath) {
		JavaFiles javaFiles = new JavaFiles();
		javaFiles.oneJavaFile(oneFilePath);
		this.run(javaFiles, new Classpath());
	}
	
}
