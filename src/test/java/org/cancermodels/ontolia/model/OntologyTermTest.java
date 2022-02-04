package org.cancermodels.ontolia.model;

import org.cancermodels.ontolia.model.TreatmentOntologyTerm;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class OntologyTermTest {

    @Test
    void given_NewTreatmentTerm_then_ToStringIsCorrect(){
        Assert.assertEquals(getCorrectToString(), getTestTreatmentTerm().toString());

    }


    private TreatmentOntologyTerm getTestTreatmentTerm(){
        return new TreatmentOntologyTerm("NCIT_1", "https://ncit_1", "test", new HashSet<>());
    }

    private String getCorrectToString(){
        return "OntologyTerm{" +
            "id='NCIT_1'" +
                    ", iri='https://ncit_1'" +
                    ", label='test'" +
                    ", synonyms=[]"  +
                    '}';
    }
}
