// Automagically generated code, please do not change
package com.feenk.jdt2famix.model.file;

import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import ch.akuhn.fame.FamePackage;


@FamePackage("FILE")
@FameDescription("Folder")
public class Folder extends AbstractFile {



    private Number numberOfFiles;
    
    @FameProperty(name = "numberOfFiles")
    public Number getNumberOfFiles() {
        return numberOfFiles;
    }

    public void setNumberOfFiles(Number numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }
    
    private Number totalNumberOfLinesOfText;
    
    @FameProperty(name = "totalNumberOfLinesOfText")
    public Number getTotalNumberOfLinesOfText() {
        return totalNumberOfLinesOfText;
    }

    public void setTotalNumberOfLinesOfText(Number totalNumberOfLinesOfText) {
        this.totalNumberOfLinesOfText = totalNumberOfLinesOfText;
    }
    
    private Number numberOfFolders;
    
    @FameProperty(name = "numberOfFolders")
    public Number getNumberOfFolders() {
        return numberOfFolders;
    }

    public void setNumberOfFolders(Number numberOfFolders) {
        this.numberOfFolders = numberOfFolders;
    }
    
    private Number numberOfLinesOfText;
    
    @FameProperty(name = "numberOfLinesOfText")
    public Number getNumberOfLinesOfText() {
        return numberOfLinesOfText;
    }

    public void setNumberOfLinesOfText(Number numberOfLinesOfText) {
        this.numberOfLinesOfText = numberOfLinesOfText;
    }
    
    private Number numberOfEmptyLinesOfText;
    
    @FameProperty(name = "numberOfEmptyLinesOfText")
    public Number getNumberOfEmptyLinesOfText() {
        return numberOfEmptyLinesOfText;
    }

    public void setNumberOfEmptyLinesOfText(Number numberOfEmptyLinesOfText) {
        this.numberOfEmptyLinesOfText = numberOfEmptyLinesOfText;
    }
    


}

