This project offers the mechanism for producing MSE files out of Java code. It is based on [JDT Core](https://projects.eclipse.org/projects/eclipse.jdt.core) and [Fame for Java](https://github.com/girba/FameJava), and it requires Java 8.

#Overview

<img src="doc/jdt2famix-standalone.png"/>

#Installation
1. Clone or download this repository
2. Make sure you have [Maven](https://maven.apache.org/download.cgi) installed and that `mvn` is in the execution path (i.e., change `$PATH`) 
3. Execute in the root folder: `release.sh`
4. This created a `./release` folder that includes all jar files and a `jdt2famix.sh` script

#How to create an MSE model from a target system
Suppose you have a `mysystem` root folder in which you have the sources and all jar dependencies of your target Java system.

After having created the release, go to `mysystem`, and execute `/path/to/jdt2famix.sh`. This will result in an MSE file named like the folder of your target system (in our case `mysystem.mse`).

You can now import the `mysystem.mse` file into [Moose](http://moosetechnology.org). 

##A note about Maven project

If you happen to have a Java project that defines dependencies through Maven, you can use the following command to copy all dependencies:

	``mvn dependency:copy-dependencies -DoutputDirectory=dependencies -DoverWriteSnapshots=true -DoverWriteReleases=false``

#License
* The main code of the jdt2famix is released under [Eclipse Public License - v 1.0](http://wiki.eclipse.org/EPL).
* JDT Core is used as an external binary library available under [Eclipse Public License - v 1.0](http://wiki.eclipse.org/EPL).
* Fame is used as an external binary library available under [GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007](https://www.gnu.org/licenses/lgpl-3.0.en.html). 
