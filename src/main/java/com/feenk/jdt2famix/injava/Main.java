package com.feenk.jdt2famix.injava;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.feenk.jdt2famix.Classpath;
import com.feenk.jdt2famix.JavaFiles;

public class Main {

	public static void main(String[] args) {
		InJavaImporter importer = new InJavaImporter();
		String pathName;
		if (args.length > 0)
			pathName = args[0];
		else 
			pathName = ".";
		Path path = Paths.get(pathName).toAbsolutePath().normalize();
		String mseFileName = path.getName(path.getNameCount() - 1) + ".mse";
		JavaFiles javaFiles = new JavaFiles();
		javaFiles.deepJavaFiles(path.toString());
		Classpath classpath = new Classpath();
		classpath.deepJarFiles(path.toString());
		System.out.println("jdt2famix - parsing started - " + path.toString());
		importer.run(javaFiles, classpath);
		System.out.println("jdt2famix - parsing ended");
		importer.exportMSE(mseFileName);
		System.out.println("jdt2famix - model exported - " + mseFileName);
	}

}
