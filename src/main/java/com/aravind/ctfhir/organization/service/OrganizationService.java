package com.aravind.ctfhir.organization.service;

import ca.uhn.fhir.rest.api.server.IBundleProvider;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.IdType;
import java.util.Map;

public interface OrganizationService {

    /**
     * Reads an Organization resource by its ID.
     */
    Organization readOrganization(IdType theId);

    /**
     * Searches for Organization resources based on the provided parameters.
     */
    IBundleProvider searchOrganization(Map<String, Object> searchParams);

    // Add create, update, delete methods as needed
    // MethodOutcome createOrganization(Organization theOrganization);
    // MethodOutcome updateOrganization(IdType theId, Organization theOrganization);
}