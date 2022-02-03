package org.cancermodels.ontolia.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OntoliaMatrix {

    String separator;
    List<OntoliaMatrixRow> matrix;
    private boolean matched;

    private Map<String, TreatmentOntologyTerm> termsByLabel;
    private Map<String, TreatmentOntologyTerm> termsBySynonym;


    public OntoliaMatrix(String separator, Map<String, TreatmentOntologyTerm> termsByLabel, Map<String, TreatmentOntologyTerm> termsBySynonym) {
        this.separator = separator;
        this.matrix = new ArrayList<>();

        if(termsByLabel != null){
            this.termsByLabel = termsByLabel;
        }
        else {
            this.termsByLabel = new HashMap<>();
        }

        if(termsBySynonym != null){
            this.termsBySynonym = termsBySynonym;
        }
        else{
            this.termsBySynonym = new HashMap<>();
        }

        this.matched = false;
    }



    public void addMatrixRow(String [] synonyms){

        OntoliaMatrixRow r = new OntoliaMatrixRow(synonyms);

        this.matrix.add(r);
    }


    /**
     * Returns the best set of ontology terms or null if none
     * @return
     */
    public OntoliaMatrixRow getBestCompleteMatch(){

        if(!matched) matchMatrixRows();

        int bestScore = 0;
        OntoliaMatrixRow perfectMatch = null;

        for(OntoliaMatrixRow omr: matrix){

            if(omr.isCompleteMatch() && omr.getMatchScore() > bestScore){
                perfectMatch = omr;
                bestScore = omr.getMatchScore();
            }
        }

        return perfectMatch;
    }

    /**
     * Returns the best set of ontology terms or null if none
     * @return
     */
    public OntoliaMatrixRow getBestMatch(){

        if(!matched) matchMatrixRows();

        int bestScore = -1;
        OntoliaMatrixRow bestMatch = null;

        for(OntoliaMatrixRow omr: matrix){

            if(omr.getMatchScore() > bestScore){
                bestMatch = omr;
                bestScore = omr.getMatchScore();
            }
        }

        return bestMatch;
    }


    /**
     * Links existing ontology terms to the elements in the matrix rows
     */
    public void matchMatrixRows(){

        for(OntoliaMatrixRow row : matrix){

            String[] synonyms = row.getSynonyms();
            boolean completeMatch = true;

            for(int i = 0; i < synonyms.length; i++){
                String label = getCleanLabel(synonyms[i]);

                if(termsByLabel.containsKey(label)){
                    row.getMatchedTerms().add(termsByLabel.get(label));
                    row.getMatchedTermsFoundIn().add("label");
                }
                else if(termsBySynonym.containsKey(label)){
                    row.getMatchedTerms().add(termsBySynonym.get(label));
                    row.getMatchedTermsFoundIn().add("synonym");
                }
                else{
                    row.getMatchedTerms().add(null);
                    row.getMatchedTermsFoundIn().add(null);
                    completeMatch = false;
                }
            }
            row.calculateScore();
            row.setCompleteMatch(completeMatch);
        }

        this.matched = true;
    }


    public String getCleanLabel(String label){

        String cleanLabel = label.toLowerCase();
        cleanLabel = cleanLabel.replace("regimen", "");
        cleanLabel = cleanLabel.replace("high dose", "");
        cleanLabel = cleanLabel.replace("low-dose", "");
        cleanLabel = cleanLabel.replace("low dose", "");
        cleanLabel = cleanLabel.replace("dose-dense", "");
        cleanLabel = cleanLabel.replace("high-dose", "");
        cleanLabel = cleanLabel.replace("pulse intense", "");
        cleanLabel = cleanLabel.replace("intravenous", "");
        cleanLabel = cleanLabel.replace("oral", "");
        cleanLabel = cleanLabel.replace("modified", "");
        cleanLabel = cleanLabel.replace("hyperfractionated", "");
        cleanLabel = cleanLabel.replace("infusional", "");

        cleanLabel = cleanLabel.replace("([^\\s]+\\s+cancer)", "");

        return cleanLabel.trim();
    }

    public List<OntoliaMatrixRow> getMatrix() {
        return matrix;
    }
}
