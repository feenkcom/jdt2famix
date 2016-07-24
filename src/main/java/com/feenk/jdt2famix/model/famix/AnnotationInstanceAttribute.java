// Automagically generated code, please do not change
package com.feenk.jdt2famix.model.famix;

import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("AnnotationInstanceAttribute")
public class AnnotationInstanceAttribute extends SourcedEntity {



    private AnnotationTypeAttribute annotationTypeAttribute;
    
    @FameProperty(name = "annotationTypeAttribute", opposite = "annotationAttributeInstances")
    public AnnotationTypeAttribute getAnnotationTypeAttribute() {
        return annotationTypeAttribute;
    }

    public void setAnnotationTypeAttribute(AnnotationTypeAttribute annotationTypeAttribute) {
        if (this.annotationTypeAttribute != null) {
            if (this.annotationTypeAttribute.equals(annotationTypeAttribute)) return;
            this.annotationTypeAttribute.getAnnotationAttributeInstances().remove(this);
        }
        this.annotationTypeAttribute = annotationTypeAttribute;
        if (annotationTypeAttribute == null) return;
        annotationTypeAttribute.getAnnotationAttributeInstances().add(this);
    }
    
    private AnnotationInstance parentAnnotationInstance;
    
    @FameProperty(name = "parentAnnotationInstance", opposite = "attributes")
    public AnnotationInstance getParentAnnotationInstance() {
        return parentAnnotationInstance;
    }

    public void setParentAnnotationInstance(AnnotationInstance parentAnnotationInstance) {
        if (this.parentAnnotationInstance != null) {
            if (this.parentAnnotationInstance.equals(parentAnnotationInstance)) return;
            this.parentAnnotationInstance.getAttributes().remove(this);
        }
        this.parentAnnotationInstance = parentAnnotationInstance;
        if (parentAnnotationInstance == null) return;
        parentAnnotationInstance.getAttributes().add(this);
    }
    
    private String value;
    
    @FameProperty(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    


}

