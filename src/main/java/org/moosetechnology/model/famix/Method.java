// Automagically generated code, please do not change
package org.moosetechnology.model.famix;

import ch.akuhn.fame.internal.MultivalueSet;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import java.util.*;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("Method")
public class Method extends BehaviouralEntity {



    @FameProperty(name = "isGetter", derived = true)
    public Boolean getIsGetter() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "isInternalImplementation", derived = true)
    public Boolean getIsInternalImplementation() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "hierarchyNestingLevel", derived = true)
    public Number getHierarchyNestingLevel() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "invokedMethods", derived = true)
    public Collection<Method> getInvokedMethods() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
        
    @FameProperty(name = "clientTypes", derived = true)
    public Collection<Type> getClientTypes() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
        
    private String kind;
    
    @FameProperty(name = "kind")
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
    
    @FameProperty(name = "providerTypes", derived = true)
    public Collection<Type> getProviderTypes() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
        
    @FameProperty(name = "invokingMethods", derived = true)
    public Collection<Method> getInvokingMethods() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
        
    private Collection<ThrownException> thrownExceptions; 

    @FameProperty(name = "thrownExceptions", opposite = "definingMethod", derived = true)
    public Collection<ThrownException> getThrownExceptions() {
        if (thrownExceptions == null) {
            thrownExceptions = new MultivalueSet<ThrownException>() {
                @Override
                protected void clearOpposite(ThrownException e) {
                    e.setDefiningMethod(null);
                }
                @Override
                protected void setOpposite(ThrownException e) {
                    e.setDefiningMethod(Method.this);
                }
            };
        }
        return thrownExceptions;
    }
    
    public void setThrownExceptions(Collection<? extends ThrownException> thrownExceptions) {
        this.getThrownExceptions().clear();
        this.getThrownExceptions().addAll(thrownExceptions);
    }                    
    
        
    public void addThrownExceptions(ThrownException one) {
        this.getThrownExceptions().add(one);
    }   
    
    public void addThrownExceptions(ThrownException one, ThrownException... many) {
        this.getThrownExceptions().add(one);
        for (ThrownException each : many)
            this.getThrownExceptions().add(each);
    }   
    
    public void addThrownExceptions(Iterable<? extends ThrownException> many) {
        for (ThrownException each : many)
            this.getThrownExceptions().add(each);
    }   
                
    public void addThrownExceptions(ThrownException[] many) {
        for (ThrownException each : many)
            this.getThrownExceptions().add(each);
    }
    
    public int numberOfThrownExceptions() {
        return getThrownExceptions().size();
    }

    public boolean hasThrownExceptions() {
        return !getThrownExceptions().isEmpty();
    }
    
                
    @FameProperty(name = "numberOfInvokedMethods", derived = true)
    public Number getNumberOfInvokedMethods() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    private Collection<CaughtException> caughtExceptions; 

    @FameProperty(name = "caughtExceptions", opposite = "definingMethod", derived = true)
    public Collection<CaughtException> getCaughtExceptions() {
        if (caughtExceptions == null) {
            caughtExceptions = new MultivalueSet<CaughtException>() {
                @Override
                protected void clearOpposite(CaughtException e) {
                    e.setDefiningMethod(null);
                }
                @Override
                protected void setOpposite(CaughtException e) {
                    e.setDefiningMethod(Method.this);
                }
            };
        }
        return caughtExceptions;
    }
    
    public void setCaughtExceptions(Collection<? extends CaughtException> caughtExceptions) {
        this.getCaughtExceptions().clear();
        this.getCaughtExceptions().addAll(caughtExceptions);
    }                    
    
        
    public void addCaughtExceptions(CaughtException one) {
        this.getCaughtExceptions().add(one);
    }   
    
    public void addCaughtExceptions(CaughtException one, CaughtException... many) {
        this.getCaughtExceptions().add(one);
        for (CaughtException each : many)
            this.getCaughtExceptions().add(each);
    }   
    
    public void addCaughtExceptions(Iterable<? extends CaughtException> many) {
        for (CaughtException each : many)
            this.getCaughtExceptions().add(each);
    }   
                
    public void addCaughtExceptions(CaughtException[] many) {
        for (CaughtException each : many)
            this.getCaughtExceptions().add(each);
    }
    
    public int numberOfCaughtExceptions() {
        return getCaughtExceptions().size();
    }

    public boolean hasCaughtExceptions() {
        return !getCaughtExceptions().isEmpty();
    }
    
                
    @FameProperty(name = "numberOfAnnotationInstances", derived = true)
    public Number getNumberOfAnnotationInstances() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    private Boolean hasClassScope;
    
    @FameProperty(name = "hasClassScope")
    public Boolean getHasClassScope() {
        return hasClassScope;
    }

    public void setHasClassScope(Boolean hasClassScope) {
        this.hasClassScope = hasClassScope;
    }
    
    private Type parentType;
    
    @FameProperty(name = "parentType", opposite = "methods")
    public Type getParentType() {
        return parentType;
    }

    public void setParentType(Type parentType) {
        if (this.parentType != null) {
            if (this.parentType.equals(parentType)) return;
            this.parentType.getMethods().remove(this);
        }
        this.parentType = parentType;
        if (parentType == null) return;
        parentType.getMethods().add(this);
    }
    
    private String timeStamp;
    
    @FameProperty(name = "timeStamp")
    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    @FameProperty(name = "isJUnit4Test", derived = true)
    public Boolean getIsJUnit4Test() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "isOverriden", derived = true)
    public Boolean getIsOverriden() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "isConstant", derived = true)
    public Boolean getIsConstant() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "isImplementing", derived = true)
    public Boolean getIsImplementing() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "isConstructor", derived = true)
    public Boolean getIsConstructor() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "isSetter", derived = true)
    public Boolean getIsSetter() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    @FameProperty(name = "isOverriding", derived = true)
    public Boolean getIsOverriding() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    private Collection<DeclaredException> declaredExceptions; 

    @FameProperty(name = "declaredExceptions", opposite = "definingMethod", derived = true)
    public Collection<DeclaredException> getDeclaredExceptions() {
        if (declaredExceptions == null) {
            declaredExceptions = new MultivalueSet<DeclaredException>() {
                @Override
                protected void clearOpposite(DeclaredException e) {
                    e.setDefiningMethod(null);
                }
                @Override
                protected void setOpposite(DeclaredException e) {
                    e.setDefiningMethod(Method.this);
                }
            };
        }
        return declaredExceptions;
    }
    
    public void setDeclaredExceptions(Collection<? extends DeclaredException> declaredExceptions) {
        this.getDeclaredExceptions().clear();
        this.getDeclaredExceptions().addAll(declaredExceptions);
    }                    
    
        
    public void addDeclaredExceptions(DeclaredException one) {
        this.getDeclaredExceptions().add(one);
    }   
    
    public void addDeclaredExceptions(DeclaredException one, DeclaredException... many) {
        this.getDeclaredExceptions().add(one);
        for (DeclaredException each : many)
            this.getDeclaredExceptions().add(each);
    }   
    
    public void addDeclaredExceptions(Iterable<? extends DeclaredException> many) {
        for (DeclaredException each : many)
            this.getDeclaredExceptions().add(each);
    }   
                
    public void addDeclaredExceptions(DeclaredException[] many) {
        for (DeclaredException each : many)
            this.getDeclaredExceptions().add(each);
    }
    
    public int numberOfDeclaredExceptions() {
        return getDeclaredExceptions().size();
    }

    public boolean hasDeclaredExceptions() {
        return !getDeclaredExceptions().isEmpty();
    }
    
                
    private String category;
    
    @FameProperty(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    


}

