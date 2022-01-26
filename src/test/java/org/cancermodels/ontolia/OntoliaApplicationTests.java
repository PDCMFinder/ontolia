package org.cancermodels.ontolia;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OntoliaApplicationTests {

    @Test
    public void Given_dirIsPassed_ThenReturnDirString() {
        String expectedDir = "/path/to/data";
        String[] args = {String.format("--dir=%s", expectedDir)};
        Assert.assertEquals(expectedDir, OntoliaCommandLineRunner.getOutputDirectory(args));
    }

}
