package org.cancermodels.ontolia.model;

import java.util.List;
import java.util.Set;

public class RegimenOntologyTerm extends OntologyTerm{

    private List<TreatmentOntologyTerm> linkedTreatmentTerms;

    public RegimenOntologyTerm(String id, String iri, String label, Set<String> synonyms, List<TreatmentOntologyTerm> linkedTreatmentTerms) {
        super(id, iri, label, synonyms);
        this.linkedTreatmentTerms = linkedTreatmentTerms;
    }

    public List<TreatmentOntologyTerm> getLinkedTreatmentTerms() {
        return linkedTreatmentTerms;
    }

    public void setLinkedTreatmentTerms(List<TreatmentOntologyTerm> linkedTreatmentTerms) {
        this.linkedTreatmentTerms = linkedTreatmentTerms;
    }
}
