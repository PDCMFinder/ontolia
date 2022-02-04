package org.cancermodels.ontolia.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RegimenOntologyTerm extends OntologyTerm{

    private List<TreatmentOntologyTerm> linkedTreatmentTerms;

    public RegimenOntologyTerm(String id, String iri, String label, Set<String> synonyms, List<TreatmentOntologyTerm> linkedTreatmentTerms) {
        super(id, iri, label, synonyms);
        this.linkedTreatmentTerms = linkedTreatmentTerms;
    }

    public List<TreatmentOntologyTerm> getLinkedTreatmentTerms() {
        return linkedTreatmentTerms;
    }

    public String getRegimenLinkString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.getId());
        sb.append("=");
        String linkedIds = linkedTreatmentTerms.stream().map(TreatmentOntologyTerm::getId)
                .collect(Collectors.joining(","));
        sb.append(linkedIds);
        return sb.toString();
    }
}
