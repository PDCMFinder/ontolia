package org.cancermodels.ontolia.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RegimenOntologyTermTest {


    @Test
    public void given_NewTreatmentTerm_then_ToRegimenLinkStringIsCorrect(){
        Assert.assertEquals(getCorrectToString(), getTestTerm().getRegimenLinkString());

    }


    private RegimenOntologyTerm getTestTerm(){
        TreatmentOntologyTerm term2 = new TreatmentOntologyTerm("NCIT_2", "https://ncit_2", "test2", new HashSet<>());
        TreatmentOntologyTerm term3 = new TreatmentOntologyTerm("NCIT_3", "https://ncit_3", "test3", new HashSet<>());
        List<TreatmentOntologyTerm> linkedTerms = new ArrayList<>();
        linkedTerms.add(term2);
        linkedTerms.add(term3);
        RegimenOntologyTerm term1 = new RegimenOntologyTerm("NCIT_1", "https://ncit_1", "test1", new HashSet<>(), null);
        term1.setLinkedTreatmentTerms(linkedTerms);
        return term1;
    }

    private String getCorrectToString(){
        return "NCIT_1=NCIT_2,NCIT_3";
    }


}
