package org.cancermodels.ontolia;

import org.cancermodels.ontolia.services.OntologyReader;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

public class OntologyReaderTest {

    OntologyReader ontologyReader = new OntologyReader();
    public final String URL1 = "http://purl.obolibrary.org/obo/NCIT_C1932";

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

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



}
