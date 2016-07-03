// Automagically generated code, please do not change
package org.moosetechnology.model.famix;

import ch.akuhn.fame.internal.MultivalueSet;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import java.util.*;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("SourceLanguage")
public class SourceLanguage extends Entity {



    private Collection<SourcedEntity> sourcedEntities; 

    @FameProperty(name = "sourcedEntities", opposite = "declaredSourceLanguage", derived = true)
    public Collection<SourcedEntity> getSourcedEntities() {
        if (sourcedEntities == null) {
            sourcedEntities = new MultivalueSet<SourcedEntity>() {
                @Override
                protected void clearOpposite(SourcedEntity e) {
                    e.setDeclaredSourceLanguage(null);
                }
                @Override
                protected void setOpposite(SourcedEntity e) {
                    e.setDeclaredSourceLanguage(SourceLanguage.this);
                }
            };
        }
        return sourcedEntities;
    }
    
    public void setSourcedEntities(Collection<? extends SourcedEntity> sourcedEntities) {
        this.getSourcedEntities().clear();
        this.getSourcedEntities().addAll(sourcedEntities);
    }                    
    
        
    public void addSourcedEntities(SourcedEntity one) {
        this.getSourcedEntities().add(one);
    }   
    
    public void addSourcedEntities(SourcedEntity one, SourcedEntity... many) {
        this.getSourcedEntities().add(one);
        for (SourcedEntity each : many)
            this.getSourcedEntities().add(each);
    }   
    
    public void addSourcedEntities(Iterable<? extends SourcedEntity> many) {
        for (SourcedEntity each : many)
            this.getSourcedEntities().add(each);
    }   
                
    public void addSourcedEntities(SourcedEntity[] many) {
        for (SourcedEntity each : many)
            this.getSourcedEntities().add(each);
    }
    
    public int numberOfSourcedEntities() {
        return getSourcedEntities().size();
    }

    public boolean hasSourcedEntities() {
        return !getSourcedEntities().isEmpty();
    }
    
                
    @FameProperty(name = "name", derived = true)
    public String getName() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    


}

