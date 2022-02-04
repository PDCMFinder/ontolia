package org.cancermodels.ontolia.config;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class OntologyBranchDefinitionsTest {

    @Test
    void given_BranchDefinitions_then_CheckNumberOfDefs(){
        OntologyBranchDefinitions ontologyBranchDefinitions = new OntologyBranchDefinitions();
        Assert.assertEquals(10, ontologyBranchDefinitions.getTreatmentBranches().size());
        Assert.assertEquals(2, ontologyBranchDefinitions.getRegimenBranches().size());
    }
}
