// Automagically generated code, please do not change
package org.moosetechnology.model.famix;

import ch.akuhn.fame.internal.MultivalueSet;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.FameDescription;
import java.util.*;
import ch.akuhn.fame.FamePackage;


@FamePackage("FAMIX")
@FameDescription("Invocation")
public class Invocation extends Association {



    private Collection<BehaviouralEntity> candidates; 

    @FameProperty(name = "candidates", opposite = "incomingInvocations")
    public Collection<BehaviouralEntity> getCandidates() {
        if (candidates == null) {
            candidates = new MultivalueSet<BehaviouralEntity>() {
                @Override
                protected void clearOpposite(BehaviouralEntity e) {
                    e.getIncomingInvocations().remove(Invocation.this);
                }
                @Override
                protected void setOpposite(BehaviouralEntity e) {
                    e.getIncomingInvocations().add(Invocation.this);
                }
            };
        }
        return candidates;
    }
    
    public void setCandidates(Collection<? extends BehaviouralEntity> candidates) {
        this.getCandidates().clear();
        this.getCandidates().addAll(candidates);
    }
    
    public void addCandidates(BehaviouralEntity one) {
        this.getCandidates().add(one);
    }   
    
    public void addCandidates(BehaviouralEntity one, BehaviouralEntity... many) {
        this.getCandidates().add(one);
        for (BehaviouralEntity each : many)
            this.getCandidates().add(each);
    }   
    
    public void addCandidates(Iterable<? extends BehaviouralEntity> many) {
        for (BehaviouralEntity each : many)
            this.getCandidates().add(each);
    }   
                
    public void addCandidates(BehaviouralEntity[] many) {
        for (BehaviouralEntity each : many)
            this.getCandidates().add(each);
    }
    
    public int numberOfCandidates() {
        return getCandidates().size();
    }

    public boolean hasCandidates() {
        return !getCandidates().isEmpty();
    }
    
                
    private NamedEntity receiver;
    
    @FameProperty(name = "receiver", opposite = "receivingInvocations")
    public NamedEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(NamedEntity receiver) {
        if (this.receiver != null) {
            if (this.receiver.equals(receiver)) return;
            this.receiver.getReceivingInvocations().remove(this);
        }
        this.receiver = receiver;
        if (receiver == null) return;
        receiver.getReceivingInvocations().add(this);
    }
    
    private BehaviouralEntity sender;
    
    @FameProperty(name = "sender", opposite = "outgoingInvocations")
    public BehaviouralEntity getSender() {
        return sender;
    }

    public void setSender(BehaviouralEntity sender) {
        if (this.sender != null) {
            if (this.sender.equals(sender)) return;
            this.sender.getOutgoingInvocations().remove(this);
        }
        this.sender = sender;
        if (sender == null) return;
        sender.getOutgoingInvocations().add(this);
    }
    
    private String signature;
    
    @FameProperty(name = "signature")
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    
    private String receiverSourceCode;
    
    @FameProperty(name = "receiverSourceCode")
    public String getReceiverSourceCode() {
        return receiverSourceCode;
    }

    public void setReceiverSourceCode(String receiverSourceCode) {
        this.receiverSourceCode = receiverSourceCode;
    }
    


}

