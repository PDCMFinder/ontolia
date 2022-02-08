package org.cancermodels.ontolia.services;

import org.cancermodels.ontolia.model.Ontology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

public class OntoliaFileWriter {

    private String outputDir;
    private Ontology ontology;
    private static final Logger log = LoggerFactory.getLogger(OntoliaFileWriter.class);

    public OntoliaFileWriter(String outputDir, Ontology ontology) {
        this.outputDir = outputDir;
        this.ontology = ontology;
    }

    public void createOutputFile(){
        writeOutputFile(ontology.getOntoliaOutputList());
    }

    public void writeOutputFile(List<String> regimenList){
        String outputFileName = outputDir+"/ontolia_output.txt";
        log.info("Creating ontolia output file at {}", outputFileName);
        try {
            PrintWriter writer = new PrintWriter(outputFileName, "UTF-8");
            for(String s:regimenList){
                writer.println(s);
            }
            writer.close();
        } catch (FileNotFoundException|UnsupportedEncodingException e) {
            log.error("Error when creating file",e);
        }
    }
}
