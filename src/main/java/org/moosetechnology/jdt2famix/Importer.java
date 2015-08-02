package org.moosetechnology.jdt2famix;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

public class Importer {	
	public static void main(String[] args) {
		new Importer().run("/Users/girba/Work/Code/moose/src/java8-tutorial");
	}
	
	public void run(String rootPath) {
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setResolveBindings(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setEnvironment(new String[] {"."}, new String[]{}, new String[]{}, true);
		String[] javaFiles = deepJavaFilesIn(new File(rootPath)).toArray(new String[0]);
		AstRequestor astRequestor = new AstRequestor(); 
		parser.createASTs(javaFiles, null, new String[0], astRequestor, null);
	}
	
	private Collection<String> deepJavaFilesIn(File root) {
		Collection<String> all = new ArrayList<String>();
		for (File child : root.listFiles()) {
			if (child.isDirectory()) {
				all.addAll(deepJavaFilesIn(child));
			}
			else {
				if (child.getName().endsWith(".java"))
					all.add(child.getAbsolutePath());
			} 
		}
		return all;
	}
}
