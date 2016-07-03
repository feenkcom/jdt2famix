// Automagically generated code, please do not change
package org.moosetechnology.model.file;

import ch.akuhn.fame.MetaRepository;

public class FILEModel {

    public static MetaRepository metamodel() {
        MetaRepository metamodel = new MetaRepository();
        importInto(metamodel);
        return metamodel;
    }
    
    public static void importInto(MetaRepository metamodel) {
		metamodel.with(org.moosetechnology.model.file.Folder.class);
		metamodel.with(org.moosetechnology.model.file.File.class);
		metamodel.with(org.moosetechnology.model.file.AbstractFile.class);

    }

}

