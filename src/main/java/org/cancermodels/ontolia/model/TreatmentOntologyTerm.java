package org.cancermodels.ontolia.model;

import java.util.Set;

public class TreatmentOntologyTerm extends OntologyTerm{

    public TreatmentOntologyTerm(String id, String iri, String label, Set<String> synonyms) {
        super(id, iri, label, synonyms);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
