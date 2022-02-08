package org.cancermodels.ontolia.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class OntologyTest {


    @Test
    void given_Ontology_when_getOntoliaOutputList_then_correctListIsCreated(){
        Ontology ontology = getTestOntology();
        Assert.assertEquals("id1=id2,id3",ontology.getOntoliaOutputList().get(0));
    }

    private Ontology getTestOntology(){
        Ontology ontology = new Ontology();
        List<TreatmentOntologyTerm> treatmentOntologyTermList = new ArrayList<>();
        treatmentOntologyTermList.add(new TreatmentOntologyTerm("id2", "url2", "label2", new HashSet<>()));
        treatmentOntologyTermList.add(new TreatmentOntologyTerm("id3", "url3", "label3", new HashSet<>()));
        ontology.addRegimenTerm(new RegimenOntologyTerm("id1", "url1", "label1", new HashSet<>(),treatmentOntologyTermList ));
        return ontology;
    }
}
