// Automagically generated code, please do not change
package org.moosetechnology.model.famix;

import ch.akuhn.fame.internal.MultivalueSet;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import java.util.*;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("Package")
public class Package extends ScopingEntity {



    private Number numberOfClientPackages;
    
    @FameProperty(name = "numberOfClientPackages")
    public Number getNumberOfClientPackages() {
        return numberOfClientPackages;
    }

    public void setNumberOfClientPackages(Number numberOfClientPackages) {
        this.numberOfClientPackages = numberOfClientPackages;
    }
    
    private Collection<NamedEntity> childNamedEntities; 

    @FameProperty(name = "childNamedEntities", opposite = "parentPackage", derived = true)
    public Collection<NamedEntity> getChildNamedEntities() {
        if (childNamedEntities == null) {
            childNamedEntities = new MultivalueSet<NamedEntity>() {
                @Override
                protected void clearOpposite(NamedEntity e) {
                    e.setParentPackage(null);
                }
                @Override
                protected void setOpposite(NamedEntity e) {
                    e.setParentPackage(Package.this);
                }
            };
        }
        return childNamedEntities;
    }
    
    public void setChildNamedEntities(Collection<? extends NamedEntity> childNamedEntities) {
        this.getChildNamedEntities().clear();
        this.getChildNamedEntities().addAll(childNamedEntities);
    }                    
    
        
    public void addChildNamedEntities(NamedEntity one) {
        this.getChildNamedEntities().add(one);
    }   
    
    public void addChildNamedEntities(NamedEntity one, NamedEntity... many) {
        this.getChildNamedEntities().add(one);
        for (NamedEntity each : many)
            this.getChildNamedEntities().add(each);
    }   
    
    public void addChildNamedEntities(Iterable<? extends NamedEntity> many) {
        for (NamedEntity each : many)
            this.getChildNamedEntities().add(each);
    }   
                
    public void addChildNamedEntities(NamedEntity[] many) {
        for (NamedEntity each : many)
            this.getChildNamedEntities().add(each);
    }
    
    public int numberOfChildNamedEntities() {
        return getChildNamedEntities().size();
    }

    public boolean hasChildNamedEntities() {
        return !getChildNamedEntities().isEmpty();
    }
    
                
    private Number numberOfMethods;
    
    @FameProperty(name = "numberOfMethods")
    public Number getNumberOfMethods() {
        return numberOfMethods;
    }

    public void setNumberOfMethods(Number numberOfMethods) {
        this.numberOfMethods = numberOfMethods;
    }
    


}

