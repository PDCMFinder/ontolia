package org.cancermodels.ontolia.services;

import org.cancermodels.ontolia.model.Ontology;
import org.cancermodels.ontolia.model.RegimenOntologyTerm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class OntoliaFileWriter {

    private String outputDir;
    private Ontology ontology;
    private static final Logger log = LoggerFactory.getLogger(OntoliaFileWriter.class);

    public OntoliaFileWriter(String outputDir, Ontology ontology) {
        this.outputDir = outputDir;
        this.ontology = ontology;
    }

    public void createOutputFile(){
        String outputFileName = outputDir+"/ontolia_output.txt";
        log.info("Creating ontolia output file at {}", outputFileName);
        try {
            PrintWriter writer = new PrintWriter(outputFileName, "UTF-8");
            for(Map.Entry<String, RegimenOntologyTerm> entry:ontology.getRegimenTerms().entrySet()){
                RegimenOntologyTerm regimen = entry.getValue();
                if(!regimen.getLinkedTreatmentTerms().isEmpty()){
                    writer.println(regimen.getRegimenLinkString());
                }
            }
            writer.close();

        } catch (FileNotFoundException|UnsupportedEncodingException e) {
            log.error("Error when creating file",e);
        }
    }
}
