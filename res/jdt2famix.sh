#!/bin/sh

#This is the main script with which to trigger jdt2famix from the command line.
#How to trigger
#- go to the path where the system is (the path should include both the sources and the jars)
#- execute /path/to/jdt2famix.sh
#- the result is an MSE file with the name of the folder from which the script was executed

BASE_PATH="$(cd "$(dirname "$0")" && pwd)"
java -cp $(echo ./*.jar | tr ' ' ':') com.feenk.jdt2famix.injava.Main
