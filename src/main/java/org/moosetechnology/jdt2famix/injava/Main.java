package org.moosetechnology.jdt2famix.injava;

import java.io.File;

import org.moosetechnology.jdt2famix.Classpath;
import org.moosetechnology.jdt2famix.JavaFiles;

public class Main {

	public static void main(String[] args) {
		InJavaImporter importer = new InJavaImporter();
		String javaFilesRoot = new File (".").getAbsolutePath();
		String classpathRoot = new File (".").getAbsolutePath();
		String mseFileName = (new File (".")).getName() + ".mse";
		if (args.length > 0) {
			javaFilesRoot = args[0];
			classpathRoot = args[0];
		}
		JavaFiles javaFiles = new JavaFiles();
		javaFiles.deepJavaFiles(javaFilesRoot);
		Classpath classpath = new Classpath();
		classpath.deepJarFiles(classpathRoot);
		System.out.println("jdt2famix - parsing started in " + javaFilesRoot);
		importer.run(javaFiles, classpath);
		System.out.println("jdt2famix - parsing ended");
		importer.exportMSE(mseFileName);
		System.out.println("jdt2famix - model exported");
	}

}
