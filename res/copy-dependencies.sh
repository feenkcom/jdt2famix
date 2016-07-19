#!/bin/sh

#This utility copies all jar dependencies to the target folder
#This is useful for making it easy for the Pharo image to collect all jars in the classpath when connecting to the JVM
mvn package
mvn org.apache.maven.plugins:maven-dependency-plugin:2.8:copy-dependencies -DoutputDirectory=./target