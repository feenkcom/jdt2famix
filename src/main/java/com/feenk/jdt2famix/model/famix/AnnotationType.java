// Automagically generated code, please do not change
package com.feenk.jdt2famix.model.famix;

import ch.akuhn.fame.internal.MultivalueSet;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import java.util.*;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("AnnotationType")
public class AnnotationType extends Type {



    private ContainerEntity container;
    
    @FameProperty(name = "container", opposite = "definedAnnotationTypes")
    public ContainerEntity getContainer() {
        return container;
    }

    public void setContainer(ContainerEntity container) {
        if (this.container != null) {
            if (this.container.equals(container)) return;
            this.container.getDefinedAnnotationTypes().remove(this);
        }
        this.container = container;
        if (container == null) return;
        container.getDefinedAnnotationTypes().add(this);
    }
    
    private Collection<AnnotationInstance> instances; 

    @FameProperty(name = "instances", opposite = "annotationType", derived = true)
    public Collection<AnnotationInstance> getInstances() {
        if (instances == null) {
            instances = new MultivalueSet<AnnotationInstance>() {
                @Override
                protected void clearOpposite(AnnotationInstance e) {
                    e.setAnnotationType(null);
                }
                @Override
                protected void setOpposite(AnnotationInstance e) {
                    e.setAnnotationType(AnnotationType.this);
                }
            };
        }
        return instances;
    }
    
    public void setInstances(Collection<? extends AnnotationInstance> instances) {
        this.getInstances().clear();
        this.getInstances().addAll(instances);
    }                    
    
        
    public void addInstances(AnnotationInstance one) {
        this.getInstances().add(one);
    }   
    
    public void addInstances(AnnotationInstance one, AnnotationInstance... many) {
        this.getInstances().add(one);
        for (AnnotationInstance each : many)
            this.getInstances().add(each);
    }   
    
    public void addInstances(Iterable<? extends AnnotationInstance> many) {
        for (AnnotationInstance each : many)
            this.getInstances().add(each);
    }   
                
    public void addInstances(AnnotationInstance[] many) {
        for (AnnotationInstance each : many)
            this.getInstances().add(each);
    }
    
    public int numberOfInstances() {
        return getInstances().size();
    }

    public boolean hasInstances() {
        return !getInstances().isEmpty();
    }
    
                


}

