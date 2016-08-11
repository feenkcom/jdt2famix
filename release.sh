#!/bin/sh

#This script produces the release package with all jars and the relevant script in the release folder.
#It should be run in the root folder of the jdt2famix project.

#1. create release folder
rm -rf ./release/
mkdir release

#2. produce the jdt2famix jar
mvn package
cp ./target/jdt2famix*.jar ./release/
cp ./src/main/java/log4j2.xml ./release/

#3. copy all jar dependencies
mvn dependency:copy-dependencies -DoutputDirectory=release -DoverWriteSnapshots=true -DoverWriteReleases=false

#4. copy the release script
cp ./res/jdt2famix.sh ./release/
chmod a+x ./release/jdt2famix.sh
cp ./res/jdt2famix.cmd ./release/
