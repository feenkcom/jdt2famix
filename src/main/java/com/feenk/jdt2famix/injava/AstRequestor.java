package com.feenk.jdt2famix.injava;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;

import com.feenk.jdt2famix.JavaFiles;

public class AstRequestor extends FileASTRequestor {
	
	protected InJavaImporter importer;
	private static Logger logger = LogManager.getLogger(AstRequestor.class);
	
	protected int currentFileIndex = 0; 
	protected int allJavaFileCount;
	
	public AstRequestor(InJavaImporter importer, JavaFiles allJavaFiles) {
		this.importer = importer;
		allJavaFileCount = allJavaFiles.size();
	}

	@Override
	public void acceptAST(String sourceFilePath, CompilationUnit ast) {
		logger.trace("importing file - "
				+ String.format("%0"+ Integer.toString(allJavaFileCount).length() + "d", ++currentFileIndex)
				+ "/" + allJavaFileCount + " - "
				+ sourceFilePath);
		importer.setCurrentFilePath(sourceFilePath);
		ast.accept(new AstVisitor(importer));
		importer.setCurrentFilePath(null);
	}

}
