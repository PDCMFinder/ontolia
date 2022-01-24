package org.cancermodels.ontolia;

import org.cancermodels.ontolia.config.OntologyBranchDefinitions;
import org.cancermodels.ontolia.model.Ontology;
import org.cancermodels.ontolia.services.OntologyLinking;
import org.cancermodels.ontolia.services.OntologyReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OntoliaCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running.");

        OntologyReader ontologyReader = new OntologyReader();
        OntologyBranchDefinitions ontologyBranchDefinitions = new OntologyBranchDefinitions();
        Ontology ontology = ontologyReader.loadOntology(ontologyBranchDefinitions.getTreatmentBranches(),
                                                        ontologyBranchDefinitions.getRegimenBranches());
        System.out.println("Treatment terms loaded:"+ontology.getTreatmentTerms().size());
        System.out.println("Regimen terms loaded:"+ontology.getRegimenTerms().size());
        OntologyLinking ontologyLinking = new OntologyLinking(ontology);
        ontologyLinking.link();
        //TODO: create linked regimens file here
    }
}
