package com.aravind.ctfhir.resourceproviders;

import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import com.aravind.ctfhir.organization.service.OrganizationService;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Organization;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service 
public class OrganizationResourceProvider implements IResourceProvider {

    private final OrganizationService organizationService;

    // Spring will inject the OrganizationService bean
    public OrganizationResourceProvider(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return Organization.class;
    }

 
    @Read()
    public Organization readOrganization(@IdParam IdType theId) {
        System.out.println("Reading Organization with ID: " + theId.getIdPart());
        return organizationService.readOrganization(theId);
    }
 
    @Search
    public IBundleProvider searchOrganization(
            @OptionalParam(name = Organization.SP_RES_ID) TokenParam theOrganizationId,
            @OptionalParam(name = Organization.SP_IDENTIFIER) TokenParam theIdentifier,
            @OptionalParam(name = Organization.SP_ACTIVE) TokenParam theActive,
            @OptionalParam(name = Organization.SP_NAME) StringParam theName,
            @OptionalParam(name = Organization.SP_ADDRESS_CITY) StringParam theAddressCity,
            @OptionalParam(name = Organization.SP_ADDRESS_STATE) StringParam theAddressState,
            @OptionalParam(name = Organization.SP_ADDRESS_POSTALCODE) StringParam theAddressPostalCode
            // Add other relevant search parameters (e.g., type, partof)
    ) {
        Map<String, Object> searchParams = new HashMap<>();

        // Add non-null parameters to the map
        if (theOrganizationId != null) searchParams.put(Organization.SP_RES_ID, theOrganizationId);
        if (theIdentifier != null) searchParams.put(Organization.SP_IDENTIFIER, theIdentifier);
        if (theActive != null) searchParams.put(Organization.SP_ACTIVE, theActive);
        if (theName != null) searchParams.put(Organization.SP_NAME, theName);
        if (theAddressCity != null) searchParams.put(Organization.SP_ADDRESS_CITY, theAddressCity);
        if (theAddressState != null) searchParams.put(Organization.SP_ADDRESS_STATE, theAddressState);
        if (theAddressPostalCode != null) searchParams.put(Organization.SP_ADDRESS_POSTALCODE, theAddressPostalCode);

        System.out.println("Searching Organization with params: " + searchParams);
        return organizationService.searchOrganization(searchParams);
    }

    // TODO: Implement @Create, @Update, @Delete methods as needed
}