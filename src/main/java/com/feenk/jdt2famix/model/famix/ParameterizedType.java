// Automagically generated code, please do not change
package com.feenk.jdt2famix.model.famix;

import ch.akuhn.fame.internal.MultivalueSet;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import java.util.*;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("ParameterizedType")
public class ParameterizedType extends Type {



    private ParameterizableClass parameterizableClass;
    
    @FameProperty(name = "parameterizableClass", opposite = "parameterizedTypes")
    public ParameterizableClass getParameterizableClass() {
        return parameterizableClass;
    }

    public void setParameterizableClass(ParameterizableClass parameterizableClass) {
        if (this.parameterizableClass != null) {
            if (this.parameterizableClass.equals(parameterizableClass)) return;
            this.parameterizableClass.getParameterizedTypes().remove(this);
        }
        this.parameterizableClass = parameterizableClass;
        if (parameterizableClass == null) return;
        parameterizableClass.getParameterizedTypes().add(this);
    }
    
    private Collection<Type> arguments; 

    @FameProperty(name = "arguments", opposite = "argumentsInParameterizedTypes")
    public Collection<Type> getArguments() {
        if (arguments == null) {
            arguments = new MultivalueSet<Type>() {
                @Override
                protected void clearOpposite(Type e) {
                    e.getArgumentsInParameterizedTypes().remove(ParameterizedType.this);
                }
                @Override
                protected void setOpposite(Type e) {
                    e.getArgumentsInParameterizedTypes().add(ParameterizedType.this);
                }
            };
        }
        return arguments;
    }
    
    public void setArguments(Collection<? extends Type> arguments) {
        this.getArguments().clear();
        this.getArguments().addAll(arguments);
    }
    
    public void addArguments(Type one) {
        this.getArguments().add(one);
    }   
    
    public void addArguments(Type one, Type... many) {
        this.getArguments().add(one);
        for (Type each : many)
            this.getArguments().add(each);
    }   
    
    public void addArguments(Iterable<? extends Type> many) {
        for (Type each : many)
            this.getArguments().add(each);
    }   
                
    public void addArguments(Type[] many) {
        for (Type each : many)
            this.getArguments().add(each);
    }
    
    public int numberOfArguments() {
        return getArguments().size();
    }

    public boolean hasArguments() {
        return !getArguments().isEmpty();
    }
    
                


}

