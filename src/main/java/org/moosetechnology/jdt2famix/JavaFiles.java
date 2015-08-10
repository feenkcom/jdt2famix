package org.moosetechnology.jdt2famix;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class JavaFiles {
	private Collection<String> javaFilePaths;

	public JavaFiles() {
		javaFilePaths = new ArrayList<String>();
	}
	
	public void deepJavaFiles(String rootPath) {
		javaFilePaths = deepJavaFilesIn(new File(rootPath));
	}
	
	public void oneJavaFile(String filePath) {
		javaFilePaths.add(filePath);
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
	
	public String[] paths() {
		return javaFilePaths.toArray(new String[0]);
	}

}
