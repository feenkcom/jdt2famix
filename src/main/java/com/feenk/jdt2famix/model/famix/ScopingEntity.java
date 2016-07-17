// Automagically generated code, please do not change
package com.feenk.jdt2famix.model.famix;

import ch.akuhn.fame.internal.MultivalueSet;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import java.util.*;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("ScopingEntity")
public class ScopingEntity extends ContainerEntity {



    private Collection<GlobalVariable> globalVariables; 

    @FameProperty(name = "globalVariables", opposite = "parentScope", derived = true)
    public Collection<GlobalVariable> getGlobalVariables() {
        if (globalVariables == null) {
            globalVariables = new MultivalueSet<GlobalVariable>() {
                @Override
                protected void clearOpposite(GlobalVariable e) {
                    e.setParentScope(null);
                }
                @Override
                protected void setOpposite(GlobalVariable e) {
                    e.setParentScope(ScopingEntity.this);
                }
            };
        }
        return globalVariables;
    }
    
    public void setGlobalVariables(Collection<? extends GlobalVariable> globalVariables) {
        this.getGlobalVariables().clear();
        this.getGlobalVariables().addAll(globalVariables);
    }                    
    
        
    public void addGlobalVariables(GlobalVariable one) {
        this.getGlobalVariables().add(one);
    }   
    
    public void addGlobalVariables(GlobalVariable one, GlobalVariable... many) {
        this.getGlobalVariables().add(one);
        for (GlobalVariable each : many)
            this.getGlobalVariables().add(each);
    }   
    
    public void addGlobalVariables(Iterable<? extends GlobalVariable> many) {
        for (GlobalVariable each : many)
            this.getGlobalVariables().add(each);
    }   
                
    public void addGlobalVariables(GlobalVariable[] many) {
        for (GlobalVariable each : many)
            this.getGlobalVariables().add(each);
    }
    
    public int numberOfGlobalVariables() {
        return getGlobalVariables().size();
    }

    public boolean hasGlobalVariables() {
        return !getGlobalVariables().isEmpty();
    }
    
                
    private ScopingEntity parentScope;
    
    @FameProperty(name = "parentScope", opposite = "childScopes")
    public ScopingEntity getParentScope() {
        return parentScope;
    }

    public void setParentScope(ScopingEntity parentScope) {
        if (this.parentScope != null) {
            if (this.parentScope.equals(parentScope)) return;
            this.parentScope.getChildScopes().remove(this);
        }
        this.parentScope = parentScope;
        if (parentScope == null) return;
        parentScope.getChildScopes().add(this);
    }
    
    private Collection<ScopingEntity> childScopes; 

    @FameProperty(name = "childScopes", opposite = "parentScope", derived = true)
    public Collection<ScopingEntity> getChildScopes() {
        if (childScopes == null) {
            childScopes = new MultivalueSet<ScopingEntity>() {
                @Override
                protected void clearOpposite(ScopingEntity e) {
                    e.setParentScope(null);
                }
                @Override
                protected void setOpposite(ScopingEntity e) {
                    e.setParentScope(ScopingEntity.this);
                }
            };
        }
        return childScopes;
    }
    
    public void setChildScopes(Collection<? extends ScopingEntity> childScopes) {
        this.getChildScopes().clear();
        this.getChildScopes().addAll(childScopes);
    }                    
    
        
    public void addChildScopes(ScopingEntity one) {
        this.getChildScopes().add(one);
    }   
    
    public void addChildScopes(ScopingEntity one, ScopingEntity... many) {
        this.getChildScopes().add(one);
        for (ScopingEntity each : many)
            this.getChildScopes().add(each);
    }   
    
    public void addChildScopes(Iterable<? extends ScopingEntity> many) {
        for (ScopingEntity each : many)
            this.getChildScopes().add(each);
    }   
                
    public void addChildScopes(ScopingEntity[] many) {
        for (ScopingEntity each : many)
            this.getChildScopes().add(each);
    }
    
    public int numberOfChildScopes() {
        return getChildScopes().size();
    }

    public boolean hasChildScopes() {
        return !getChildScopes().isEmpty();
    }
    
                


}

