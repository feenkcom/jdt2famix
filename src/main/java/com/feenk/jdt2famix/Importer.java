package com.feenk.jdt2famix;

import java.util.Arrays;
import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.FileASTRequestor;

public abstract class Importer {	
	
	/**
	 * Typically holds the prefix of the path of the root folder in which the importer was triggered.
	 * It is useful for creating relative paths for the source anchors  
	 */
	protected String ignoredRootPath;
	public String pathWithoutIgnoredRootPath(String originalPath) { 
		return originalPath.replaceAll("\\\\", "/").replaceFirst("^"+ignoredRootPath+"/", "");
	}

	/**
	 * Primary method to trigger the importer after having defined the 
	 * (1) {@link JavaFiles} with files to be parsed, and 
	 * (2) {@link Classpath} with dependencies
	 */
	public void run(JavaFiles javaFiles, Classpath classpath) {
		ignoredRootPath = javaFiles.ignoredRootPath().replaceAll("\\\\", "/");
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setResolveBindings(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		@SuppressWarnings("unchecked")
		Map<String, String> options = JavaCore.getOptions();
		options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8);
		options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8);
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
		parser.setCompilerOptions(options);
		
		parser.setEnvironment(classpath.paths(), new String[]{}, new String[]{}, true);

		String[] paths = javaFiles.paths();
		String[] encodings = Arrays.stream(paths).map(path -> "UTF-8").toArray(String[]::new);
		
		parser.createASTs(paths, encodings, new String[0], getRequestor(javaFiles), null);
	}

	public void run(JavaFiles javaFiles) {
		this.run(javaFiles, new Classpath());
	}

	public void runOne(String oneFilePath) {
		JavaFiles javaFiles = new JavaFiles();
		javaFiles.oneJavaFile(oneFilePath);
		this.run(javaFiles, new Classpath());
	}

	protected abstract FileASTRequestor getRequestor(JavaFiles allJavaFiles);

}
