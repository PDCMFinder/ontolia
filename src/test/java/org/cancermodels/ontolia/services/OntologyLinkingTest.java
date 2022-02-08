package org.cancermodels.ontolia.services;

import org.cancermodels.ontolia.model.Ontology;
import org.cancermodels.ontolia.model.RegimenOntologyTerm;
import org.cancermodels.ontolia.model.TreatmentOntologyTerm;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.*;

public class OntologyLinkingTest {


    @Test
    public void given_Ontology_when_initTermMaps_then_treatmentMapsPopulated(){
        OntologyLinking ontologyLinking = new OntologyLinking(getTestOntology());
        ontologyLinking.initTermMaps();
        Assert.assertEquals(3, ontologyLinking.getTreatmentTermLabelsMap().size());
        Assert.assertEquals(6, ontologyLinking.getTreatmentTermSynonymsMap().size());
    }

    @Test
    public void given_regimenObject_when_linkRegimenToTreatments_then_regimenIsLinked(){
        Ontology ontology = getTestOntology();
        OntologyLinking ontologyLinking = new OntologyLinking(ontology);
        ontologyLinking.initTermMaps();
        ontologyLinking.linkRegimenToTreatments(ontology.getRegimenTerms().get("NCIT_4"));
        ontologyLinking.linkRegimenToTreatments(ontology.getRegimenTerms().get("NCIT_5"));
        Assert.assertEquals(2, ontology.getRegimenTerms().get("NCIT_4").getLinkedTreatmentTerms().size());
        Assert.assertEquals(0, ontology.getRegimenTerms().get("NCIT_5").getLinkedTreatmentTerms().size());
    }

    @Test
    public void given_regimenObject_when_link_then_regimensAreLinked(){
        Ontology ontology = getTestOntology();
        OntologyLinking ontologyLinking = new OntologyLinking(ontology);
        ontologyLinking.link();
        Assert.assertEquals(2, ontology.getRegimenTerms().get("NCIT_4").getLinkedTreatmentTerms().size());
        Assert.assertEquals(0, ontology.getRegimenTerms().get("NCIT_5").getLinkedTreatmentTerms().size());
    }


    private Ontology getTestOntology(){
        Ontology ontology = new Ontology();
        ontology.addTreatmentTerm(new TreatmentOntologyTerm("NCIT_1", "url", "label1", new HashSet<>(Arrays.asList("label1syn1", "label1syn2"))));
        ontology.addTreatmentTerm(new TreatmentOntologyTerm("NCIT_2", "url", "label2", new HashSet<>(Arrays.asList("label2syn1", "label2syn2"))));
        ontology.addTreatmentTerm(new TreatmentOntologyTerm("NCIT_3", "url", "label3", new HashSet<>(Arrays.asList("label3syn1", "label3syn2"))));

        ontology.addRegimenTerm(new RegimenOntologyTerm("NCIT_4", "url", "label4", new HashSet<>(Arrays.asList("label1/label2")), new ArrayList<>()));
        ontology.addRegimenTerm(new RegimenOntologyTerm("NCIT_5", "url", "label5", new HashSet<>(Arrays.asList("nonexisting1/nonexisting2")), new ArrayList<>()));

        return ontology;
    }
}
