package org.cancermodels.ontolia;

import org.cancermodels.ontolia.model.Ontology;
import org.cancermodels.ontolia.services.OntologyReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OntologyReaderTest {


    OntologyReader ontologyReader = new OntologyReader();
    private static final String URL1 = "http://purl.obolibrary.org/obo/NCIT_C1932";

    private static final String EMBEDDED = "_embedded";
    private static final String SYNONYMS = "synonyms";


    @Test
    public void given_UrlString_when_encodeUrlIsCalled_then_correctStringReturned(){
        Assert.assertEquals("http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C1932", ontologyReader.encodeUrl(URL1));
    }

    @Test
    public void given_JsonString_then_JsonObjectIsCreated(){
        JSONObject job = ontologyReader.createJsonObjectFromString(getExampleApiResponse());
        JSONObject job2 = ontologyReader.createJsonObjectFromString(getExampleInvalidApiResponse());

        Assert.assertEquals(true, job.has("_embedded"));
        Assert.assertEquals(true, job.getJSONObject("_embedded").has("terms"));
        Assert.assertEquals(false, job2.has("_embedded"));
    }

    @Test
    public void given_BranchUrl_when_getApiResponseFromBranchUrl_then_JSONObjectIsCorrect(){
        OntologyReader ontologyReaderMock = mock(OntologyReader.class);
        when(ontologyReaderMock.getApiResponseFromBranchUrl(Mockito.anyString(), Mockito.anyInt())).thenReturn(getBranchJSONObject());
        JSONObject job = ontologyReaderMock.getApiResponseFromBranchUrl("", 0);
        JSONObject job2 = job.getJSONObject(EMBEDDED);
        JSONArray hierarchicalChildren = job2.getJSONArray("terms");
        Assert.assertEquals(6, hierarchicalChildren.length());
    }

    @Test
    public void given_JSONObject_when_parseHierarchicalChildren_then_TermsPopulatedInOntology(){
        Ontology ontology = new Ontology();
        ontologyReader.parseHierarchicalChildrenTerms(getBranchJSONObject(), ontology, false);
        ontologyReader.parseHierarchicalChildrenTerms(getBranchJSONObject(), ontology, true);

        Assert.assertEquals(6, ontology.getTreatmentTerms().size());
        Assert.assertEquals(6, ontology.getRegimenTerms().size());
    }

    @Test
    public void given_JSONObject_when_determineTotalPages_then_correctPageNumberIsReturned(){
        Assert.assertEquals(0, ontologyReader.determineTotalPages(getBranchJSONObject()));
    }

    private JSONObject createJsonObjectFromString(String json){
        JSONObject job;
        try {
            job = new JSONObject(json);
        }
        catch (JSONException e){
            return new JSONObject("{}");
        }
        return job;
    }

    private JSONObject getBranchJSONObject(){
        return createJsonObjectFromString(getBranchDataFromApi());
    }

    private String getExampleInvalidApiResponse(){
        return "{\n" +
                "  \"_embedded\" : {\n" +
                "    \"terms\" : [ {\n" +
                "      \"iri\" : \"http://purl.obolibrary.org/obo/NCIT_C171758\",\n" +
                "      \"label\" : \"Calcium Carbimide\",\n" +
                "      \"description\" : null,\n";
    }

    private String getExampleApiResponse(){
        return "{\n" +
                "  \"_embedded\" : {\n" +
                "    \"terms\" : [ {\n" +
                "      \"iri\" : \"http://purl.obolibrary.org/obo/NCIT_C171758\",\n" +
                "      \"label\" : \"Calcium Carbimide\",\n" +
                "      \"description\" : null,\n" +
                "      \"annotation\" : {\n" +
                "        \"Contributing_Source\" : [ \"FDA\" ],\n" +
                "        \"FDA_UNII_Code\" : [ \"ZLR270912W\" ],\n" +
                "        \"NCI_META_CUI\" : [ \"CL1405745\" ],\n" +
                "        \"Preferred_Name\" : [ \"Calcium Carbimide\" ],\n" +
                "        \"Semantic_Type\" : [ \"Pharmacologic Substance\" ],\n" +
                "        \"code\" : [ \"C171758\" ]\n" +
                "      },\n" +
                "      \"synonyms\" : [ \"Calcium Carbimide\", \"CALCIUM CARBIMIDE\" ],\n" +
                "      \"ontology_name\" : \"ncit\",\n" +
                "      \"ontology_prefix\" : \"NCIT\",\n" +
                "      \"ontology_iri\" : \"http://purl.obolibrary.org/obo/ncit.owl\",\n" +
                "      \"is_obsolete\" : false,\n" +
                "      \"term_replaced_by\" : null,\n" +
                "      \"is_defining_ontology\" : true,\n" +
                "      \"has_children\" : false,\n" +
                "      \"is_root\" : false,\n" +
                "      \"short_form\" : \"NCIT_C171758\",\n" +
                "      \"obo_id\" : \"NCIT:C171758\",\n" +
                "      \"in_subset\" : [ \"NCIT_C63923\" ],\n" +
                "      \"obo_definition_citation\" : null,\n" +
                "      \"obo_xref\" : null,\n" +
                "      \"obo_synonym\" : [{\"name\":\"Calcium Carbimide\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"CALCIUM CARBIMIDE\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}],\n" +
                "      \"is_preferred_root\" : false,\n" +
                "      \"_links\" : {\n" +
                "        \"self\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C171758\"\n" +
                "        },\n" +
                "        \"parents\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C171758/parents\"\n" +
                "        },\n" +
                "        \"ancestors\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C171758/ancestors\"\n" +
                "        },\n" +
                "        \"hierarchicalParents\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C171758/hierarchicalParents\"\n" +
                "        },\n" +
                "        \"hierarchicalAncestors\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C171758/hierarchicalAncestors\"\n" +
                "        },\n" +
                "        \"jstree\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C171758/jstree\"\n" +
                "        },\n" +
                "        \"graph\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C171758/graph\"\n" +
                "        }\n" +
                "      }\n" +
                "    }, {\n" +
                "      \"iri\" : \"http://purl.obolibrary.org/obo/NCIT_C93260\",\n" +
                "      \"label\" : \"Pro-oxidant\",\n" +
                "      \"description\" : null,\n" +
                "      \"annotation\" : {\n" +
                "        \"ALT_DEFINITION\" : [ \"A substance that can produce oxygen byproducts of metabolism that can cause damage to cells.\" ],\n" +
                "        \"NCI_META_CUI\" : [ \"CL425147\" ],\n" +
                "        \"Preferred_Name\" : [ \"Pro-oxidant\" ],\n" +
                "        \"Semantic_Type\" : [ \"Chemical Viewed Functionally\" ],\n" +
                "        \"code\" : [ \"C93260\" ]\n" +
                "      },\n" +
                "      \"synonyms\" : [ \"pro-oxidant\", \"Pro-oxidant\" ],\n" +
                "      \"ontology_name\" : \"ncit\",\n" +
                "      \"ontology_prefix\" : \"NCIT\",\n" +
                "      \"ontology_iri\" : \"http://purl.obolibrary.org/obo/ncit.owl\",\n" +
                "      \"is_obsolete\" : false,\n" +
                "      \"term_replaced_by\" : null,\n" +
                "      \"is_defining_ontology\" : true,\n" +
                "      \"has_children\" : false,\n" +
                "      \"is_root\" : false,\n" +
                "      \"short_form\" : \"NCIT_C93260\",\n" +
                "      \"obo_id\" : \"NCIT:C93260\",\n" +
                "      \"in_subset\" : null,\n" +
                "      \"obo_definition_citation\" : null,\n" +
                "      \"obo_xref\" : null,\n" +
                "      \"obo_synonym\" : [{\"name\":\"Pro-oxidant\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"pro-oxidant\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}],\n" +
                "      \"is_preferred_root\" : false,\n" +
                "      \"_links\" : {\n" +
                "        \"self\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C93260\"\n" +
                "        },\n" +
                "        \"parents\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C93260/parents\"\n" +
                "        },\n" +
                "        \"ancestors\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C93260/ancestors\"\n" +
                "        },\n" +
                "        \"hierarchicalParents\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C93260/hierarchicalParents\"\n" +
                "        },\n" +
                "        \"hierarchicalAncestors\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C93260/hierarchicalAncestors\"\n" +
                "        },\n" +
                "        \"jstree\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C93260/jstree\"\n" +
                "        },\n" +
                "        \"graph\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C93260/graph\"\n" +
                "        }\n" +
                "      }\n" +
                "    }, {\n" +
                "      \"iri\" : \"http://purl.obolibrary.org/obo/NCIT_C170006\",\n" +
                "      \"label\" : \"Foscolic Acid\",\n" +
                "      \"description\" : null,\n" +
                "      \"annotation\" : {\n" +
                "        \"Contributing_Source\" : [ \"FDA\" ],\n" +
                "        \"FDA_UNII_Code\" : [ \"DZJ4QTB7YY\" ],\n" +
                "        \"NCI_META_CUI\" : [ \"CL1382506\" ],\n" +
                "        \"Preferred_Name\" : [ \"Foscolic Acid\" ],\n" +
                "        \"Semantic_Type\" : [ \"Pharmacologic Substance\" ],\n" +
                "        \"code\" : [ \"C170006\" ]\n" +
                "      },\n" +
                "      \"synonyms\" : [ \"FOSCOLIC ACID\", \"Foscolic Acid\" ],\n" +
                "      \"ontology_name\" : \"ncit\",\n" +
                "      \"ontology_prefix\" : \"NCIT\",\n" +
                "      \"ontology_iri\" : \"http://purl.obolibrary.org/obo/ncit.owl\",\n" +
                "      \"is_obsolete\" : false,\n" +
                "      \"term_replaced_by\" : null,\n" +
                "      \"is_defining_ontology\" : true,\n" +
                "      \"has_children\" : false,\n" +
                "      \"is_root\" : false,\n" +
                "      \"short_form\" : \"NCIT_C170006\",\n" +
                "      \"obo_id\" : \"NCIT:C170006\",\n" +
                "      \"in_subset\" : [ \"NCIT_C63923\" ],\n" +
                "      \"obo_definition_citation\" : null,\n" +
                "      \"obo_xref\" : null,\n" +
                "      \"obo_synonym\" : [{\"name\":\"Foscolic Acid\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"FOSCOLIC ACID\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}],\n" +
                "      \"is_preferred_root\" : false,\n" +
                "      \"_links\" : {\n" +
                "        \"self\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C170006\"\n" +
                "        },\n" +
                "        \"parents\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C170006/parents\"\n" +
                "        },\n" +
                "        \"ancestors\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C170006/ancestors\"\n" +
                "        },\n" +
                "        \"hierarchicalParents\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C170006/hierarchicalParents\"\n" +
                "        },\n" +
                "        \"hierarchicalAncestors\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C170006/hierarchicalAncestors\"\n" +
                "        },\n" +
                "        \"jstree\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C170006/jstree\"\n" +
                "        },\n" +
                "        \"graph\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C170006/graph\"\n" +
                "        }\n" +
                "      }\n" +
                "    }, {\n" +
                "      \"iri\" : \"http://purl.obolibrary.org/obo/NCIT_C170160\",\n" +
                "      \"label\" : \"Menfegol\",\n" +
                "      \"description\" : null,\n" +
                "      \"annotation\" : {\n" +
                "        \"Contributing_Source\" : [ \"FDA\" ],\n" +
                "        \"FDA_UNII_Code\" : [ \"FX89Y239GL\" ],\n" +
                "        \"NCI_META_CUI\" : [ \"CL1382634\" ],\n" +
                "        \"Preferred_Name\" : [ \"Menfegol\" ],\n" +
                "        \"Semantic_Type\" : [ \"Pharmacologic Substance\" ],\n" +
                "        \"code\" : [ \"C170160\" ]\n" +
                "      },\n" +
                "      \"synonyms\" : [ \"MENFEGOL\", \"Menfegol\" ],\n" +
                "      \"ontology_name\" : \"ncit\",\n" +
                "      \"ontology_prefix\" : \"NCIT\",\n" +
                "      \"ontology_iri\" : \"http://purl.obolibrary.org/obo/ncit.owl\",\n" +
                "      \"is_obsolete\" : false,\n" +
                "      \"term_replaced_by\" : null,\n" +
                "      \"is_defining_ontology\" : true,\n" +
                "      \"has_children\" : false,\n" +
                "      \"is_root\" : false,\n" +
                "      \"short_form\" : \"NCIT_C170160\",\n" +
                "      \"obo_id\" : \"NCIT:C170160\",\n" +
                "      \"in_subset\" : [ \"NCIT_C63923\" ],\n" +
                "      \"obo_definition_citation\" : null,\n" +
                "      \"obo_xref\" : null,\n" +
                "      \"obo_synonym\" : [{\"name\":\"MENFEGOL\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"Menfegol\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}],\n" +
                "      \"is_preferred_root\" : false,\n" +
                "      \"_links\" : {\n" +
                "        \"self\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C170160\"\n" +
                "        },\n" +
                "        \"parents\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C170160/parents\"\n" +
                "        },\n" +
                "        \"ancestors\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C170160/ancestors\"\n" +
                "        },\n" +
                "        \"hierarchicalParents\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C170160/hierarchicalParents\"\n" +
                "        },\n" +
                "        \"hierarchicalAncestors\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C170160/hierarchicalAncestors\"\n" +
                "        },\n" +
                "        \"jstree\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C170160/jstree\"\n" +
                "        },\n" +
                "        \"graph\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C170160/graph\"\n" +
                "        }\n" +
                "      }\n" +
                "    }, {\n" +
                "      \"iri\" : \"http://purl.obolibrary.org/obo/NCIT_C62651\",\n" +
                "      \"label\" : \"Allergen\",\n" +
                "      \"description\" : [ \"A substance that elicits an allergic reaction.\" ],\n" +
                "      \"annotation\" : {\n" +
                "        \"ALT_DEFINITION\" : [ \"A substance that causes an allergic response. Examples include pollen, molds, and certain foods.\" ],\n" +
                "        \"CHEBI_ID\" : [ \"CHEBI:50904\" ],\n" +
                "        \"Contributing_Source\" : [ \"NICHD\" ],\n" +
                "        \"Legacy Concept Name\" : [ \"Allergen\" ],\n" +
                "        \"Preferred_Name\" : [ \"Allergen\" ],\n" +
                "        \"Semantic_Type\" : [ \"Chemical Viewed Functionally\" ],\n" +
                "        \"UMLS_CUI\" : [ \"C0002092\" ],\n" +
                "        \"code\" : [ \"C62651\" ]\n" +
                "      },\n" +
                "      \"synonyms\" : [ \"allergen\", \"Allergen\" ],\n" +
                "      \"ontology_name\" : \"ncit\",\n" +
                "      \"ontology_prefix\" : \"NCIT\",\n" +
                "      \"ontology_iri\" : \"http://purl.obolibrary.org/obo/ncit.owl\",\n" +
                "      \"is_obsolete\" : false,\n" +
                "      \"term_replaced_by\" : null,\n" +
                "      \"is_defining_ontology\" : true,\n" +
                "      \"has_children\" : false,\n" +
                "      \"is_root\" : false,\n" +
                "      \"short_form\" : \"NCIT_C62651\",\n" +
                "      \"obo_id\" : \"NCIT:C62651\",\n" +
                "      \"in_subset\" : [ \"NCIT_C90259\", \"NCIT_C96388\" ],\n" +
                "      \"obo_definition_citation\" : [{\"definition\":\"A substance that elicits an allergic reaction.\",\"oboXrefs\":[{\"database\":null,\"id\":\"NCI\",\"description\":null,\"url\":null}]}],\n" +
                "      \"obo_xref\" : null,\n" +
                "      \"obo_synonym\" : [{\"name\":\"allergen\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"Allergen\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}],\n" +
                "      \"is_preferred_root\" : false,\n" +
                "      \"_links\" : {\n" +
                "        \"self\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C62651\"\n" +
                "        },\n" +
                "        \"parents\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C62651/parents\"\n" +
                "        },\n" +
                "        \"ancestors\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C62651/ancestors\"\n" +
                "        },\n" +
                "        \"hierarchicalParents\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C62651/hierarchicalParents\"\n" +
                "        },\n" +
                "        \"hierarchicalAncestors\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C62651/hierarchicalAncestors\"\n" +
                "        },\n" +
                "        \"jstree\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C62651/jstree\"\n" +
                "        },\n" +
                "        \"graph\" : {\n" +
                "          \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C62651/graph\"\n" +
                "        }\n" +
                "      }\n" +
                "    } ]\n" +
                "  },\n" +
                "  \"_links\" : {\n" +
                "    \"first\" : {\n" +
                "      \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C1932/hierarchicalDescendants?page=0&size=20\"\n" +
                "    },\n" +
                "    \"self\" : {\n" +
                "      \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C1932/hierarchicalDescendants\"\n" +
                "    },\n" +
                "    \"next\" : {\n" +
                "      \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C1932/hierarchicalDescendants?page=1&size=20\"\n" +
                "    },\n" +
                "    \"last\" : {\n" +
                "      \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C1932/hierarchicalDescendants?page=25&size=20\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"page\" : {\n" +
                "    \"size\" : 20,\n" +
                "    \"totalElements\" : 518,\n" +
                "    \"totalPages\" : 26,\n" +
                "    \"number\" : 0\n" +
                "  }\n" +
                "}";
    }

    private String getTreatmentTermApiResponse(){
        return "{\n" +
                "  \"iri\" : \"http://purl.obolibrary.org/obo/NCIT_C1181\",\n" +
                "  \"label\" : \"Oxaliplatin\",\n" +
                "  \"description\" : [ \"An organoplatinum complex in which the platinum atom is complexed with 1,2-diaminocyclohexane (DACH) and with an oxalate ligand as a 'leaving group.' A 'leaving group' is an atom or a group of atoms that is displaced as a stable species taking with it the bonding electrons. After displacement of the labile oxalate ligand leaving group, active oxaliplatin derivatives, such as monoaquo and diaquo DACH platinum, alkylate macromolecules, forming both inter- and intra-strand platinum-DNA crosslinks, which result in inhibition of DNA replication and transcription and cell-cycle nonspecific cytotoxicity. The DACH side chain appears to inhibit alkylating-agent resistance. (NCI04)\" ],\n" +
                "  \"annotation\" : {\n" +
                "    \"ALT_DEFINITION\" : [ \"A drug used with other drugs to treat colorectal cancer that is advanced or has come back. It is also being studied in the treatment of other types of cancer. Eloxatin attaches to DNA in cells and may kill cancer cells. It is a type of platinum compound.\" ],\n" +
                "    \"Accepted_Therapeutic_Use_For\" : [ \"Metastatic colorectal cancer; ovarian cancer\" ],\n" +
                "    \"CAS_Registry\" : [ \"61825-94-3\" ],\n" +
                "    \"CHEBI_ID\" : [ \"CHEBI:31941\" ],\n" +
                "    \"Chemical_Formula\" : [ \"C8H14N2O4Pt\" ],\n" +
                "    \"Contributing_Source\" : [ \"CTRP\", \"FDA\" ],\n" +
                "    \"Display_Name\" : [ \"Oxaliplatin\" ],\n" +
                "    \"FDA_UNII_Code\" : [ \"04ZR38536J\" ],\n" +
                "    \"Has_Target\" : [ \"http://purl.obolibrary.org/obo/NCIT_C449\" ],\n" +
                "    \"Is_Value_For_GDC_Property\" : [ \"http://purl.obolibrary.org/obo/NCIT_C1909\" ],\n" +
                "    \"Legacy Concept Name\" : [ \"Oxaliplatin\" ],\n" +
                "    \"Maps_To\" : [ \"Oxaliplatin\" ],\n" +
                "    \"NCI_Drug_Dictionary_ID\" : [ \"42374\" ],\n" +
                "    \"NSC Number\" : [ \"266046\" ],\n" +
                "    \"PDQ_Closed_Trial_Search_ID\" : [ \"42374\" ],\n" +
                "    \"PDQ_Open_Trial_Search_ID\" : [ \"42374\" ],\n" +
                "    \"Preferred_Name\" : [ \"Oxaliplatin\" ],\n" +
                "    \"Semantic_Type\" : [ \"Pharmacologic Substance\" ],\n" +
                "    \"UMLS_CUI\" : [ \"C0069717\" ],\n" +
                "    \"code\" : [ \"C1181\" ]\n" +
                "  },\n" +
                "  \"synonyms\" : [ \"Dacplat\", \"oxalato (trans-l-1,2-diaminocyclohexane)platinum(II)\", \"Diaminocyclohexane Oxalatoplatinum\", \"RP-54780\", \"trans-l diaminocyclohexane oxalatoplatinum\", \"[SP-4-2-(1R-trans)]-(1,2,cyclohexanediamine-N,N')[ethanedioato(2--)-O,O']platinum\", \"Oxalatoplatinum\", \"SR-96669\", \"OXALIPLATIN\", \"Eloxatine\", \"Eloxatin\", \"Oxaliplatin\", \"oxaliplatin\", \"[(1R,-2R)-1,2-cyclohexanediamine-N,N'][oxalato (2--)-O,O']platinum\", \"Oxalatoplatin\", \"Aiheng\", \"Dacotin\", \"Ai Heng\", \"1-OHP\", \"JM-83\", \"trans-l DACH oxalatoplatinum\", \"ELOXATIN\", \"DACPLAT\", \"RP 54780\", \"oxalato (1R,2R-cyclohexanediamine)platinum(II)\" ],\n" +
                "  \"ontology_name\" : \"ncit\",\n" +
                "  \"ontology_prefix\" : \"NCIT\",\n" +
                "  \"ontology_iri\" : \"http://purl.obolibrary.org/obo/ncit.owl\",\n" +
                "  \"is_obsolete\" : false,\n" +
                "  \"term_replaced_by\" : null,\n" +
                "  \"is_defining_ontology\" : true,\n" +
                "  \"has_children\" : false,\n" +
                "  \"is_root\" : false,\n" +
                "  \"short_form\" : \"NCIT_C1181\",\n" +
                "  \"obo_id\" : \"NCIT:C1181\",\n" +
                "  \"in_subset\" : [ \"NCIT_C116978\", \"NCIT_C176424\", \"NCIT_C128784\", \"NCIT_C63923\", \"NCIT_C116977\", \"NCIT_C157711\", \"NCIT_C177537\", \"NCIT_C157712\" ],\n" +
                "  \"obo_definition_citation\" : [{\"definition\":\"An organoplatinum complex in which the platinum atom is complexed with 1,2-diaminocyclohexane (DACH) and with an oxalate ligand as a 'leaving group.' A 'leaving group' is an atom or a group of atoms that is displaced as a stable species taking with it the bonding electrons. After displacement of the labile oxalate ligand leaving group, active oxaliplatin derivatives, such as monoaquo and diaquo DACH platinum, alkylate macromolecules, forming both inter- and intra-strand platinum-DNA crosslinks, which result in inhibition of DNA replication and transcription and cell-cycle nonspecific cytotoxicity. The DACH side chain appears to inhibit alkylating-agent resistance. (NCI04)\",\"oboXrefs\":[{\"database\":null,\"id\":\"NCI\",\"description\":null,\"url\":null}]}],\n" +
                "  \"obo_xref\" : null,\n" +
                "  \"obo_synonym\" : [{\"name\":\"oxalato (trans-l-1,2-diaminocyclohexane)platinum(II)\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"Dacplat\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"[(1R,-2R)-1,2-cyclohexanediamine-N,N'][oxalato (2--)-O,O']platinum\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"Oxalatoplatinum\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"Dacotin\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"OXALIPLATIN\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"Oxaliplatin\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"trans-l DACH oxalatoplatinum\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"SR-96669\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"[SP-4-2-(1R-trans)]-(1,2,cyclohexanediamine-N,N')[ethanedioato(2--)-O,O']platinum\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"JM-83\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"RP 54780\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"oxaliplatin\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"DACPLAT\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"ELOXATIN\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"Eloxatin\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"Eloxatine\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"oxalato (1R,2R-cyclohexanediamine)platinum(II)\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"Ai Heng\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"trans-l diaminocyclohexane oxalatoplatinum\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"1-OHP\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"Oxalatoplatin\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"RP-54780\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"Diaminocyclohexane Oxalatoplatinum\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}, {\"name\":\"Aiheng\",\"scope\":\"hasExactSynonym\",\"type\":null,\"xrefs\":[]}],\n" +
                "  \"is_preferred_root\" : false,\n" +
                "  \"_links\" : {\n" +
                "    \"self\" : {\n" +
                "      \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C1181\"\n" +
                "    },\n" +
                "    \"parents\" : {\n" +
                "      \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C1181/parents\"\n" +
                "    },\n" +
                "    \"ancestors\" : {\n" +
                "      \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C1181/ancestors\"\n" +
                "    },\n" +
                "    \"hierarchicalParents\" : {\n" +
                "      \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C1181/hierarchicalParents\"\n" +
                "    },\n" +
                "    \"hierarchicalAncestors\" : {\n" +
                "      \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C1181/hierarchicalAncestors\"\n" +
                "    },\n" +
                "    \"jstree\" : {\n" +
                "      \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C1181/jstree\"\n" +
                "    },\n" +
                "    \"graph\" : {\n" +
                "      \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C1181/graph\"\n" +
                "    },\n" +
                "    \"Chemical_Or_Drug_Has_Physiologic_Effect\" : {\n" +
                "      \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C1181/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_R125\"\n" +
                "    },\n" +
                "    \"Chemical_Or_Drug_Has_Mechanism_Of_Action\" : {\n" +
                "      \"href\" : \"https://www.ebi.ac.uk/ols/api/ontologies/ncit/terms/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_C1181/http%253A%252F%252Fpurl.obolibrary.org%252Fobo%252FNCIT_R124\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }


    private String getBranchDataFromApi(){
        return "{\n" +
                "  \"_embedded\" : {\n" +
                "    \"terms\" : [ {\n" +
                "      \"iri\" : \"http://purl.obolibrary.org/obo/NCIT_C134000\",\n" +
                "      \"label\" : \"Organic Impurity\",\n" +
                "      \"description\" : [ \"Any unwanted organic compound that is present in specifically defined substances or products and is generated during the manufacturing process or is created upon degradation during storage.\" ],\n" +
                "      \"annotation\" : {\n" +
                "        \"ALT_DEFINITION\" : [ \"Materials that are degradation products or residuals and are generated during a manufacturing process or storage. [Source: Adapted form ICH Q3A(R2)]\" ],\n" +
                "        \"Contributing_Source\" : [ \"FDA\" ],\n" +
                "        \"NCI_META_CUI\" : [ \"CL521833\" ],\n" +
                "        \"Preferred_Name\" : [ \"Organic Impurity\" ],\n" +
                "        \"Semantic_Type\" : [ \"Chemical Viewed Functionally\" ],\n" +
                "        \"code\" : [ \"C134000\" ]\n" +
                "      },\n" +
                "      \"synonyms\" : [ \"Organic Impurities\", \"Organic\", \"Organic Impurity\" ],\n" +
                "      \"ontology_name\" : \"ncit\",\n" +
                "      \"ontology_prefix\" : \"NCIT\",\n" +
                "      \"ontology_iri\" : \"http://purl.obolibrary.org/obo/ncit.owl\",\n" +
                "      \"is_obsolete\" : false,\n" +
                "      \"term_replaced_by\" : null,\n" +
                "      \"is_defining_ontology\" : true,\n" +
                "      \"has_children\" : false,\n" +
                "      \"is_root\" : false,\n" +
                "      \"short_form\" : \"NCIT_C134000\"\n" +
                "    }, {\n" +
                "      \"iri\" : \"http://purl.obolibrary.org/obo/NCIT_C62014\",\n" +
                "      \"label\" : \"BMS-582949 Hydrochloride\",\n" +
                "      \"description\" : null,\n" +
                "      \"annotation\" : {\n" +
                "        \"CAS_Registry\" : [ \"912806-16-7\" ],\n" +
                "        \"Chemical_Formula\" : [ \"C22H26N6O2.ClH\" ],\n" +
                "        \"Contributing_Source\" : [ \"FDA\" ],\n" +
                "        \"FDA_UNII_Code\" : [ \"3Z6GAP3R9Q\" ],\n" +
                "        \"Legacy Concept Name\" : [ \"_4_5_Cyclopropylamino_Carbonyl_2-Methylphenyl_Amino_5-Methyl-N-Propylpyrrolo_2_1-F_1_2_4_Triazine-6-Carboxamide_Hydrochloride\" ],\n" +
                "        \"Preferred_Name\" : [ \"BMS-582949 Hydrochloride\" ],\n" +
                "        \"Semantic_Type\" : [ \"Organic Chemical\" ],\n" +
                "        \"UMLS_CUI\" : [ \"C3266926\" ],\n" +
                "        \"code\" : [ \"C62014\" ]\n" +
                "      },\n" +
                "      \"synonyms\" : [ \"BMS-582949 HYDROCHLORIDE\", \"BMS-582949 HCl\", \"BMS-582949 Hydrochloride\", \"4-((5-((Cyclopropylamino)Carbonyl)-2-Methylphenyl)Amino)-5-Methyl-N-Propylpyrrolo(2,1-F)(1,2,4)Triazine-6-Carboxamide Hydrochloride\" ],\n" +
                "      \"ontology_name\" : \"ncit\",\n" +
                "      \"ontology_prefix\" : \"NCIT\",\n" +
                "      \"ontology_iri\" : \"http://purl.obolibrary.org/obo/ncit.owl\",\n" +
                "      \"is_obsolete\" : false,\n" +
                "      \"term_replaced_by\" : null,\n" +
                "      \"is_defining_ontology\" : true,\n" +
                "      \"has_children\" : false,\n" +
                "      \"is_root\" : false,\n" +
                "      \"short_form\" : \"NCIT_C62014\"\n" +
                "    }, {\n" +
                "      \"iri\" : \"http://purl.obolibrary.org/obo/NCIT_C173418\",\n" +
                "      \"label\" : \"Ethylbenzene\",\n" +
                "      \"description\" : [ \"An aromatic hydrocarbon composed of a benzene ring linked to an ethyl group. Ethylbenzene is a constituent of petroleum and coal tar and is used as either a petroleum additive or a chemical intermediate in the production of polystyrene. High level exposure to airborne ethylbenzene is associated with eye and throat irritation.\" ],\n" +
                "      \"annotation\" : {\n" +
                "        \"CAS_Registry\" : [ \"100-41-4\" ],\n" +
                "        \"CHEBI_ID\" : [ \"CHEBI:16101\" ],\n" +
                "        \"Chemical_Formula\" : [ \"C8H10\" ],\n" +
                "        \"Contributing_Source\" : [ \"CTRP\", \"FDA\" ],\n" +
                "        \"Display_Name\" : [ \"Ethylbenzene\" ],\n" +
                "        \"FDA_UNII_Code\" : [ \"L5I45M5G0O\" ],\n" +
                "        \"NCI_META_CUI\" : [ \"CL1407427\" ],\n" +
                "        \"Preferred_Name\" : [ \"Ethylbenzene\" ],\n" +
                "        \"Semantic_Type\" : [ \"Organic Chemical\", \"Hazardous or Poisonous Substance\" ],\n" +
                "        \"code\" : [ \"C173418\" ]\n" +
                "      },\n" +
                "      \"synonyms\" : [ \"Ethylbenzene\", \"Ethyl Benzene\", \"ETHYLBENZENE\", \"Phenylethane\", \"Ethylbenzol\", \"Benzene, Ethyl-\" ],\n" +
                "      \"ontology_name\" : \"ncit\",\n" +
                "      \"ontology_prefix\" : \"NCIT\",\n" +
                "      \"ontology_iri\" : \"http://purl.obolibrary.org/obo/ncit.owl\",\n" +
                "      \"is_obsolete\" : false,\n" +
                "      \"term_replaced_by\" : null,\n" +
                "      \"is_defining_ontology\" : true,\n" +
                "      \"has_children\" : false,\n" +
                "      \"is_root\" : false,\n" +
                "      \"short_form\" : \"NCIT_C173418\"\n" +
                "    }, {\n" +
                "      \"iri\" : \"http://purl.obolibrary.org/obo/NCIT_C76520\",\n" +
                "      \"label\" : \"Aluminum Chloride Anhydrous\",\n" +
                "      \"description\" : null,\n" +
                "      \"annotation\" : {\n" +
                "        \"CAS_Registry\" : [ \"7446-70-0\" ],\n" +
                "        \"CHEBI_ID\" : [ \"CHEBI:30114\" ],\n" +
                "        \"Chemical_Formula\" : [ \"Al.3Cl\" ],\n" +
                "        \"Contributing_Source\" : [ \"FDA\" ],\n" +
                "        \"FDA_UNII_Code\" : [ \"LIF1N9568Y\" ],\n" +
                "        \"Legacy Concept Name\" : [ \"Aluminum_Chloride\" ],\n" +
                "        \"Preferred_Name\" : [ \"Aluminum Chloride Anhydrous\" ],\n" +
                "        \"Semantic_Type\" : [ \"Pharmacologic Substance\", \"Inorganic Chemical\" ],\n" +
                "        \"UMLS_CUI\" : [ \"C3887607\" ],\n" +
                "        \"code\" : [ \"C76520\" ]\n" +
                "      },\n" +
                "      \"synonyms\" : [ \"Aluminum Chloride Anhydrous\", \"ALUMINUM CHLORIDE ANHYDROUS\" ],\n" +
                "      \"ontology_name\" : \"ncit\",\n" +
                "      \"ontology_prefix\" : \"NCIT\",\n" +
                "      \"ontology_iri\" : \"http://purl.obolibrary.org/obo/ncit.owl\",\n" +
                "      \"is_obsolete\" : false,\n" +
                "      \"term_replaced_by\" : null,\n" +
                "      \"is_defining_ontology\" : true,\n" +
                "      \"has_children\" : false,\n" +
                "      \"is_root\" : false,\n" +
                "      \"short_form\" : \"NCIT_C76520\"\n" +
                "    }, {\n" +
                "      \"iri\" : \"http://purl.obolibrary.org/obo/NCIT_C76926\",\n" +
                "      \"label\" : \"Lactic Acid\",\n" +
                "      \"description\" : null,\n" +
                "      \"annotation\" : {\n" +
                "        \"ALT_DEFINITION\" : [ \"A substance made from sugars in milk, by the action of certain enzymes. It is used in skin care products to reduce wrinkles and soften the skin. It is also being studied in the treatment of hand-foot syndrome (a condition marked by pain, swelling, numbness, tingling, or redness of the hands or feet) in patients receiving chemotherapy. Lactic acid is also made in muscles in the body and is used in many chemical processes in the body. It is a type of alpha hydroxyl acid.\" ],\n" +
                "        \"CAS_Registry\" : [ \"50-21-5\" ],\n" +
                "        \"CHEBI_ID\" : [ \"CHEBI:28358\" ],\n" +
                "        \"Contributing_Source\" : [ \"FDA\" ],\n" +
                "        \"FDA_UNII_Code\" : [ \"33X04XA5AT\" ],\n" +
                "        \"Legacy Concept Name\" : [ \"Lactic_Acid\" ],\n" +
                "        \"Preferred_Name\" : [ \"Lactic Acid\" ],\n" +
                "        \"Semantic_Type\" : [ \"Pharmacologic Substance\", \"Organic Chemical\" ],\n" +
                "        \"UMLS_CUI\" : [ \"C0064582\" ],\n" +
                "        \"code\" : [ \"C76926\" ]\n" +
                "      },\n" +
                "      \"synonyms\" : [ \"lactic acid\", \"LACTIC ACID, UNSPECIFIED FORM\", \"Lactate\", \"Lactic Acid\" ],\n" +
                "      \"ontology_name\" : \"ncit\",\n" +
                "      \"ontology_prefix\" : \"NCIT\",\n" +
                "      \"ontology_iri\" : \"http://purl.obolibrary.org/obo/ncit.owl\",\n" +
                "      \"is_obsolete\" : false,\n" +
                "      \"term_replaced_by\" : null,\n" +
                "      \"is_defining_ontology\" : true,\n" +
                "      \"has_children\" : false,\n" +
                "      \"is_root\" : false,\n" +
                "      \"short_form\" : \"NCIT_C76926\"\n" +
                "    }, {\n" +
                "      \"iri\" : \"http://purl.obolibrary.org/obo/NCIT_C96191\",\n" +
                "      \"label\" : \"1,1,2-Trichlorotrifluoroethane\",\n" +
                "      \"description\" : null,\n" +
                "      \"annotation\" : {\n" +
                "        \"CAS_Registry\" : [ \"76-13-1\" ],\n" +
                "        \"Chemical_Formula\" : [ \"C2Cl3F3\" ],\n" +
                "        \"Contributing_Source\" : [ \"FDA\" ],\n" +
                "        \"FDA_UNII_Code\" : [ \"0739N04X3A\" ],\n" +
                "        \"Preferred_Name\" : [ \"1,1,2-Trichlorotrifluoroethane\" ],\n" +
                "        \"Semantic_Type\" : [ \"Organic Chemical\", \"Hazardous or Poisonous Substance\" ],\n" +
                "        \"UMLS_CUI\" : [ \"C0118235\" ],\n" +
                "        \"code\" : [ \"C96191\" ]\n" +
                "      },\n" +
                "      \"synonyms\" : [ \"1,1,2-Trifluoro-1,2,2-trichloroethane\", \"Freon 113\", \"1,1,2-Trichlorotrifluoroethane\", \"Trichlorotrifluoroethane\", \"1,1,2-Trifluorotrichloroethane\", \"Fluorocarbon 113\", \"R113\", \"1,1,2-TRICHLOROTRIFLUOROETHANE\", \"1,2,2-Trichlorotrifluoroethane\" ],\n" +
                "      \"ontology_name\" : \"ncit\",\n" +
                "      \"ontology_prefix\" : \"NCIT\",\n" +
                "      \"ontology_iri\" : \"http://purl.obolibrary.org/obo/ncit.owl\",\n" +
                "      \"is_obsolete\" : false,\n" +
                "      \"term_replaced_by\" : null,\n" +
                "      \"is_defining_ontology\" : true,\n" +
                "      \"has_children\" : false,\n" +
                "      \"is_root\" : false,\n" +
                "      \"short_form\" : \"NCIT_C96191\"\n" +
                "    } ]\n" +
                "  },\n" +
                "  \"page\" : {\n" +
                "    \"size\" : 20,\n" +
                "    \"totalElements\" : 1392,\n" +
                "    \"totalPages\" : 1,\n" +
                "    \"number\" : 0\n" +
                "  }\n" +
                "}";
    }

}
