package com.aravind.ctfhir.resourceproviders;

import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.param.ReferenceParam;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import com.aravind.ctfhir.practitionerrole.service.PractitionerRoleService;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PractitionerRoleResourceProvider implements IResourceProvider {

    private final PractitionerRoleService practitionerRoleService;

    // Spring will inject the PractitionerRoleService bean
    public PractitionerRoleResourceProvider(PractitionerRoleService practitionerRoleService) {
        this.practitionerRoleService = practitionerRoleService;
    }

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return PractitionerRole.class;
    }

    /**
     * Handles GET requests for a specific PractitionerRole resource by ID.
     * Example: [base]/PractitionerRole/123
     */
    @Read()
    public PractitionerRole readPractitionerRole(@IdParam IdType theId) {
        System.out.println("Reading PractitionerRole with ID: " + theId.getIdPart());
        return practitionerRoleService.readPractitionerRole(theId);
    }

    /**
     * Handles search requests for PractitionerRole resources.
     * Example: [base]/PractitionerRole?practitioner=Practitioner/abc&organization=Organization/xyz
     */
    @Search
    public IBundleProvider searchPractitionerRoles(
            @OptionalParam(name = PractitionerRole.SP_RES_ID) TokenParam thePractitionerRoleId,
            @OptionalParam(name = PractitionerRole.SP_IDENTIFIER) TokenParam theIdentifier,
            @OptionalParam(name = PractitionerRole.SP_ACTIVE) TokenParam theActive,
            @OptionalParam(name = PractitionerRole.SP_PRACTITIONER, chainWhitelist = { Practitioner.SP_IDENTIFIER, Practitioner.SP_NAME, Practitioner.SP_FAMILY, Practitioner.SP_GIVEN }) ReferenceParam thePractitioner,
            @OptionalParam(name = PractitionerRole.SP_ORGANIZATION, chainWhitelist = { Organization.SP_IDENTIFIER, Organization.SP_NAME }) ReferenceParam theOrganization,
            @OptionalParam(name = PractitionerRole.SP_ROLE) TokenParam theRole, // CodeableConcept for role
            @OptionalParam(name = PractitionerRole.SP_SPECIALTY) TokenParam theSpecialty, // CodeableConcept for specialty
            @OptionalParam(name = PractitionerRole.SP_SERVICE) ReferenceParam theService, // Reference to HealthcareService
            @OptionalParam(name = PractitionerRole.SP_LOCATION) ReferenceParam theLocation, // Reference to Location
            @OptionalParam(name = PractitionerRole.SP_TELECOM) TokenParam theTelecom,
            @OptionalParam(name = PractitionerRole.SP_ENDPOINT) ReferenceParam theEndpoint // Reference to Endpoint
            // Add other relevant search parameters as needed
    ) {
        Map<String, Object> searchParams = new HashMap<>();

        // Add non-null parameters to the map
        if (thePractitionerRoleId != null) searchParams.put(PractitionerRole.SP_RES_ID, thePractitionerRoleId);
        if (theIdentifier != null) searchParams.put(PractitionerRole.SP_IDENTIFIER, theIdentifier);
        if (theActive != null) searchParams.put(PractitionerRole.SP_ACTIVE, theActive);
        if (thePractitioner != null) searchParams.put(PractitionerRole.SP_PRACTITIONER, thePractitioner);
        if (theOrganization != null) searchParams.put(PractitionerRole.SP_ORGANIZATION, theOrganization);
        if (theRole != null) searchParams.put(PractitionerRole.SP_ROLE, theRole);
        if (theSpecialty != null) searchParams.put(PractitionerRole.SP_SPECIALTY, theSpecialty);
        if (theService != null) searchParams.put(PractitionerRole.SP_SERVICE, theService);
        if (theLocation != null) searchParams.put(PractitionerRole.SP_LOCATION, theLocation);
        if (theTelecom != null) searchParams.put(PractitionerRole.SP_TELECOM, theTelecom);
        if (theEndpoint != null) searchParams.put(PractitionerRole.SP_ENDPOINT, theEndpoint);

        System.out.println("Searching PractitionerRole with params: " + searchParams);
        return practitionerRoleService.searchPractitionerRoles(searchParams);
    }

    // TODO: Implement @Create, @Update, @Delete methods as needed
}