// Automagically generated code, please do not change
package com.feenk.jdt2famix.model.famix;

import ch.akuhn.fame.internal.MultivalueSet;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import java.util.*;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("AnnotationInstance")
public class AnnotationInstance extends Entity {



    private NamedEntity annotatedEntity;
    
    @FameProperty(name = "annotatedEntity", opposite = "annotationInstances")
    public NamedEntity getAnnotatedEntity() {
        return annotatedEntity;
    }

    public void setAnnotatedEntity(NamedEntity annotatedEntity) {
        if (this.annotatedEntity != null) {
            if (this.annotatedEntity.equals(annotatedEntity)) return;
            this.annotatedEntity.getAnnotationInstances().remove(this);
        }
        this.annotatedEntity = annotatedEntity;
        if (annotatedEntity == null) return;
        annotatedEntity.getAnnotationInstances().add(this);
    }
    
    private AnnotationType annotationType;
    
    @FameProperty(name = "annotationType", opposite = "instances")
    public AnnotationType getAnnotationType() {
        return annotationType;
    }

    public void setAnnotationType(AnnotationType annotationType) {
        if (this.annotationType != null) {
            if (this.annotationType.equals(annotationType)) return;
            this.annotationType.getInstances().remove(this);
        }
        this.annotationType = annotationType;
        if (annotationType == null) return;
        annotationType.getInstances().add(this);
    }
    
    private Collection<AnnotationInstanceAttribute> attributes; 

    @FameProperty(name = "attributes", opposite = "parentAnnotationInstance", derived = true)
    public Collection<AnnotationInstanceAttribute> getAttributes() {
        if (attributes == null) {
            attributes = new MultivalueSet<AnnotationInstanceAttribute>() {
                @Override
                protected void clearOpposite(AnnotationInstanceAttribute e) {
                    e.setParentAnnotationInstance(null);
                }
                @Override
                protected void setOpposite(AnnotationInstanceAttribute e) {
                    e.setParentAnnotationInstance(AnnotationInstance.this);
                }
            };
        }
        return attributes;
    }
    
    public void setAttributes(Collection<? extends AnnotationInstanceAttribute> attributes) {
        this.getAttributes().clear();
        this.getAttributes().addAll(attributes);
    }                    
    
        
    public void addAttributes(AnnotationInstanceAttribute one) {
        this.getAttributes().add(one);
    }   
    
    public void addAttributes(AnnotationInstanceAttribute one, AnnotationInstanceAttribute... many) {
        this.getAttributes().add(one);
        for (AnnotationInstanceAttribute each : many)
            this.getAttributes().add(each);
    }   
    
    public void addAttributes(Iterable<? extends AnnotationInstanceAttribute> many) {
        for (AnnotationInstanceAttribute each : many)
            this.getAttributes().add(each);
    }   
                
    public void addAttributes(AnnotationInstanceAttribute[] many) {
        for (AnnotationInstanceAttribute each : many)
            this.getAttributes().add(each);
    }
    
    public int numberOfAttributes() {
        return getAttributes().size();
    }

    public boolean hasAttributes() {
        return !getAttributes().isEmpty();
    }
    
                


}

