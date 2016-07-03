// Automagically generated code, please do not change
package org.moosetechnology.model.famix;

import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("GlobalVariable")
public class GlobalVariable extends StructuralEntity {



    private ScopingEntity parentScope;
    
    @FameProperty(name = "parentScope", opposite = "globalVariables")
    public ScopingEntity getParentScope() {
        return parentScope;
    }

    public void setParentScope(ScopingEntity parentScope) {
        if (this.parentScope != null) {
            if (this.parentScope.equals(parentScope)) return;
            this.parentScope.getGlobalVariables().remove(this);
        }
        this.parentScope = parentScope;
        if (parentScope == null) return;
        parentScope.getGlobalVariables().add(this);
    }
    
    private Module parentModule;
    
    @FameProperty(name = "parentModule")
    public Module getParentModule() {
        return parentModule;
    }

    public void setParentModule(Module parentModule) {
        this.parentModule = parentModule;
    }
    


}

