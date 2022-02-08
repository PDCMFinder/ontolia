# Ontolia

Ontolia is an ONTology LInking Application. The software connects to EMBL-EBI's Ontology Lookup Service to gather regimens and treatments specified in NCIT. 
Not all branches are loaded by the application, see the [branch definitions](/src/main/java/org/cancermodels/ontolia/config/OntologyBranchDefinitions.java) for more info.
Once the regimes and treatments are loaded, the application tries to link regimen terms to their corresponding set of treatment terms. Once these links are created Ontolia will export this knowledge into an output file.


To run the applicat
