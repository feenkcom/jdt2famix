package org.moosetechnology.jdt2famix.injava;

public class Main {

	public static void main(String[] args) {
		InJavaImporter importer = new InJavaImporter();
		//TODO read args and trigger the importer
		importer.runOne("src/test/java/org/moosetechnology/jdt2famix/samples/basic/SimpleInterface.java");
		importer.exportMSE("model.mse");
	}

}
