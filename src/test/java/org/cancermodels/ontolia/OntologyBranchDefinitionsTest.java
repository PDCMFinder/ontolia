package org.cancermodels.ontolia;

import org.cancermodels.ontolia.config.OntologyBranchDefinitions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OntologyBranchDefinitionsTest {


    @Test
    public void given_BranchDefinitions_then_CheckNumberOfDefs(){
        OntologyBranchDefinitions ontologyBranchDefinitions = new OntologyBranchDefinitions();
        Assert.assertEquals(10, ontologyBranchDefinitions.getTreatmentBranches().size());
        Assert.assertEquals(2, ontologyBranchDefinitions.getRegimenBranches().size());
    }
}
