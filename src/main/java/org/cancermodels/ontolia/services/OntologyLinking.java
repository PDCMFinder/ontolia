package org.cancermodels.ontolia.services;

import org.cancermodels.ontolia.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class OntologyLinking {

    private Ontology ontology;
    private Map<String, TreatmentOntologyTerm> treatmentTermLabelsMap;
    private Map<String, TreatmentOntologyTerm> treatmentTermSynonymsMap;
    private static final Logger log = LoggerFactory.getLogger(OntologyLinking.class);

    public OntologyLinking(Ontology ontology) {
        this.ontology = ontology;
    }

    public void link(){
        log.info("Linking treatment terms to regimens.");
        initTermMaps();

        for(Map.Entry<String, RegimenOntologyTerm> entry:ontology.getRegimenTerms().entrySet()){
            RegimenOntologyTerm regimen = entry.getValue();
            linkRegimenToTreatments(regimen);
        }
        log.info("Linked regimens: {}",ontology.getLinkedRegimenCounter());
        log.info("Linking treatment terms to regimens. DONE");

    }

    public void initTermMaps(){
        treatmentTermSynonymsMap = new HashMap<>();
        treatmentTermLabelsMap = new HashMap<>();
        for(Map.Entry<String, TreatmentOntologyTerm> entry:ontology.getTreatmentTerms().entrySet()){
            TreatmentOntologyTerm term = entry.getValue();
            try {
                treatmentTermLabelsMap.put(term.getLabel().toLowerCase(), term);
                for (String synonym : term.getSynonyms()) {
                    treatmentTermSynonymsMap.put(synonym.toLowerCase(), term);
                }
            }catch (Exception e){
                log.error("Exception with {}",term);
            }
        }
    }


    public void linkRegimenToTreatments(RegimenOntologyTerm regimen) {

        Set<String> synonyms = regimen.getSynonyms();
        OntoliaMatrix slashOntoliaMatrix = new OntoliaMatrix("/", treatmentTermLabelsMap, treatmentTermSynonymsMap);
        OntoliaMatrix dashOntoliaMatrix = new OntoliaMatrix("-", treatmentTermLabelsMap, treatmentTermSynonymsMap);

        for (String synonym : synonyms) {

            String[] synSlashPre = synonym.split("/");
            String[] synDash = synonym.split("-");
            String[] synSlash = splitCombos(synSlashPre);
            slashOntoliaMatrix.addMatrixRow(synSlash);
            dashOntoliaMatrix.addMatrixRow(synDash);
        }

        OntoliaMatrixRow slashOMR = slashOntoliaMatrix.getBestCompleteMatch();
        OntoliaMatrixRow dashOMR = dashOntoliaMatrix.getBestCompleteMatch();
        OntoliaMatrixRow completeMatch = null;

        if (slashOMR != null && dashOMR != null && slashOMR.getMatchScore() >= dashOMR.getMatchScore())
            completeMatch = slashOMR;
        if (slashOMR != null && dashOMR != null && slashOMR.getMatchScore() < dashOMR.getMatchScore())
            completeMatch = dashOMR;
        if (slashOMR == null && dashOMR != null) completeMatch = dashOMR;
        if (slashOMR != null && dashOMR == null) completeMatch = slashOMR;

        if (completeMatch != null) {
            regimen.setLinkedTreatmentTerms(completeMatch.getMatchedTerms());
            ontology.increaseLinkedRegimenCounter();
        } else {
            //we couldn't match all synonyms to a term
            String matchString = getBestMatchString(slashOntoliaMatrix, dashOntoliaMatrix);
            ontology.getUnlinkedRegimensWithReason().add(regimen.getLabel() + " => " + matchString);
        }

    }

    private String getBestMatchString(OntoliaMatrix slashOntoliaMatrix, OntoliaMatrix dashOntoliaMatrix){

        OntoliaMatrixRow slashOMR = slashOntoliaMatrix.getBestMatch();
        OntoliaMatrixRow dashOMR = dashOntoliaMatrix.getBestMatch();

        OntoliaMatrixRow bestMatch = null;

        if (slashOMR != null && dashOMR != null && slashOMR.getMatchScore() >= dashOMR.getMatchScore())
            bestMatch = slashOMR;
        if (slashOMR != null && dashOMR != null && slashOMR.getMatchScore() < dashOMR.getMatchScore())
            bestMatch = dashOMR;
        if (slashOMR == null && dashOMR != null) bestMatch = dashOMR;
        if (slashOMR != null && dashOMR == null) bestMatch = slashOMR;

        String matchString = "NOT AVAILABLE";

        if (bestMatch != null) {
            matchString = bestMatch.getRowString();
        }
        return matchString;
    }

    private String[] splitCombos(String[] combos) {
        //cyclophosphamide followed by paclitaxel + trastuzumab
        List<String> list = new ArrayList<>();

        for (String c : combos) {
            if (c.toLowerCase().contains("followed by")) {
                String[] followedBy = c.toLowerCase().split("followed by");
                list.add(followedBy[0]);
                if (followedBy[1].contains("+")) {

                    String[] plusDrugs = followedBy[1].split("\\+");
                    list.addAll(Arrays.asList(plusDrugs));

                } else if (followedBy[1].contains("/")) {

                    String[] dashDrugs = followedBy[1].split("/");
                    list.addAll(Arrays.asList(dashDrugs));
                } else {
                    list.add(followedBy[1]);
                }
            } else {
                list.add(c);
            }
        }
        return list.stream().toArray(String[]::new);
    }


    public Map<String, TreatmentOntologyTerm> getTreatmentTermLabelsMap() {
        return treatmentTermLabelsMap;
    }

    public Map<String, TreatmentOntologyTerm> getTreatmentTermSynonymsMap() {
        return treatmentTermSynonymsMap;
    }
}
