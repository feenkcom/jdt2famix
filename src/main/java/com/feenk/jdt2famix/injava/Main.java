package com.feenk.jdt2famix.injava;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.feenk.jdt2famix.Classpath;
import com.feenk.jdt2famix.JavaFiles;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
	
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
		logger.trace("importing root folder - " + path.toString());
		importer.run(javaFiles, classpath);
		logger.trace("exporting - " + mseFileName);
		importer.exportMSE(mseFileName);
		logger.trace("done");
	}

}
