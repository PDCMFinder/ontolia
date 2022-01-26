package org.cancermodels.ontolia.config;

import java.util.ArrayList;
import java.util.List;

public class OntologyBranchDefinitions {

    private static final String CHEMICAL_MODIFIER_BRANCH_URL = "http://purl.obolibrary.org/obo/NCIT_C1932";
    private static final String DIETARY_SUPPLEMENT_BRANCH_URL = "http://purl.obolibrary.org/obo/NCIT_C1505";
    private static final String DRUG_OR_CHEM_BY_STRUCT_BRANCH_URL = "http://purl.obolibrary.org/obo/NCIT_C1913";
    private static final String INDUSTRIAL_AID_BRANCH_URL = "http://purl.obolibrary.org/obo/NCIT_C45678";
    private static final String PHARMA_SUBSTANCE_BRANCH_URL = "http://purl.obolibrary.org/obo/NCIT_C1909";
    private static final String PHYSIOLOGY_BRANCH_URL = "http://purl.obolibrary.org/obo/NCIT_C1899";
    private static final String HEMATOPOIETIC_BRANCH_URL = "http://purl.obolibrary.org/obo/NCIT_C15431";
    private static final String THERAPEUTIC_PROCEDURES_BRANCH_URL = "http://purl.obolibrary.org/obo/NCIT_C49236";
    private static final String CLINICAL_STUDY_BRANCH_URL = "http://purl.obolibrary.org/obo/NCIT_C15206";
    private static final String GENE_PRODUCT_BRANCH_URL = "http://purl.obolibrary.org/obo/NCIT_C26548";


    private static final String REGIMEN_BRANCH_URL = "http://purl.obolibrary.org/obo/NCIT_C12218";
    private static final String CUSTOM_REGIMEN_BRANCH_URL = "http://purl.obolibrary.org/obo/NCIT_C11197";



    public List<String> getTreatmentBranches(){
        List<String> branches = new ArrayList<>();
        branches.add(CHEMICAL_MODIFIER_BRANCH_URL);
        branches.add(DIETARY_SUPPLEMENT_BRANCH_URL);
        branches.add(DRUG_OR_CHEM_BY_STRUCT_BRANCH_URL);
        branches.add(INDUSTRIAL_AID_BRANCH_URL);
        branches.add(PHARMA_SUBSTANCE_BRANCH_URL);
        branches.add(PHYSIOLOGY_BRANCH_URL);
        branches.add(HEMATOPOIETIC_BRANCH_URL);
        branches.add(THERAPEUTIC_PROCEDURES_BRANCH_URL);
        branches.add(CLINICAL_STUDY_BRANCH_URL);
        branches.add(GENE_PRODUCT_BRANCH_URL);
        return branches;
    }

    public List<String> getRegimenBranches(){
        List<String> branches = new ArrayList<>();
        branches.add(REGIMEN_BRANCH_URL);
        branches.add(CUSTOM_REGIMEN_BRANCH_URL);
        return branches;
    }

}
