// Automagically generated code, please do not change
package org.moosetechnology.model.file;

import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import ch.akuhn.fame.FamePackage;
import org.moosetechnology.model.famix.Entity;


@FamePackage("FILE")
@FameDescription("AbstractFile")
public class AbstractFile extends Entity {



    private String name;
    
    @FameProperty(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    


}

