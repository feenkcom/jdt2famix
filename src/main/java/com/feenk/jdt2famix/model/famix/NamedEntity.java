// Automagically generated code, please do not change
package com.feenk.jdt2famix.model.famix;

import ch.akuhn.fame.internal.MultivalueSet;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import java.util.*;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("NamedEntity")
public class NamedEntity extends SourcedEntity {



    private Collection<Invocation> receivingInvocations; 

    @FameProperty(name = "receivingInvocations", opposite = "receiver", derived = true)
    public Collection<Invocation> getReceivingInvocations() {
        if (receivingInvocations == null) {
            receivingInvocations = new MultivalueSet<Invocation>() {
                @Override
                protected void clearOpposite(Invocation e) {
                    e.setReceiver(null);
                }
                @Override
                protected void setOpposite(Invocation e) {
                    e.setReceiver(NamedEntity.this);
                }
            };
        }
        return receivingInvocations;
    }
    
    public void setReceivingInvocations(Collection<? extends Invocation> receivingInvocations) {
        this.getReceivingInvocations().clear();
        this.getReceivingInvocations().addAll(receivingInvocations);
    }                    
    
        
    public void addReceivingInvocations(Invocation one) {
        this.getReceivingInvocations().add(one);
    }   
    
    public void addReceivingInvocations(Invocation one, Invocation... many) {
        this.getReceivingInvocations().add(one);
        for (Invocation each : many)
            this.getReceivingInvocations().add(each);
    }   
    
    public void addReceivingInvocations(Iterable<? extends Invocation> many) {
        for (Invocation each : many)
            this.getReceivingInvocations().add(each);
    }   
                
    public void addReceivingInvocations(Invocation[] many) {
        for (Invocation each : many)
            this.getReceivingInvocations().add(each);
    }
    
    public int numberOfReceivingInvocations() {
        return getReceivingInvocations().size();
    }

    public boolean hasReceivingInvocations() {
        return !getReceivingInvocations().isEmpty();
    }
    
                
    private Collection<String> modifiers; 

    @FameProperty(name = "modifiers")
    public Collection<String> getModifiers() {
        if (modifiers == null) modifiers = new HashSet<String>();
        return modifiers;
    }
    
    public void setModifiers(Collection<? extends String> modifiers) {
        this.getModifiers().clear();
        this.getModifiers().addAll(modifiers);
    }                    

    public void addModifiers(String one) {
        this.getModifiers().add(one);
    }   
    
    public void addModifiers(String one, String... many) {
        this.getModifiers().add(one);
        for (String each : many)
            this.getModifiers().add(each);
    }   
    
    public void addModifiers(Iterable<? extends String> many) {
        for (String each : many)
            this.getModifiers().add(each);
    }   
                
    public void addModifiers(String[] many) {
        for (String each : many)
            this.getModifiers().add(each);
    }
    
    public int numberOfModifiers() {
        return getModifiers().size();
    }

    public boolean hasModifiers() {
        return !getModifiers().isEmpty();
    }
    
                
    private Boolean isStub;
    
    @FameProperty(name = "isStub")
    public Boolean getIsStub() {
        return isStub;
    }

    public void setIsStub(Boolean isStub) {
        this.isStub = isStub;
    }
    
    private String name;
    
    @FameProperty(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    private Package parentPackage;
    
    @FameProperty(name = "parentPackage", opposite = "childNamedEntities")
    public Package getParentPackage() {
        return parentPackage;
    }

    public void setParentPackage(Package parentPackage) {
        if (this.parentPackage != null) {
            if (this.parentPackage.equals(parentPackage)) return;
            this.parentPackage.getChildNamedEntities().remove(this);
        }
        this.parentPackage = parentPackage;
        if (parentPackage == null) return;
        parentPackage.getChildNamedEntities().add(this);
    }
    
    private Collection<AnnotationInstance> annotationInstances; 

    @FameProperty(name = "annotationInstances", opposite = "annotatedEntity", derived = true)
    public Collection<AnnotationInstance> getAnnotationInstances() {
        if (annotationInstances == null) {
            annotationInstances = new MultivalueSet<AnnotationInstance>() {
                @Override
                protected void clearOpposite(AnnotationInstance e) {
                    e.setAnnotatedEntity(null);
                }
                @Override
                protected void setOpposite(AnnotationInstance e) {
                    e.setAnnotatedEntity(NamedEntity.this);
                }
            };
        }
        return annotationInstances;
    }
    
    public void setAnnotationInstances(Collection<? extends AnnotationInstance> annotationInstances) {
        this.getAnnotationInstances().clear();
        this.getAnnotationInstances().addAll(annotationInstances);
    }                    
    
        
    public void addAnnotationInstances(AnnotationInstance one) {
        this.getAnnotationInstances().add(one);
    }   
    
    public void addAnnotationInstances(AnnotationInstance one, AnnotationInstance... many) {
        this.getAnnotationInstances().add(one);
        for (AnnotationInstance each : many)
            this.getAnnotationInstances().add(each);
    }   
    
    public void addAnnotationInstances(Iterable<? extends AnnotationInstance> many) {
        for (AnnotationInstance each : many)
            this.getAnnotationInstances().add(each);
    }   
                
    public void addAnnotationInstances(AnnotationInstance[] many) {
        for (AnnotationInstance each : many)
            this.getAnnotationInstances().add(each);
    }
    
    public int numberOfAnnotationInstances() {
        return getAnnotationInstances().size();
    }

    public boolean hasAnnotationInstances() {
        return !getAnnotationInstances().isEmpty();
    }
    
                


}

