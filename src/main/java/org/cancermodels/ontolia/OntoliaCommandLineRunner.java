package org.cancermodels.ontolia;

import org.apache.commons.cli.*;
import org.cancermodels.ontolia.config.OntologyBranchDefinitions;
import org.cancermodels.ontolia.model.Ontology;
import org.cancermodels.ontolia.services.OntologyLinking;
import org.cancermodels.ontolia.services.OntologyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OntoliaCommandLineRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(OntoliaCommandLineRunner.class);
    private static CommandLineParser parser = new DefaultParser();
    private static final Options options =
            new Options()
                    .addOption(
                            "d",
                            "dir",
                            true,
                            "Defines an output directory in which the ontolia file will be dumped into.");

    @Override
    public void run(String... args) throws Exception {

        String outputDir = getOutputDirectory(args);
        if(!outputDir.equals("")){
            runOntolia(outputDir);
        }

    }

    public void runOntolia(String outputDir){

        OntologyReader ontologyReader = new OntologyReader();
        OntologyBranchDefinitions ontologyBranchDefinitions = new OntologyBranchDefinitions();
        Ontology ontology = ontologyReader.loadOntology(ontologyBranchDefinitions.getTreatmentBranches(),
                ontologyBranchDefinitions.getRegimenBranches());
        log.info("Treatment terms loaded: {}", ontology.getTreatmentTerms().size());
        log.info("Regimen terms loaded: {}", ontology.getRegimenTerms().size());
        OntologyLinking ontologyLinking = new OntologyLinking(ontology);
        ontologyLinking.link();
        //TODO: create linked regimens file here
    }



    public static String getOutputDirectory(String[] args) {
        String directory = "";
        try {
            CommandLine line = parser.parse(options, args);
            boolean hasDir = line.hasOption("dir");
            if (hasDir) {
                directory = (String) line.getParsedOptionValue("dir");
            } else {
                log.error("Output data path required. Use --dir=/path/to/outputdir");
            }
        } catch (ParseException exp) {
            log.error(String.format("%s%s", "Parsing failed.  Reason: ", exp.getMessage()));
        }
        return directory;
    }
}
