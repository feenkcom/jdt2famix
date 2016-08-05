// Automagically generated code, please do not change
package com.feenk.jdt2famix.model.file;

import ch.akuhn.fame.MetaRepository;

public class FILEModel {

    public static MetaRepository metamodel() {
        MetaRepository metamodel = new MetaRepository();
        importInto(metamodel);
        return metamodel;
    }
    
    public static void importInto(MetaRepository metamodel) {
		metamodel.with(com.feenk.jdt2famix.model.file.File.class);
		metamodel.with(com.feenk.jdt2famix.model.file.Folder.class);
		metamodel.with(com.feenk.jdt2famix.model.file.AbstractFile.class);

    }

}

