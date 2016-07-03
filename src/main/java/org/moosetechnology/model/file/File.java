// Automagically generated code, please do not change
package org.moosetechnology.model.file;

import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import ch.akuhn.fame.FamePackage;


@FamePackage("FILE")
@FameDescription("File")
public class File extends AbstractFile {



    private Number numberOfInternalClones;
    
    @FameProperty(name = "numberOfInternalClones")
    public Number getNumberOfInternalClones() {
        return numberOfInternalClones;
    }

    public void setNumberOfInternalClones(Number numberOfInternalClones) {
        this.numberOfInternalClones = numberOfInternalClones;
    }
    
    private Number totalNumberOfLinesOfText;
    
    @FameProperty(name = "totalNumberOfLinesOfText")
    public Number getTotalNumberOfLinesOfText() {
        return totalNumberOfLinesOfText;
    }

    public void setTotalNumberOfLinesOfText(Number totalNumberOfLinesOfText) {
        this.totalNumberOfLinesOfText = totalNumberOfLinesOfText;
    }
    
    private Number numberOfKiloBytes;
    
    @FameProperty(name = "numberOfKiloBytes")
    public Number getNumberOfKiloBytes() {
        return numberOfKiloBytes;
    }

    public void setNumberOfKiloBytes(Number numberOfKiloBytes) {
        this.numberOfKiloBytes = numberOfKiloBytes;
    }
    
    private Number numberOfDuplicatedFiles;
    
    @FameProperty(name = "numberOfDuplicatedFiles")
    public Number getNumberOfDuplicatedFiles() {
        return numberOfDuplicatedFiles;
    }

    public void setNumberOfDuplicatedFiles(Number numberOfDuplicatedFiles) {
        this.numberOfDuplicatedFiles = numberOfDuplicatedFiles;
    }
    
    private Number numberOfEmptyLinesOfText;
    
    @FameProperty(name = "numberOfEmptyLinesOfText")
    public Number getNumberOfEmptyLinesOfText() {
        return numberOfEmptyLinesOfText;
    }

    public void setNumberOfEmptyLinesOfText(Number numberOfEmptyLinesOfText) {
        this.numberOfEmptyLinesOfText = numberOfEmptyLinesOfText;
    }
    
    private Number numberOfCharacters;
    
    @FameProperty(name = "numberOfCharacters")
    public Number getNumberOfCharacters() {
        return numberOfCharacters;
    }

    public void setNumberOfCharacters(Number numberOfCharacters) {
        this.numberOfCharacters = numberOfCharacters;
    }
    
    @FameProperty(name = "numberOfInternalDuplications", derived = true)
    public Number getNumberOfInternalDuplications() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    private Number averageNumberOfCharactersPerLine;
    
    @FameProperty(name = "averageNumberOfCharactersPerLine")
    public Number getAverageNumberOfCharactersPerLine() {
        return averageNumberOfCharactersPerLine;
    }

    public void setAverageNumberOfCharactersPerLine(Number averageNumberOfCharactersPerLine) {
        this.averageNumberOfCharactersPerLine = averageNumberOfCharactersPerLine;
    }
    
    private Number numberOfLinesOfText;
    
    @FameProperty(name = "numberOfLinesOfText")
    public Number getNumberOfLinesOfText() {
        return numberOfLinesOfText;
    }

    public void setNumberOfLinesOfText(Number numberOfLinesOfText) {
        this.numberOfLinesOfText = numberOfLinesOfText;
    }
    
    private Number numberOfExternalClones;
    
    @FameProperty(name = "numberOfExternalClones")
    public Number getNumberOfExternalClones() {
        return numberOfExternalClones;
    }

    public void setNumberOfExternalClones(Number numberOfExternalClones) {
        this.numberOfExternalClones = numberOfExternalClones;
    }
    
    private Number numberOfInternalMultiplications;
    
    @FameProperty(name = "numberOfInternalMultiplications")
    public Number getNumberOfInternalMultiplications() {
        return numberOfInternalMultiplications;
    }

    public void setNumberOfInternalMultiplications(Number numberOfInternalMultiplications) {
        this.numberOfInternalMultiplications = numberOfInternalMultiplications;
    }
    
    @FameProperty(name = "numberOfExternalDuplications", derived = true)
    public Number getNumberOfExternalDuplications() {
        // TODO: this is a derived property, implement this method manually.
        throw new UnsupportedOperationException("Not yet implemented!");  
    }
    
    private Number numberOfBytes;
    
    @FameProperty(name = "numberOfBytes")
    public Number getNumberOfBytes() {
        return numberOfBytes;
    }

    public void setNumberOfBytes(Number numberOfBytes) {
        this.numberOfBytes = numberOfBytes;
    }
    


}

