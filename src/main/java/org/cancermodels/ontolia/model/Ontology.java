package org.cancermodels.ontolia.model;

import java.util.HashMap;
import java.util.Map;


public class Ontology {

    private Map<String, TreatmentOntologyTerm> treatmentTerms;
    private Map<String, RegimenOntologyTerm> regimenTerms;

    public Ontology() {
        treatmentTerms = new HashMap<>();
        regimenTerms = new HashMap<>();
    }

    public void addTreatmentTerm(TreatmentOntologyTerm term){
        treatmentTerms.put(term.getId(), term);
    }

    public void addRegimenTerm(RegimenOntologyTerm term){
        regimenTerms.put(term.getId(), term);
    }
}
