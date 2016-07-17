// Automagically generated code, please do not change
package com.feenk.jdt2famix.model.famix;

import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("DereferencedInvocation")
public class DereferencedInvocation extends Invocation {



    private StructuralEntity referencer;
    
    @FameProperty(name = "referencer", opposite = "dereferencedInvocations")
    public StructuralEntity getReferencer() {
        return referencer;
    }

    public void setReferencer(StructuralEntity referencer) {
        if (this.referencer != null) {
            if (this.referencer.equals(referencer)) return;
            this.referencer.getDereferencedInvocations().remove(this);
        }
        this.referencer = referencer;
        if (referencer == null) return;
        referencer.getDereferencedInvocations().add(this);
    }
    


}

