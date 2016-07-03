// Automagically generated code, please do not change
package org.moosetechnology.model.famix;

import ch.akuhn.fame.internal.MultivalueSet;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import java.util.*;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("Enum")
public class Enum extends Type {



    private Collection<EnumValue> values; 

    @FameProperty(name = "values", opposite = "parentEnum", derived = true)
    public Collection<EnumValue> getValues() {
        if (values == null) {
            values = new MultivalueSet<EnumValue>() {
                @Override
                protected void clearOpposite(EnumValue e) {
                    e.setParentEnum(null);
                }
                @Override
                protected void setOpposite(EnumValue e) {
                    e.setParentEnum(Enum.this);
                }
            };
        }
        return values;
    }
    
    public void setValues(Collection<? extends EnumValue> values) {
        this.getValues().clear();
        this.getValues().addAll(values);
    }                    
    
        
    public void addValues(EnumValue one) {
        this.getValues().add(one);
    }   
    
    public void addValues(EnumValue one, EnumValue... many) {
        this.getValues().add(one);
        for (EnumValue each : many)
            this.getValues().add(each);
    }   
    
    public void addValues(Iterable<? extends EnumValue> many) {
        for (EnumValue each : many)
            this.getValues().add(each);
    }   
                
    public void addValues(EnumValue[] many) {
        for (EnumValue each : many)
            this.getValues().add(each);
    }
    
    public int numberOfValues() {
        return getValues().size();
    }

    public boolean hasValues() {
        return !getValues().isEmpty();
    }
    
                


}

