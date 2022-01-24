package org.cancermodels.ontolia.model;

import java.util.Set;

abstract class OntologyTerm {

    private String id;
    private String iri;
    private String label;
    private Set<String> synonyms;

    public OntologyTerm() {
    }

    public OntologyTerm(String id, String iri, String label, Set<String> synonyms) {
        this.id = id;
        this.iri = iri;
        this.label = label;
        this.synonyms = synonyms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIri() {
        return iri;
    }

    public void setIri(String iri) {
        this.iri = iri;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Set<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(Set<String> synonyms) {
        this.synonyms = synonyms;
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
