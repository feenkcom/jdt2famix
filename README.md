This project is meant as a simple binding for the Pharo implementation.

It consists of only a few classes, the most important being the org.moosetechnology.jdt2famix.AstVisitor one. For each important visiting method calls the visitor raises an corresponding SmalltalkNotification that ultimately is being handled in the Pharo implementation.