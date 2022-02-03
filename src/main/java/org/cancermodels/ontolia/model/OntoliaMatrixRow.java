package org.cancermodels.ontolia.model;

import java.util.ArrayList;
import java.util.List;

public class OntoliaMatrixRow {

    private String[] synonyms;

    private List<TreatmentOntologyTerm> matchedTerms;
    private List<String> matchedTermsFoundIn;

    private int matchScore;
    private boolean completeMatch;

    public OntoliaMatrixRow(String[] synonyms) {

        this.synonyms = synonyms;
        matchedTerms = new ArrayList<>();
        matchedTermsFoundIn = new ArrayList<>();
        matchScore = 0;
        completeMatch = false;
    }


    /**
     *
     * [existingOntologyLabel]
     * (existingOntologySynonym)
     * |not found|
     *
     * @return
     */
    public String getRowString(){

        StringBuilder s = new StringBuilder("");
        for(int i = 0; i < synonyms.length; i++){

            if(matchedTerms.get(i) != null){
                if(matchedTermsFoundIn.get(i).equals("label")){
                    s.append("["+matchedTerms.get(i).getLabel()+"] ");
                }
                else {
                    s.append("("+matchedTerms.get(i).getLabel()+") ");
                }
            }
            else{

                s.append("|"+ synonyms[i]+"| ");
            }
        }
        s.append("Score: "+matchScore);
        return s.toString();
    }

    public void calculateScore(){
        for(int i=0; i < matchedTerms.size(); i++){
            if(matchedTerms.get(i) != null){
                if(matchedTermsFoundIn.get(i).equals("synonym")) matchScore +=10;
                if(matchedTermsFoundIn.get(i).equals("label")) matchScore +=100;
            }
            else{
                matchScore +=1;
            }
        }
    }

    public List<TreatmentOntologyTerm> getMatchedTerms() {
        return matchedTerms;
    }

    public String[] getSynonyms() {
        return synonyms;
    }

    public List<String> getMatchedTermsFoundIn() {
        return matchedTermsFoundIn;
    }

    public int getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(int matchScore) {
        this.matchScore = matchScore;
    }

    public boolean isCompleteMatch() {
        return completeMatch;
    }

    public void setCompleteMatch(boolean completeMatch) {
        this.completeMatch = completeMatch;
    }
}
