package org.cancermodels.ontolia.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Ontology {

    private Map<String, TreatmentOntologyTerm> treatmentTerms;
    private Map<String, RegimenOntologyTerm> regimenTerms;
    private int linkedRegimenCounter;
    private List<String> unlinkedRegimensWithReason;

    public Ontology() {
        treatmentTerms = new HashMap<>();
        regimenTerms = new HashMap<>();
        linkedRegimenCounter = 0;
        unlinkedRegimensWithReason = new ArrayList<>();
    }

    public void addTreatmentTerm(TreatmentOntologyTerm term){
        treatmentTerms.put(term.getId(), term);
    }

    public void addRegimenTerm(RegimenOntologyTerm term){
        regimenTerms.put(term.getId(), term);
    }

    public Map<String, TreatmentOntologyTerm> getTreatmentTerms() {
        return treatmentTerms;
    }

    public Map<String, RegimenOntologyTerm> getRegimenTerms() {
        return regimenTerms;
    }

    public int getLinkedRegimenCounter() {
        return linkedRegimenCounter;
    }

    public void increaseLinkedRegimenCounter(){
        this.linkedRegimenCounter += 1;
    }

    public List<String> getUnlinkedRegimensWithReason() {
        return unlinkedRegimensWithReason;
    }

    public List<String> getOntoliaOutputList(){
        List<String> outputList = new ArrayList<>();
        for(Map.Entry<String, RegimenOntologyTerm> entry:this.getRegimenTerms().entrySet()){
            RegimenOntologyTerm regimen = entry.getValue();
            if(!regimen.getLinkedTreatmentTerms().isEmpty()){
                outputList.add(regimen.getRegimenLinkString());
            }
        }
        return outputList;
    }
}
