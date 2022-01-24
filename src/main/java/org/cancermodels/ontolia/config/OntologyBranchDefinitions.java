package org.cancermodels.ontolia.config;

import java.util.ArrayList;
import java.util.List;

public class OntologyBranchDefinitions {

    private static final String chemicalModifierBranchUrl = "http://purl.obolibrary.org/obo/NCIT_C1932";
    private static final String dietarySupplementBranchUrl = "http://purl.obolibrary.org/obo/NCIT_C1505";
    private static final String drugOrChemByStructBranchUrl = "http://purl.obolibrary.org/obo/NCIT_C1913";
    private static final String industrialAidBranchUrl = "http://purl.obolibrary.org/obo/NCIT_C45678";
    private static final String pharmaSubstanceBranchUrl = "http://purl.obolibrary.org/obo/NCIT_C1909";
    private static final String physiologyBranchUrl = "http://purl.obolibrary.org/obo/NCIT_C1899";
    private static final String hematopoieticBranchUrl = "http://purl.obolibrary.org/obo/NCIT_C15431";
    private static final String therapeuticProceduresBranchUrl = "http://purl.obolibrary.org/obo/NCIT_C49236";
    private static final String clinicalStudyBranchUrl = "http://purl.obolibrary.org/obo/NCIT_C15206";
    private static final String geneProductBranchUrl = "http://purl.obolibrary.org/obo/NCIT_C26548";


    private static final String regimenBranchUrl = "http://purl.obolibrary.org/obo/NCIT_C12218";
    private static final String customRegimenBranchUrl = "http://purl.obolibrary.org/obo/NCIT_C11197";



    public List<String> getTreatmentBranches(){
        List<String> branches = new ArrayList<>();
        branches.add(chemicalModifierBranchUrl);
        branches.add(dietarySupplementBranchUrl);
        branches.add(drugOrChemByStructBranchUrl);
        branches.add(industrialAidBranchUrl);
        branches.add(pharmaSubstanceBranchUrl);
        branches.add(physiologyBranchUrl);
        branches.add(hematopoieticBranchUrl);
        branches.add(therapeuticProceduresBranchUrl);
        branches.add(clinicalStudyBranchUrl);
        branches.add(geneProductBranchUrl);
        return branches;
    }

    public List<String> getRegimenBranches(){
        List<String> branches = new ArrayList<>();
        branches.add(regimenBranchUrl);
        branches.add(customRegimenBranchUrl);
        return branches;
    }

}
