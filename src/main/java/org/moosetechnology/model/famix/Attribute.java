// Automagically generated code, please do not change
package org.moosetechnology.model.famix;

import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("Attribute")
public class Attribute extends StructuralEntity {



    @FameProperty(name = "numberOfAccesses", derived = true)
    public Number getNumberOfAccesses() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "numberOfLocalAccesses", derived = true)
    public Number getNumberOfLocalAccesses() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "hierarchyNestingLevel", derived = true)
    public Number getHierarchyNestingLevel() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "numberOfAccessingMethods", derived = true)
    public Number getNumberOfAccessingMethods() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "numberOfAccessingClasses", derived = true)
    public Number getNumberOfAccessingClasses() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "numberOfGlobalAccesses", derived = true)
    public Number getNumberOfGlobalAccesses() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    private Boolean hasClassScope;
    
    @FameProperty(name = "hasClassScope")
    public Boolean getHasClassScope() {
        return hasClassScope;
    }

    public void setHasClassScope(Boolean hasClassScope) {
        this.hasClassScope = hasClassScope;
    }
    
    private Type parentType;
    
    @FameProperty(name = "parentType", opposite = "attributes")
    public Type getParentType() {
        return parentType;
    }

    public void setParentType(Type parentType) {
        if (this.parentType != null) {
            if (this.parentType.equals(parentType)) return;
            this.parentType.getAttributes().remove(this);
        }
        this.parentType = parentType;
        if (parentType == null) return;
        parentType.getAttributes().add(this);
    }
    


}

