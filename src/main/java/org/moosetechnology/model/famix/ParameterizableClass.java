// Automagically generated code, please do not change
package org.moosetechnology.model.famix;

import ch.akuhn.fame.internal.MultivalueSet;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import java.util.*;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("ParameterizableClass")
public class ParameterizableClass extends Class {



    private Collection<ParameterizedType> parameterizedTypes; 

    @FameProperty(name = "parameterizedTypes", opposite = "parameterizableClass", derived = true)
    public Collection<ParameterizedType> getParameterizedTypes() {
        if (parameterizedTypes == null) {
            parameterizedTypes = new MultivalueSet<ParameterizedType>() {
                @Override
                protected void clearOpposite(ParameterizedType e) {
                    e.setParameterizableClass(null);
                }
                @Override
                protected void setOpposite(ParameterizedType e) {
                    e.setParameterizableClass(ParameterizableClass.this);
                }
            };
        }
        return parameterizedTypes;
    }
    
    public void setParameterizedTypes(Collection<? extends ParameterizedType> parameterizedTypes) {
        this.getParameterizedTypes().clear();
        this.getParameterizedTypes().addAll(parameterizedTypes);
    }                    
    
        
    public void addParameterizedTypes(ParameterizedType one) {
        this.getParameterizedTypes().add(one);
    }   
    
    public void addParameterizedTypes(ParameterizedType one, ParameterizedType... many) {
        this.getParameterizedTypes().add(one);
        for (ParameterizedType each : many)
            this.getParameterizedTypes().add(each);
    }   
    
    public void addParameterizedTypes(Iterable<? extends ParameterizedType> many) {
        for (ParameterizedType each : many)
            this.getParameterizedTypes().add(each);
    }   
                
    public void addParameterizedTypes(ParameterizedType[] many) {
        for (ParameterizedType each : many)
            this.getParameterizedTypes().add(each);
    }
    
    public int numberOfParameterizedTypes() {
        return getParameterizedTypes().size();
    }

    public boolean hasParameterizedTypes() {
        return !getParameterizedTypes().isEmpty();
    }
    
                


}

