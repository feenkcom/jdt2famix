// Automagically generated code, please do not change
package org.moosetechnology.model.famix;

import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("DeclaredException")
public class DeclaredException extends Exception {



    private Method definingMethod;
    
    @FameProperty(name = "definingMethod", opposite = "declaredExceptions")
    public Method getDefiningMethod() {
        return definingMethod;
    }

    public void setDefiningMethod(Method definingMethod) {
        if (this.definingMethod != null) {
            if (this.definingMethod.equals(definingMethod)) return;
            this.definingMethod.getDeclaredExceptions().remove(this);
        }
        this.definingMethod = definingMethod;
        if (definingMethod == null) return;
        definingMethod.getDeclaredExceptions().add(this);
    }
    


}

