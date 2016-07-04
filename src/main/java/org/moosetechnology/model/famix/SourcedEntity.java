// Automagically generated code, please do not change
package org.moosetechnology.model.famix;

import ch.akuhn.fame.internal.MultivalueSet;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import java.util.*;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("SourcedEntity")
public class SourcedEntity extends Entity {



    private SourceAnchor sourceAnchor;
    
    @FameProperty(name = "sourceAnchor", opposite = "element")
    public SourceAnchor getSourceAnchor() {
        return sourceAnchor;
    }

    public void setSourceAnchor(SourceAnchor sourceAnchor) {
        if (this.sourceAnchor == null ? sourceAnchor != null : !this.sourceAnchor.equals(sourceAnchor)) {
            SourceAnchor old_sourceAnchor = this.sourceAnchor;
            this.sourceAnchor = sourceAnchor;
            if (old_sourceAnchor != null) old_sourceAnchor.setElement(null);
            if (sourceAnchor != null) sourceAnchor.setElement(this);
        }
    }
    
    private Collection<Comment> comments; 

    @FameProperty(name = "comments", opposite = "container", derived = true)
    public Collection<Comment> getComments() {
        if (comments == null) {
            comments = new MultivalueSet<Comment>() {
                @Override
                protected void clearOpposite(Comment e) {
                    e.setContainer(null);
                }
                @Override
                protected void setOpposite(Comment e) {
                    e.setContainer(SourcedEntity.this);
                }
            };
        }
        return comments;
    }
    
    public void setComments(Collection<? extends Comment> comments) {
        this.getComments().clear();
        this.getComments().addAll(comments);
    }                    
    
        
    public void addComments(Comment one) {
        this.getComments().add(one);
    }   
    
    public void addComments(Comment one, Comment... many) {
        this.getComments().add(one);
        for (Comment each : many)
            this.getComments().add(each);
    }   
    
    public void addComments(Iterable<? extends Comment> many) {
        for (Comment each : many)
            this.getComments().add(each);
    }   
                
    public void addComments(Comment[] many) {
        for (Comment each : many)
            this.getComments().add(each);
    }
    
    public int numberOfComments() {
        return getComments().size();
    }

    public boolean hasComments() {
        return !getComments().isEmpty();
    }
    
                
    private SourceLanguage declaredSourceLanguage;
    
    @FameProperty(name = "declaredSourceLanguage", opposite = "sourcedEntities")
    public SourceLanguage getDeclaredSourceLanguage() {
        return declaredSourceLanguage;
    }

    public void setDeclaredSourceLanguage(SourceLanguage declaredSourceLanguage) {
        if (this.declaredSourceLanguage != null) {
            if (this.declaredSourceLanguage.equals(declaredSourceLanguage)) return;
            this.declaredSourceLanguage.getSourcedEntities().remove(this);
        }
        this.declaredSourceLanguage = declaredSourceLanguage;
        if (declaredSourceLanguage == null) return;
        declaredSourceLanguage.getSourcedEntities().add(this);
    }
    


}

