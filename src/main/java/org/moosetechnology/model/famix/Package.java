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



    @FameProperty(name = "efferentCoupling", derived = true)
    public Number getEfferentCoupling() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    private Number numberOfClientPackages;
    
    @FameProperty(name = "numberOfClientPackages")
    public Number getNumberOfClientPackages() {
        return numberOfClientPackages;
    }

    public void setNumberOfClientPackages(Number numberOfClientPackages) {
        this.numberOfClientPackages = numberOfClientPackages;
    }
    
    @FameProperty(name = "relativeImportanceForSystem", derived = true)
    public Number getRelativeImportanceForSystem() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "clientTypes", derived = true)
    public Collection<Type> getClientTypes() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
        
    @FameProperty(name = "weightedMethodCount", derived = true)
    public Number getWeightedMethodCount() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "distance", derived = true)
    public Number getDistance() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "providerTypes", derived = true)
    public Collection<Type> getProviderTypes() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
        
    @FameProperty(name = "numberOfClasses", derived = true)
    public Number getNumberOfClasses() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
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
    
                
    @FameProperty(name = "martinCohesion", derived = true)
    public Number getMartinCohesion() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "bunchCohesion", derived = true)
    public Number getBunchCohesion() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    private Number numberOfMethods;
    
    @FameProperty(name = "numberOfMethods")
    public Number getNumberOfMethods() {
        return numberOfMethods;
    }

    public void setNumberOfMethods(Number numberOfMethods) {
        this.numberOfMethods = numberOfMethods;
    }
    
    @FameProperty(name = "afferentCoupling", derived = true)
    public Number getAfferentCoupling() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "numberOfLinesOfCode", derived = true)
    public Number getNumberOfLinesOfCode() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "instability", derived = true)
    public Number getInstability() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "abstractness", derived = true)
    public Number getAbstractness() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    


}

