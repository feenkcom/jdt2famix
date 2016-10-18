package com.feenk.jdt2famix.injava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
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

		// Manage options
		// https://commons.apache.org/proper/commons-cli/usage.html
		// create Options object
		Options options = new Options();

		// autocp: add in the parsing CP all the .jar of the folder in arg
		options.addOption("autocp", true, "Add in the parsing CP all the .jar of the folder in arg ");
		options.addOption("filecp", true,
				"Add in the parsing CP all the absolute paths contained in the file in arg (one by line)");

		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine cmd = parser.parse(options, args);
			if (cmd.hasOption("autocp")) {
				classpath.addAll(collectAllJars(cmd.getOptionValue("autocp")));
			}
			if (cmd.hasOption("filecp")) {
				classpath.addAll(readAllJars(cmd.getOptionValue("filecp")));
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Run the parsing

		logger.trace("importing root folder - " + path.toString());
		importer.run(javaFiles, classpath);
		logger.trace("exporting - " + mseFileName);
		importer.exportMSE(mseFileName);
		logger.trace("done");
	}

	/**
	 * Reads all .jars in classpath from a file, one per line
	 * 
	 * @param the
	 *            filename of the file containing the jars of tyhe classpath
	 * @return the collection of jar paths
	 */
	static private List<String> readAllJars(String filename) {
		List<String> jarPaths = new ArrayList<String>();
		try {
			BufferedReader fileBuffer = new BufferedReader(new FileReader(filename));
			String jarName = fileBuffer.readLine();
			while (jarName != null) {
				jarPaths.add(jarName);
				jarName = fileBuffer.readLine();
			}
			fileBuffer.close();
		} catch (FileNotFoundException e) {
			System.err.println("** Error classpath file " + filename + " not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("** Error reading classpath file: " + filename);
			e.printStackTrace();
		}
		return jarPaths;
	}


	public static List<String> collectAllJars(String sDir) {
		File[] listOfFiles = new File(sDir).listFiles();
		List<String> jarPaths = new ArrayList<String>();
		for (File file : listOfFiles) {
			if (file.getName().endsWith("jar")) {
				jarPaths.add(file.getAbsolutePath());
			}
			if (file.isDirectory()) {
				jarPaths.addAll(collectAllJars(file.getAbsolutePath()));
			}
		}
		return jarPaths;
	}

	
}
