package org.cancermodels.ontolia.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class OntoliaMatrixRowTest {

    @Test
    void given_MatrixRow_when_GetRowStringIsCalled_then_RowStringIsCorrect(){
        Assert.assertEquals("[Term1] [Term2] Score: 200", getTestRow1().getRowString());
        Assert.assertEquals("[Term1] (Term2) (Term3) Score: 120", getTestRow2().getRowString());
        Assert.assertEquals("[Term1] (Term2) |NonExistingTerm3| Score: 111", getTestRow3().getRowString());
    }


    private OntoliaMatrixRow getTestRow1(){
        String[] synonyms = new String[]{"Term1", "Term2"};
        TreatmentOntologyTerm term1 = new TreatmentOntologyTerm("NCIT_1", "url1", "Term1", new HashSet<>());
        TreatmentOntologyTerm term2 = new TreatmentOntologyTerm("NCIT_2", "url2", "Term2", new HashSet<>());
        OntoliaMatrixRow row = new OntoliaMatrixRow(synonyms);
        row.getMatchedTerms().add(term1);
        row.getMatchedTerms().add(term2);
        row.getMatchedTermsFoundIn().add("label");
        row.getMatchedTermsFoundIn().add("label");
        row.calculateScore();
        return row;
    }

    private OntoliaMatrixRow getTestRow2(){
        String[] synonyms = new String[]{"Term1", "Term2", "Term3"};
        TreatmentOntologyTerm term1 = new TreatmentOntologyTerm("NCIT_1", "url1", "Term1", new HashSet<>());
        TreatmentOntologyTerm term2 = new TreatmentOntologyTerm("NCIT_2", "url2", "Term2", new HashSet<>());
        TreatmentOntologyTerm term3 = new TreatmentOntologyTerm("NCIT_3", "url3", "Term3", new HashSet<>());
        OntoliaMatrixRow row = new OntoliaMatrixRow(synonyms);
        row.getMatchedTerms().add(term1);
        row.getMatchedTerms().add(term2);
        row.getMatchedTerms().add(term3);
        row.getMatchedTermsFoundIn().add("label");
        row.getMatchedTermsFoundIn().add("synonym");
        row.getMatchedTermsFoundIn().add("synonym");
        row.calculateScore();
        return row;
    }

    private OntoliaMatrixRow getTestRow3(){
        String[] synonyms = new String[]{"Term1", "Term2", "NonExistingTerm3"};
        TreatmentOntologyTerm term1 = new TreatmentOntologyTerm("NCIT_1", "url1", "Term1", new HashSet<>());
        TreatmentOntologyTerm term2 = new TreatmentOntologyTerm("NCIT_2", "url2", "Term2", new HashSet<>());
        TreatmentOntologyTerm term3 = null;
        OntoliaMatrixRow row = new OntoliaMatrixRow(synonyms);
        row.getMatchedTerms().add(term1);
        row.getMatchedTerms().add(term2);
        row.getMatchedTerms().add(term3);
        row.getMatchedTermsFoundIn().add("label");
        row.getMatchedTermsFoundIn().add("synonym");
        row.getMatchedTermsFoundIn().add("");
        row.calculateScore();
        return row;
    }
}
