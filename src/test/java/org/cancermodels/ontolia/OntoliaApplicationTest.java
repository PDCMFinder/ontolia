package org.cancermodels.ontolia;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class OntoliaApplicationTest {

    @Test
    void Given_dirIsPassed_ThenReturnDirString() {
        String expectedDir = "/path/to/data";
        String[] args = {String.format("--dir=%s", expectedDir)};
        Assert.assertEquals(expectedDir, OntoliaCommandLineRunner.getOutputDirectory(args));
    }

}
