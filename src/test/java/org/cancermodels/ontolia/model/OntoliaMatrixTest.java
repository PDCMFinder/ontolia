package org.cancermodels.ontolia.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class OntoliaMatrixTest {



    @Test
    public void given_OntoliaMatrix_when_GetBestCompleteMatch_then_BestCompleteMatchIsCorrect(){
        OntoliaMatrix testMatrix1 = getTestMatrix1();
        OntoliaMatrix testMatrix2 = getTestMatrix2();
        testMatrix1.matchMatrixRows();
        testMatrix2.matchMatrixRows();
        Assert.assertEquals("[Fluorouracil] [Leucovorin Calcium] [Oxaliplatin] Score: 300", testMatrix1.getBestCompleteMatch().getRowString());
        Assert.assertEquals("[Fluorouracil] [Leucovorin Calcium] (Oxaliplatin) Score: 210", testMatrix2.getBestCompleteMatch().getRowString());
    }

    @Test
    public void given_OntoliaMatrix_when_GetBestMatch_then_BestMatchIsCorrect(){
        OntoliaMatrix testMatrix3 = getTestMatrix3();
        Assert.assertEquals("[Fluorouracil] [Leucovorin Calcium] |NonExistingTerm| Score: 201", testMatrix3.getBestMatch().getRowString());
    }

    @Test
    public void given_LabelString_then_LabelStringIsCleaned(){
        OntoliaMatrix ontoliaMatrix = getTestMatrix1();
        String label1 = "regimen high dose low-dose low dose FOLFOX";
        Assert.assertEquals("folfox", ontoliaMatrix.getCleanLabel(label1));
    }


    private OntoliaMatrix getTestMatrix1(){

        OntoliaMatrix om = new OntoliaMatrix("/", getTermsByLabel(), getTermsBySynonym());
        om.addMatrixRow(new String[]{"FOLFOX regimen"});
        om.addMatrixRow(new String[]{"Fluorouracil","Leucovorin Calcium","Oxaliplatin"});
        om.addMatrixRow(new String[]{"CF","5-FU","L-OHP"});
        return om;
    }

    private OntoliaMatrix getTestMatrix2(){

        OntoliaMatrix om = new OntoliaMatrix("/", getTermsByLabel(), getTermsBySynonym());
        om.addMatrixRow(new String[]{"FOLFOX regimen"});
        om.addMatrixRow(new String[]{"Fluorouracil","Leucovorin Calcium","L-OHP"});
        om.addMatrixRow(new String[]{"CF","5-FU","L-OHP"});
        return om;
    }

    private OntoliaMatrix getTestMatrix3(){

        OntoliaMatrix om = new OntoliaMatrix("/", getTermsByLabel(), getTermsBySynonym());
        om.addMatrixRow(new String[]{"FOLFOX regimen"});
        om.addMatrixRow(new String[]{"Fluorouracil","Leucovorin Calcium","NonExistingTerm"});
        om.addMatrixRow(new String[]{"CF","5-FU","L-OHP"});
        return om;
    }


    private Map<String, TreatmentOntologyTerm> getTermsByLabel(){
        Map<String,TreatmentOntologyTerm> map = new HashMap<>();
        TreatmentOntologyTerm fluorouracil = new TreatmentOntologyTerm("NCIT_C505", "http://purl.obolibrary.org/obo/NCIT_C505", "Fluorouracil", null);
        TreatmentOntologyTerm leuvocorinCalcium = new TreatmentOntologyTerm("NCIT_C607", "http://purl.obolibrary.org/obo/NCIT_C607", "Leucovorin Calcium", null);
        TreatmentOntologyTerm oxaliplatin = new TreatmentOntologyTerm("Oxaliplatin", "http://purl.obolibrary.org/obo/NCIT_C1181", "Oxaliplatin", null);
        map.put("fluorouracil", fluorouracil);
        map.put("leucovorin calcium", leuvocorinCalcium);
        map.put("oxaliplatin", oxaliplatin);
        return map;
    }

    private Map<String, TreatmentOntologyTerm> getTermsBySynonym(){
        Map<String,TreatmentOntologyTerm> map = new HashMap<>();
        TreatmentOntologyTerm fluorouracil = new TreatmentOntologyTerm("NCIT_C505", "http://purl.obolibrary.org/obo/NCIT_C505", "Fluorouracil", null);
        TreatmentOntologyTerm leuvocorinCalcium = new TreatmentOntologyTerm("NCIT_C607", "http://purl.obolibrary.org/obo/NCIT_C607", "Leucovorin Calcium", null);
        TreatmentOntologyTerm oxaliplatin = new TreatmentOntologyTerm("Oxaliplatin", "http://purl.obolibrary.org/obo/NCIT_C1181", "Oxaliplatin", null);
        map.put("cf", fluorouracil);
        map.put("5-fu", leuvocorinCalcium);
        map.put("l-ohp", oxaliplatin);
        return map;
    }

}
