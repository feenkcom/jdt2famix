// Automagically generated code, please do not change
package com.feenk.jdt2famix.model.famix;

import ch.akuhn.fame.internal.MultivalueSet;
import com.feenk.jdt2famix.model.file.File;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import java.util.*;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("SourcedEntity")
public class SourcedEntity extends Entity {



    private SourceAnchor sourceAnchor;
    
    @FameProperty(name = "sourceAnchor", opposite = "element", derived = true)
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
    
    private Number astStartPosition;
    
    @FameProperty(name = "astStartPosition")
    public Number getAstStartPosition() {
        return astStartPosition;
    }

    public void setAstStartPosition(Number astStartPosition) {
        this.astStartPosition = astStartPosition;
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
    
                
    private Collection<File> containerFiles; 

    @FameProperty(name = "containerFiles", opposite = "entities")
    public Collection<File> getContainerFiles() {
        if (containerFiles == null) {
            containerFiles = new MultivalueSet<File>() {
                @Override
                protected void clearOpposite(File e) {
                    e.getEntities().remove(SourcedEntity.this);
                }
                @Override
                protected void setOpposite(File e) {
                    e.getEntities().add(SourcedEntity.this);
                }
            };
        }
        return containerFiles;
    }
    
    public void setContainerFiles(Collection<? extends File> containerFiles) {
        this.getContainerFiles().clear();
        this.getContainerFiles().addAll(containerFiles);
    }
    
    public void addContainerFiles(File one) {
        this.getContainerFiles().add(one);
    }   
    
    public void addContainerFiles(File one, File... many) {
        this.getContainerFiles().add(one);
        for (File each : many)
            this.getContainerFiles().add(each);
    }   
    
    public void addContainerFiles(Iterable<? extends File> many) {
        for (File each : many)
            this.getContainerFiles().add(each);
    }   
                
    public void addContainerFiles(File[] many) {
        for (File each : many)
            this.getContainerFiles().add(each);
    }
    
    public int numberOfContainerFiles() {
        return getContainerFiles().size();
    }

    public boolean hasContainerFiles() {
        return !getContainerFiles().isEmpty();
    }
    
                
    private Number astStopPosition;
    
    @FameProperty(name = "astStopPosition")
    public Number getAstStopPosition() {
        return astStopPosition;
    }

    public void setAstStopPosition(Number astStopPosition) {
        this.astStopPosition = astStopPosition;
    }
    


}

