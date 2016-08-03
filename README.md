This project offers the mechanism for producing MSE files out of Java code. It is based on [JDT Core](https://projects.eclipse.org/projects/eclipse.jdt.core) and [Fame for Java](https://github.com/girba/FameJava), and it requires Java 8.

#Overview

<img src="doc/jdt2famix-standalone.png"/>

#Installation

##Run a release
1. [Download a binary release](https://github.com/girba/jdt2famix/releases)
2. The downloaded folder contains all jar files and a `jdt2famix.sh` script

##Create a binary release from sources
1. Clone or download this repository
2. Make sure you have [Maven](https://maven.apache.org/download.cgi) installed and that `mvn` is in the execution path (i.e., change `$PATH`) 
3. Execute in the root folder: `release.sh`
4. This created a `./release` folder that includes all jar files and a `jdt2famix.sh` script

#How to create an MSE model from a target system
Suppose you have a `mysystem` root folder in which you have the sources and all jar dependencies of your target Java system.

After having created the release, go to `mysystem`, and execute `/path/to/jdt2famix.sh`. This will result in an MSE file named like the folder of your target system (in our case `mysystem.mse`).

You can now import the `mysystem.mse` file into [Moose](http://moosetechnology.org). 

##The importance of including external dependencies

It is important to have all external dependencies in a subfolder of `mysystem`. This ensures that the importer can correctly identify dependencies to outside entities. Consider the following example of a class definition 

	@ExternalAnnotation
	public class MyClass extends ExternalClass { ... }

In order to describe completely `MyClass`, we need to extract information about all its immediate dependencies. For example, in our case we need to have information about `ExternalClass` and `@ExternalAnnotation`. That is why, we need to have the external libraries available in the Classpath of the importer. As the importer automatically retrieves all jars/ears/wars from the root folder of `mysystem`, it is enough to place these dependencies somewhere under that folder.  


##A note about Maven projects

If you happen to have a Java project that defines dependencies through Maven, you can use the following command to copy all dependencies:

	mvn dependency:copy-dependencies -DoutputDirectory=dependencies -DoverWriteSnapshots=true -DoverWriteReleases=false

#License
* The main code of the jdt2famix is released under [Eclipse Public License - v 1.0](http://wiki.eclipse.org/EPL).
* JDT Core is used as an external binary library available under [Eclipse Public License - v 1.0](http://wiki.eclipse.org/EPL).
* Fame is used as an external binary library available under [GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007](https://www.gnu.org/licenses/lgpl-3.0.en.html). 
