package org.cancermodels.ontolia.services;

import org.cancermodels.ontolia.model.RegimenOntologyTerm;
import org.cancermodels.ontolia.model.TreatmentOntologyTerm;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.cancermodels.ontolia.model.Ontology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OntologyReader {

    private static final String ONTOLOGY_ROOT_URL = "https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/";
    private static final String EMBEDDED = "_embedded";
    private static final Logger log = LoggerFactory.getLogger(OntologyReader.class);

    public OntologyReader() {
    }

    public Ontology loadOntology(List<String> treatmentOntologyBranches, List<String> regimenOntologyBranches){

        Ontology ontology = new Ontology();

        for(String branch:treatmentOntologyBranches){
            loadAllTreatmentTermsFromBranch(branch, ontology);
        }

        for(String branch:regimenOntologyBranches){
            loadAllRegimenTermsFromBranch(branch, ontology);
        }

        return ontology;
    }

    public void loadAllTreatmentTermsFromBranch(String branch, Ontology ontology){
        int totalPages = 0;
        log.info("Loading treatment branch: "+branch);
        for (int currentPage = 0; currentPage <= totalPages; currentPage++) {
            JSONObject job = getJsonObject(branch, currentPage);
            parseHierarchicalChildrenTerms(job, ontology, false);
            totalPages = determineTotalPages(job);
        }
    }

    public void loadAllRegimenTermsFromBranch(String branch, Ontology ontology){
        int totalPages = 0;
        log.info("Loading regimen branch: "+branch);
        for (int currentPage = 0; currentPage <= totalPages; currentPage++) {
            JSONObject job = getJsonObject(branch, currentPage);
            parseHierarchicalChildrenTerms(job, ontology, true);
            totalPages = determineTotalPages(job);
        }
    }

    public JSONObject getJsonObject(String branch, int page)  {
        String encodedTermUrl = encodeUrl(branch);
        String url = ONTOLOGY_ROOT_URL + encodedTermUrl + "/hierarchicalDescendants?size=200&page=" + page;
        String json = parseURL(url);
        JSONObject job;
        try {
            job = new JSONObject(json);
        }
        catch (JSONException e){
            log.error("JSONException in "+branch);
            return new JSONObject("{}");
        }
        return job;
    }

    public void parseHierarchicalChildrenTerms(JSONObject job, Ontology ontology, boolean isRegimen){

        try {
            if (!job.has(EMBEDDED)) return;
            JSONObject job2 = job.getJSONObject("_embedded");
            JSONArray hierarchicalChildren = job2.getJSONArray("terms");

            for (int i = 0; i < hierarchicalChildren.length(); i++) {
                JSONObject term = hierarchicalChildren.getJSONObject(i);
                if(isRegimen) {
                    RegimenOntologyTerm regimenOntologyTerm = createRegimenOntologyTerm(term);
                    ontology.addRegimenTerm(regimenOntologyTerm);
                }
                else{
                    TreatmentOntologyTerm treatmentOntologyTerm = createTreatmentOntologyTerm(term);
                    ontology.addTreatmentTerm(treatmentOntologyTerm);
                }
            }
        } catch (Exception e) {
            log.error(" {} ", e.getMessage());
        }
    }

    public TreatmentOntologyTerm createTreatmentOntologyTerm(JSONObject term){
        String url = term.getString("iri");
        String termLabel = term.getString("label");
        String id = term.getString("short_form");
        Set<String> synonymsSet = new HashSet<>();
        if (term.has("synonyms")) {
            JSONArray synonyms = term.getJSONArray("synonyms");
            for (int i = 0; i < synonyms.length(); i++) {
                synonymsSet.add(synonyms.getString(i));
            }
        }
        TreatmentOntologyTerm newTerm = new TreatmentOntologyTerm(id, url, termLabel, synonymsSet);
        return newTerm;
    }

    public RegimenOntologyTerm createRegimenOntologyTerm(JSONObject term){
        String url = term.getString("iri");
        String termLabel = term.getString("label");
        String id = term.getString("short_form");
        Set<String> synonymsSet = new HashSet<>();
        if (term.has("synonyms")) {
            JSONArray synonyms = term.getJSONArray("synonyms");
            for (int i = 0; i < synonyms.length(); i++) {
                synonymsSet.add(synonyms.getString(i));
            }
        }
        RegimenOntologyTerm newTerm = new RegimenOntologyTerm(id, url, termLabel, synonymsSet, new ArrayList<>());
        return newTerm;
    }

    public int determineTotalPages(JSONObject job){
        JSONObject pageObj = job.getJSONObject("page");
        return pageObj.getInt("totalPages") - 1;
    }

    public String encodeUrl(String url){
        String encodedTermUrl = "";
        try {
            //have to double encode the url to get the desired result
            encodedTermUrl = URLEncoder.encode(url, "UTF-8");
            encodedTermUrl = URLEncoder.encode(encodedTermUrl, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedTermUrl;
    }

    public String parseURL(String urlStr) {

        StringBuilder sb = new StringBuilder();

        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        assert url != null;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))){
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }

        } catch (Exception e) {
            log.error("Unable to read from URL " + urlStr, e);
        }
        return sb.toString();
    }
}
