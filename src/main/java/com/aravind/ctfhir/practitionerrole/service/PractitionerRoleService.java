package com.aravind.ctfhir.practitionerrole.service;

import ca.uhn.fhir.rest.api.server.IBundleProvider;
import org.hl7.fhir.r4.model.PractitionerRole;
import org.hl7.fhir.r4.model.IdType;
import java.util.Map;

public interface PractitionerRoleService {

    /**
     * Reads a PractitionerRole resource by its ID.
     */
    PractitionerRole readPractitionerRole(IdType theId);

    /**
     * Searches for PractitionerRole resources based on the provided parameters.
     */
    IBundleProvider searchPractitionerRoles(Map<String, Object> searchParams);

    // Add create, update, delete methods as needed
    // MethodOutcome createPractitionerRole(PractitionerRole thePractitionerRole);
    // MethodOutcome updatePractitionerRole(IdType theId, PractitionerRole thePractitionerRole);
}