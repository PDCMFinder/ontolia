package org.cancermodels.ontolia;

import org.cancermodels.ontolia.model.OntoliaMatrix;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class OntoliaMatrixTest {




    @Test
    public void given_LabelString_then_LabelStringIsCleaned(){
        OntoliaMatrix ontoliaMatrix = getTestMatrix();
        String label1 = "regimen high dose low-dose low dose FOLFOX";
        Assert.assertEquals("folfox", ontoliaMatrix.getCleanLabel(label1));
    }


    private OntoliaMatrix getTestMatrix(){

        OntoliaMatrix om = new OntoliaMatrix("/", new HashMap<>(), new HashMap<>());
        return om;



    }

}
