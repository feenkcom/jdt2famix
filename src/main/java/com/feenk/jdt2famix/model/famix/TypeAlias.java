// Automagically generated code, please do not change
package com.feenk.jdt2famix.model.famix;

import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("TypeAlias")
public class TypeAlias extends Type {



    private Type aliasedType;
    
    @FameProperty(name = "aliasedType", opposite = "typeAliases")
    public Type getAliasedType() {
        return aliasedType;
    }

    public void setAliasedType(Type aliasedType) {
        if (this.aliasedType != null) {
            if (this.aliasedType.equals(aliasedType)) return;
            this.aliasedType.getTypeAliases().remove(this);
        }
        this.aliasedType = aliasedType;
        if (aliasedType == null) return;
        aliasedType.getTypeAliases().add(this);
    }
    


}

