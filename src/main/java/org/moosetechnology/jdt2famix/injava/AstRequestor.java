package org.moosetechnology.jdt2famix.injava;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;

public class AstRequestor extends FileASTRequestor {
	
	private InJavaImporter importer;

	public AstRequestor(InJavaImporter importer) {
		this.importer = importer;
	}

	@Override
	public void acceptAST(String sourceFilePath, CompilationUnit ast) {
		System.out.println("jdt2famix - importing - " + sourceFilePath);
		ast.accept(new AstVisitor(importer));
	}

}
