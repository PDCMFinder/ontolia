package org.cancermodels.ontolia.model;

import java.util.Set;

abstract class OntologyTerm {

    private String id;
    private String iri;
    private String label;
    private Set<String> synonyms;

    protected OntologyTerm(String id, String iri, String label, Set<String> synonyms) {
        this.id = id;
        this.iri = iri;
        this.label = label;
        this.synonyms = synonyms;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Set<String> getSynonyms() {
        return synonyms;
    }


    @Override
    public String toString() {
        return "OntologyTerm{" +
                "id='" + id + '\'' +
                ", iri='" + iri + '\'' +
                ", label='" + label + '\'' +
                ", synonyms=" + synonyms +
                '}';
    }
}
