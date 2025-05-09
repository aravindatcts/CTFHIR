package com.aravind.ctfhir.patient.service;

import java.util.Date;

import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Meta;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.StringType;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;

public class Mapper {

    public static void main(String[] args) {
        // Create a new Patient resource
        Patient patient = new Patient();

        // Set basic patient details (similar to your example)
        patient.setId("2"); // Or let the server assign it
        patient.setMeta(new Meta()
            .setVersionId("2") // Usually set by the server on update
            .setLastUpdated(new Date()) // Example, usually set by server
            .setSource("#riIHpZ3lsjW2iBMH")); // Example source

        patient.addIdentifier(new Identifier()
            .setSystem("www.mypatientidentifier.com/ids")
            .setValue("aravind.balasubramanian"));

        patient.addName(new HumanName()
            .setFamily("Balasubramanian")
            .addGiven("Aravind"));
        
        patient.addAddress(new Address()
            .addLine("108 Weight Avenue is updated")
            .setCity("Ann Arbor")
            .setState("MI")
            .setCountry("USA"));

        patient.setManagingOrganization(new Reference("Organization/1210409"));


        // 1. US Core Race Extension (Complex Extension with nested extensions)
        // Corresponds to:
        // {
        //   "extension" : [ { "url" : "ombCategory", ... }, { "url" : "text", ... } ],
        //   "url" : "http://hl7.org/fhir/us/core/StructureDefinition/us-core-race"
        // }
        Extension usCoreRace = new Extension();
        usCoreRace.setUrl("http://hl7.org/fhir/us/core/StructureDefinition/us-core-race");

        // Nested extension for ombCategory
        Extension raceOmbCategory = new Extension();
        raceOmbCategory.setUrl("ombCategory"); // Relative URL within the parent extension's definition
        Coding raceCoding = new Coding();
        raceCoding.setSystem("urn:oid:2.16.840.1.113883.6.238");
        raceCoding.setCode("2028-9");
        raceCoding.setDisplay("Asian");
        raceOmbCategory.setValue(raceCoding);
        usCoreRace.addExtension(raceOmbCategory); // Add nested to parent

        // Nested extension for text
        Extension raceText = new Extension();
        raceText.setUrl("text");
        raceText.setValue(new StringType("Asian"));
        usCoreRace.addExtension(raceText); // Add nested to parent

        patient.addExtension(usCoreRace); // Add the top-level race extension to the patient


        // 2. US Core Ethnicity Extension (Complex Extension with nested extensions)
        // Corresponds to:
        // {
        //   "extension" : [ { "url" : "ombCategory", ... }, { "url" : "text", ... } ],
        //   "url" : "http://hl7.org/fhir/us/core/StructureDefinition/us-core-ethnicity"
        // }
        Extension usCoreEthnicity = new Extension();
        usCoreEthnicity.setUrl("http://hl7.org/fhir/us/core/StructureDefinition/us-core-ethnicity");

        // Nested extension for ombCategory
        Extension ethnicityOmbCategory = new Extension();
        ethnicityOmbCategory.setUrl("ombCategory");
        Coding ethnicityCoding = new Coding();
        ethnicityCoding.setSystem("urn:oid:2.16.840.1.113883.6.238");
        ethnicityCoding.setCode("2186-5");
        ethnicityCoding.setDisplay("Not Hispanic or Latino");
        ethnicityOmbCategory.setValue(ethnicityCoding);
        usCoreEthnicity.addExtension(ethnicityOmbCategory);

        // Nested extension for text
        Extension ethnicityText = new Extension();
        ethnicityText.setUrl("text");
        ethnicityText.setValue(new StringType("Not Hispanic or Latino"));
        usCoreEthnicity.addExtension(ethnicityText);

        patient.addExtension(usCoreEthnicity); // Add the top-level ethnicity extension to the patient


        // 3. US Core Sex Extension (Simple Extension with a direct valueCoding)
        // Corresponds to:
        // {
        //   "url" : "http://hl7.org/fhir/us/core/StructureDefinition/us-core-sex",
        //   "valueCoding" : { ... }
        // }
        Extension usCoreSex = new Extension();
        usCoreSex.setUrl("http://hl7.org/fhir/us/core/StructureDefinition/us-core-sex");
        Coding sexCoding = new Coding();
        sexCoding.setSystem("http://snomed.info/sct");
        sexCoding.setVersion("http://snomed.info/sct/731000124108");
        sexCoding.setCode("248153007");
        sexCoding.setDisplay("Male (finding)");
        usCoreSex.setValue(sexCoding); // Set value directly on the extension

        patient.addExtension(usCoreSex); // Add the top-level sex extension to the patient


        // Now, let's serialize this Patient object to JSON to see the result
        FhirContext ctx = FhirContext.forR4();
        IParser parser = ctx.newJsonParser().setPrettyPrint(true);
        String patientJson = parser.encodeResourceToString(patient);

        System.out.println(patientJson);

        // You can also use the FhirExtensionUtil from the previous example to print them
        if (patient.hasExtension()) {
            System.out.println("\n--- Parsed Extensions using FhirExtensionUtil ---");
            // Assuming FhirExtensionUtil is in com.aravind.ctfhir.util package
            // System.out.println(com.aravind.ctfhir.util.FhirExtensionUtil.extensionsToString(patient.getExtension()));
        }
    }
}
 
