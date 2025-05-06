package com.aravind.ctfhir.practitioner.service;

 
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.IdType;

import java.util.Map;

public interface PractitionerService {

    /**
     * Reads a Practitioner resource by its ID.
     */
    Practitioner readPractitioner(IdType theId);

    /**
     * Searches for Practitioner resources based on the provided parameters.
     */
    IBundleProvider searchPractitioners(Map<String, Object> searchParams);

    // Add create, update, delete methods as needed
    // MethodOutcome createPractitioner(Practitioner thePractitioner);
    // MethodOutcome updatePractitioner(IdType theId, Practitioner thePractitioner);
}