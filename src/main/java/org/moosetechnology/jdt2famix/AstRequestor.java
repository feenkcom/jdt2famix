package org.moosetechnology.jdt2famix;

import jniport.RequestNotHandedException;
import jniport.SmalltalkRequest;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;

public class AstRequestor extends FileASTRequestor {
	
	public static String acceptASTCallback = AstRequestor.class.getName() + "acceptAST()";

	public AstRequestor() {
	}

	@Override
	public void acceptAST(String sourceFilePath, CompilationUnit ast) {
		try {
			new SmalltalkRequest(acceptASTCallback, this, sourceFilePath).value();
		} catch (RequestNotHandedException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		ast.accept(new AstVisitor());
	}

}
