// Automagically generated code, please do not change
package com.feenk.jdt2famix.model.famix;

import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import java.util.*;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("MultipleFileAnchor")
public class MultipleFileAnchor extends SourceAnchor {



    private Collection<FileAnchor> allFiles; 

    @FameProperty(name = "allFiles")
    public Collection<FileAnchor> getAllFiles() {
        if (allFiles == null) allFiles = new HashSet<FileAnchor>();
        return allFiles;
    }
    
    public void setAllFiles(Collection<? extends FileAnchor> allFiles) {
        this.getAllFiles().clear();
        this.getAllFiles().addAll(allFiles);
    }                    

    public void addAllFiles(FileAnchor one) {
        this.getAllFiles().add(one);
    }   
    
    public void addAllFiles(FileAnchor one, FileAnchor... many) {
        this.getAllFiles().add(one);
        for (FileAnchor each : many)
            this.getAllFiles().add(each);
    }   
    
    public void addAllFiles(Iterable<? extends FileAnchor> many) {
        for (FileAnchor each : many)
            this.getAllFiles().add(each);
    }   
                
    public void addAllFiles(FileAnchor[] many) {
        for (FileAnchor each : many)
            this.getAllFiles().add(each);
    }
    
    public int numberOfAllFiles() {
        return getAllFiles().size();
    }

    public boolean hasAllFiles() {
        return !getAllFiles().isEmpty();
    }
    
                


}

