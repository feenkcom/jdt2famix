// Automagically generated code, please do not change
package org.moosetechnology.model.famix;

import ch.akuhn.fame.internal.MultivalueSet;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import java.util.*;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("StructuralEntity")
public class StructuralEntity extends LeafEntity {



    @FameProperty(name = "accessors", derived = true)
    public Collection<BehaviouralEntity> getAccessors() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
        
    private Collection<Access> incomingAccesses; 

    @FameProperty(name = "incomingAccesses", opposite = "variable", derived = true)
    public Collection<Access> getIncomingAccesses() {
        if (incomingAccesses == null) {
            incomingAccesses = new MultivalueSet<Access>() {
                @Override
                protected void clearOpposite(Access e) {
                    e.setVariable(null);
                }
                @Override
                protected void setOpposite(Access e) {
                    e.setVariable(StructuralEntity.this);
                }
            };
        }
        return incomingAccesses;
    }
    
    public void setIncomingAccesses(Collection<? extends Access> incomingAccesses) {
        this.getIncomingAccesses().clear();
        this.getIncomingAccesses().addAll(incomingAccesses);
    }                    
    
        
    public void addIncomingAccesses(Access one) {
        this.getIncomingAccesses().add(one);
    }   
    
    public void addIncomingAccesses(Access one, Access... many) {
        this.getIncomingAccesses().add(one);
        for (Access each : many)
            this.getIncomingAccesses().add(each);
    }   
    
    public void addIncomingAccesses(Iterable<? extends Access> many) {
        for (Access each : many)
            this.getIncomingAccesses().add(each);
    }   
                
    public void addIncomingAccesses(Access[] many) {
        for (Access each : many)
            this.getIncomingAccesses().add(each);
    }
    
    public int numberOfIncomingAccesses() {
        return getIncomingAccesses().size();
    }

    public boolean hasIncomingAccesses() {
        return !getIncomingAccesses().isEmpty();
    }
    
                
    private Type declaredType;
    
    @FameProperty(name = "declaredType", opposite = "structuresWithDeclaredType")
    public Type getDeclaredType() {
        return declaredType;
    }

    public void setDeclaredType(Type declaredType) {
        if (this.declaredType != null) {
            if (this.declaredType.equals(declaredType)) return;
            this.declaredType.getStructuresWithDeclaredType().remove(this);
        }
        this.declaredType = declaredType;
        if (declaredType == null) return;
        declaredType.getStructuresWithDeclaredType().add(this);
    }
    
    private Collection<DereferencedInvocation> dereferencedInvocations; 

    @FameProperty(name = "dereferencedInvocations", opposite = "referencer", derived = true)
    public Collection<DereferencedInvocation> getDereferencedInvocations() {
        if (dereferencedInvocations == null) {
            dereferencedInvocations = new MultivalueSet<DereferencedInvocation>() {
                @Override
                protected void clearOpposite(DereferencedInvocation e) {
                    e.setReferencer(null);
                }
                @Override
                protected void setOpposite(DereferencedInvocation e) {
                    e.setReferencer(StructuralEntity.this);
                }
            };
        }
        return dereferencedInvocations;
    }
    
    public void setDereferencedInvocations(Collection<? extends DereferencedInvocation> dereferencedInvocations) {
        this.getDereferencedInvocations().clear();
        this.getDereferencedInvocations().addAll(dereferencedInvocations);
    }                    
    
        
    public void addDereferencedInvocations(DereferencedInvocation one) {
        this.getDereferencedInvocations().add(one);
    }   
    
    public void addDereferencedInvocations(DereferencedInvocation one, DereferencedInvocation... many) {
        this.getDereferencedInvocations().add(one);
        for (DereferencedInvocation each : many)
            this.getDereferencedInvocations().add(each);
    }   
    
    public void addDereferencedInvocations(Iterable<? extends DereferencedInvocation> many) {
        for (DereferencedInvocation each : many)
            this.getDereferencedInvocations().add(each);
    }   
                
    public void addDereferencedInvocations(DereferencedInvocation[] many) {
        for (DereferencedInvocation each : many)
            this.getDereferencedInvocations().add(each);
    }
    
    public int numberOfDereferencedInvocations() {
        return getDereferencedInvocations().size();
    }

    public boolean hasDereferencedInvocations() {
        return !getDereferencedInvocations().isEmpty();
    }
    
                


}

