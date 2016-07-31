package com.feenk.jdt2famix.injava;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;

public class AstRequestor extends FileASTRequestor {
	
	private InJavaImporter importer;
	private static Logger logger = LogManager.getLogger(AstRequestor.class);

	public AstRequestor(InJavaImporter importer) {
		this.importer = importer;
	}

	@Override
	public void acceptAST(String sourceFilePath, CompilationUnit ast) {
		logger.trace("importing - " + sourceFilePath);
		ast.accept(new AstVisitor(importer, sourceFilePath));
		logger.trace("done importing - " + sourceFilePath);
	}

}
