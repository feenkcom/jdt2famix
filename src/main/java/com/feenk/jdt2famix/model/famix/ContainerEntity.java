// Automagically generated code, please do not change
package com.feenk.jdt2famix.model.famix;

import ch.akuhn.fame.internal.MultivalueSet;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import java.util.*;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("ContainerEntity")
public class ContainerEntity extends NamedEntity {



    private Collection<Type> types; 

    @FameProperty(name = "types", opposite = "container", derived = true)
    public Collection<Type> getTypes() {
        if (types == null) {
            types = new MultivalueSet<Type>() {
                @Override
                protected void clearOpposite(Type e) {
                    e.setContainer(null);
                }
                @Override
                protected void setOpposite(Type e) {
                    e.setContainer(ContainerEntity.this);
                }
            };
        }
        return types;
    }
    
    public void setTypes(Collection<? extends Type> types) {
        this.getTypes().clear();
        this.getTypes().addAll(types);
    }                    
    
        
    public void addTypes(Type one) {
        this.getTypes().add(one);
    }   
    
    public void addTypes(Type one, Type... many) {
        this.getTypes().add(one);
        for (Type each : many)
            this.getTypes().add(each);
    }   
    
    public void addTypes(Iterable<? extends Type> many) {
        for (Type each : many)
            this.getTypes().add(each);
    }   
                
    public void addTypes(Type[] many) {
        for (Type each : many)
            this.getTypes().add(each);
    }
    
    public int numberOfTypes() {
        return getTypes().size();
    }

    public boolean hasTypes() {
        return !getTypes().isEmpty();
    }
    
                
    private Collection<Function> functions; 

    @FameProperty(name = "functions", opposite = "container", derived = true)
    public Collection<Function> getFunctions() {
        if (functions == null) {
            functions = new MultivalueSet<Function>() {
                @Override
                protected void clearOpposite(Function e) {
                    e.setContainer(null);
                }
                @Override
                protected void setOpposite(Function e) {
                    e.setContainer(ContainerEntity.this);
                }
            };
        }
        return functions;
    }
    
    public void setFunctions(Collection<? extends Function> functions) {
        this.getFunctions().clear();
        this.getFunctions().addAll(functions);
    }                    
    
        
    public void addFunctions(Function one) {
        this.getFunctions().add(one);
    }   
    
    public void addFunctions(Function one, Function... many) {
        this.getFunctions().add(one);
        for (Function each : many)
            this.getFunctions().add(each);
    }   
    
    public void addFunctions(Iterable<? extends Function> many) {
        for (Function each : many)
            this.getFunctions().add(each);
    }   
                
    public void addFunctions(Function[] many) {
        for (Function each : many)
            this.getFunctions().add(each);
    }
    
    public int numberOfFunctions() {
        return getFunctions().size();
    }

    public boolean hasFunctions() {
        return !getFunctions().isEmpty();
    }
    
                
    private Collection<AnnotationType> definedAnnotationTypes; 

    @FameProperty(name = "definedAnnotationTypes", opposite = "container", derived = true)
    public Collection<AnnotationType> getDefinedAnnotationTypes() {
        if (definedAnnotationTypes == null) {
            definedAnnotationTypes = new MultivalueSet<AnnotationType>() {
                @Override
                protected void clearOpposite(AnnotationType e) {
                    e.setContainer(null);
                }
                @Override
                protected void setOpposite(AnnotationType e) {
                    e.setContainer(ContainerEntity.this);
                }
            };
        }
        return definedAnnotationTypes;
    }
    
    public void setDefinedAnnotationTypes(Collection<? extends AnnotationType> definedAnnotationTypes) {
        this.getDefinedAnnotationTypes().clear();
        this.getDefinedAnnotationTypes().addAll(definedAnnotationTypes);
    }                    
    
        
    public void addDefinedAnnotationTypes(AnnotationType one) {
        this.getDefinedAnnotationTypes().add(one);
    }   
    
    public void addDefinedAnnotationTypes(AnnotationType one, AnnotationType... many) {
        this.getDefinedAnnotationTypes().add(one);
        for (AnnotationType each : many)
            this.getDefinedAnnotationTypes().add(each);
    }   
    
    public void addDefinedAnnotationTypes(Iterable<? extends AnnotationType> many) {
        for (AnnotationType each : many)
            this.getDefinedAnnotationTypes().add(each);
    }   
                
    public void addDefinedAnnotationTypes(AnnotationType[] many) {
        for (AnnotationType each : many)
            this.getDefinedAnnotationTypes().add(each);
    }
    
    public int numberOfDefinedAnnotationTypes() {
        return getDefinedAnnotationTypes().size();
    }

    public boolean hasDefinedAnnotationTypes() {
        return !getDefinedAnnotationTypes().isEmpty();
    }
    
                


}

