// Automagically generated code, please do not change
package com.feenk.jdt2famix.model.file;

import ch.akuhn.fame.internal.MultivalueSet;
import ch.akuhn.fame.FameProperty;
import com.feenk.jdt2famix.model.famix.SourcedEntity;
import ch.akuhn.fame.FameDescription;
import java.util.*;
import ch.akuhn.fame.FamePackage;


@FamePackage("FILE")
@FameDescription("File")
public class File extends AbstractFile {



    private Collection<SourcedEntity> entities; 

    @FameProperty(name = "entities", opposite = "containerFiles")
    public Collection<SourcedEntity> getEntities() {
        if (entities == null) {
            entities = new MultivalueSet<SourcedEntity>() {
                @Override
                protected void clearOpposite(SourcedEntity e) {
                    e.getContainerFiles().remove(File.this);
                }
                @Override
                protected void setOpposite(SourcedEntity e) {
                    e.getContainerFiles().add(File.this);
                }
            };
        }
        return entities;
    }
    
    public void setEntities(Collection<? extends SourcedEntity> entities) {
        this.getEntities().clear();
        this.getEntities().addAll(entities);
    }
    
    public void addEntities(SourcedEntity one) {
        this.getEntities().add(one);
    }   
    
    public void addEntities(SourcedEntity one, SourcedEntity... many) {
        this.getEntities().add(one);
        for (SourcedEntity each : many)
            this.getEntities().add(each);
    }   
    
    public void addEntities(Iterable<? extends SourcedEntity> many) {
        for (SourcedEntity each : many)
            this.getEntities().add(each);
    }   
                
    public void addEntities(SourcedEntity[] many) {
        for (SourcedEntity each : many)
            this.getEntities().add(each);
    }
    
    public int numberOfEntities() {
        return getEntities().size();
    }

    public boolean hasEntities() {
        return !getEntities().isEmpty();
    }
    
                


}

