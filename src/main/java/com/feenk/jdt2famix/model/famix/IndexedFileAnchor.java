// Automagically generated code, please do not change
package com.feenk.jdt2famix.model.famix;

import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("IndexedFileAnchor")
public class IndexedFileAnchor extends AbstractFileAnchor {



    private Number startPos;
    
    @FameProperty(name = "startPos")
    public Number getStartPos() {
        return startPos;
    }

    public void setStartPos(Number startPos) {
        this.startPos = startPos;
    }
    
    private Number endPos;
    
    @FameProperty(name = "endPos")
    public Number getEndPos() {
        return endPos;
    }

    public void setEndPos(Number endPos) {
        this.endPos = endPos;
    }
    


}

