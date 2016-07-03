// Automagically generated code, please do not change
package org.moosetechnology.model.famix;

import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("Include")
public class Include extends Association {



    private Object source;
    
    @FameProperty(name = "source")
    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
    
    private Object target;
    
    @FameProperty(name = "target")
    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
    


}

