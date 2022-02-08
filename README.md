# Ontolia

Ontolia is an ONTology LInking Application. The software connects to EMBL-EBI's Ontology Lookup Service to gather regimens 
and treatments specified in NCIT. Not all branches are loaded by the application, see the 
[branch definitions](/src/main/java/org/cancermodels/ontolia/config/OntologyBranchDefinitions.java) for more info.
Once the regimens and treatments are loaded, the application tries to link regimen terms to their corresponding set of 
treatment terms, then Ontolia will export this knowledge into an output file. To do this the user has to specify a valid
output directory, using the --dir= program argument.



### Running the application
```aidl
java -jar ontolia.jar --dir=/Users/csaba/Ontolia
```
This command will run the application and creates the ontolia output file at: /Users/csaba/Ontolia/ontolia_output.txt

###Ontolia output file format
*RegimenId=TreatmentTermId1,TreatmentTermId2,TreatmentTermIdX**

Example output:
```aidl
NCIT_C37600=NCIT_C1282,NCIT_C405,NCIT_C456,NCIT_C491
NCIT_C37601=NCIT_C1282,NCIT_C405,NCIT_C1093
```

