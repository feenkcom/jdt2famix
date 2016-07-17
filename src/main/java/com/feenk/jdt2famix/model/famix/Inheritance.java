// Automagically generated code, please do not change
package com.feenk.jdt2famix.model.famix;

import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("Inheritance")
public class Inheritance extends Association {



    private Type superclass;
    
    @FameProperty(name = "superclass", opposite = "subInheritances")
    public Type getSuperclass() {
        return superclass;
    }

    public void setSuperclass(Type superclass) {
        if (this.superclass != null) {
            if (this.superclass.equals(superclass)) return;
            this.superclass.getSubInheritances().remove(this);
        }
        this.superclass = superclass;
        if (superclass == null) return;
        superclass.getSubInheritances().add(this);
    }
    
    private Type subclass;
    
    @FameProperty(name = "subclass", opposite = "superInheritances")
    public Type getSubclass() {
        return subclass;
    }

    public void setSubclass(Type subclass) {
        if (this.subclass != null) {
            if (this.subclass.equals(subclass)) return;
            this.subclass.getSuperInheritances().remove(this);
        }
        this.subclass = subclass;
        if (subclass == null) return;
        subclass.getSuperInheritances().add(this);
    }
    


}

