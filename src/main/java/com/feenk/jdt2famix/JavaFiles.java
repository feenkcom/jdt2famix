package com.feenk.jdt2famix;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This is a utility builder class that is used for defining which Java files should be used as input for the {@link Importer}  
 */
public class JavaFiles {
	private Collection<String> javaFilePaths;
	private String ignoredRootPath;

	public JavaFiles() {
		javaFilePaths = new ArrayList<String>();
		ignoredRootPath = "";
	}
	
	public void deepJavaFiles(String rootPath) {
		ignoredRootPath = rootPath;
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
	
	/**
	 * The collection of file paths  
	 */
	public String[] paths() {
		return javaFilePaths.toArray(new String[0]);
	}
	
	/**
	 * These is the path that is ignored when creating the paths of source anchors.
	 * When we get the files recursively from a folder, we store the root folder path,
	 * and from that we can recover the relative path.
	 * An alternative design would be to store the root folder altogether,
	 * but the current thing looked easier for now even if it is not the most elegant.
	 */
	public String ignoredRootPath() {
		return ignoredRootPath;
	}
	
	public int size() {
		return javaFilePaths.size();
	}

}
