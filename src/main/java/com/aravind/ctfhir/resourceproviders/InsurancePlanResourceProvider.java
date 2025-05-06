package com.aravind.ctfhir.resourceproviders;

import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.param.ReferenceParam;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import com.aravind.ctfhir.insuranceplan.service.InsurancePlanService;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.InsurancePlan;
import org.hl7.fhir.r4.model.Organization; // For owned-by, administered-by, network
 
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service // Mark as a Spring component bean
public class InsurancePlanResourceProvider implements IResourceProvider {

    private final InsurancePlanService insurancePlanService;

    // Spring will inject the InsurancePlanService bean
    public InsurancePlanResourceProvider(InsurancePlanService insurancePlanService) {
        this.insurancePlanService = insurancePlanService;
    }

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return InsurancePlan.class;
    }

    /**
     * Handles GET requests for a specific InsurancePlan resource by ID.
     * Example: [base]/InsurancePlan/123
     */
    @Read()
    public InsurancePlan readInsurancePlan(@IdParam IdType theId) {
        System.out.println("Reading InsurancePlan with ID: " + theId.getIdPart());
        return insurancePlanService.readInsurancePlan(theId);
    }

    /**
     * Handles search requests for InsurancePlan resources.
     * Example: [base]/InsurancePlan?name=MyHealthPlan&owned-by=Organization/payer-org
     */
    @Search
    public IBundleProvider searchInsurancePlans(
            @OptionalParam(name = InsurancePlan.SP_RES_ID) TokenParam theInsurancePlanId,
            @OptionalParam(name = InsurancePlan.SP_IDENTIFIER) TokenParam theIdentifier,
            @OptionalParam(name = InsurancePlan.SP_NAME) StringParam theName,
            @OptionalParam(name = InsurancePlan.SP_STATUS) TokenParam theStatus, // active | draft | retired
            @OptionalParam(name = InsurancePlan.SP_TYPE) TokenParam theType, // CodeableConcept for plan type
            @OptionalParam(name = InsurancePlan.SP_OWNED_BY, chainWhitelist = { Organization.SP_IDENTIFIER, Organization.SP_NAME }) ReferenceParam theOwnedBy,
            @OptionalParam(name = InsurancePlan.SP_ADMINISTERED_BY, chainWhitelist = { Organization.SP_IDENTIFIER, Organization.SP_NAME }) ReferenceParam theAdministeredBy,
            @OptionalParam(name = InsurancePlan.SP_ADDRESS_CITY) StringParam theAddressCity, // Searches plan.coverageArea.address.city
            @OptionalParam(name = InsurancePlan.SP_ADDRESS_STATE) StringParam theAddressState, // Searches plan.coverageArea.address.state
            @OptionalParam(name = InsurancePlan.SP_ADDRESS_POSTALCODE) StringParam theAddressPostalCode // Searches plan.coverageArea.address.postalCode
            // Add other relevant search parameters (e.g., endpoint, network, plan-type)
    ) {
        Map<String, Object> searchParams = new HashMap<>();

        // Add non-null parameters to the map
        if (theInsurancePlanId != null) searchParams.put(InsurancePlan.SP_RES_ID, theInsurancePlanId);
        if (theIdentifier != null) searchParams.put(InsurancePlan.SP_IDENTIFIER, theIdentifier);
        if (theName != null) searchParams.put(InsurancePlan.SP_NAME, theName);
        if (theStatus != null) searchParams.put(InsurancePlan.SP_STATUS, theStatus);
        if (theType != null) searchParams.put(InsurancePlan.SP_TYPE, theType);
        if (theOwnedBy != null) searchParams.put(InsurancePlan.SP_OWNED_BY, theOwnedBy);
        if (theAdministeredBy != null) searchParams.put(InsurancePlan.SP_ADMINISTERED_BY, theAdministeredBy);
        if (theAddressCity != null) searchParams.put(InsurancePlan.SP_ADDRESS_CITY, theAddressCity);
        if (theAddressState != null) searchParams.put(InsurancePlan.SP_ADDRESS_STATE, theAddressState);
        if (theAddressPostalCode != null) searchParams.put(InsurancePlan.SP_ADDRESS_POSTALCODE, theAddressPostalCode);

        System.out.println("Searching InsurancePlan with params: " + searchParams);
        return insurancePlanService.searchInsurancePlans(searchParams);
    }

    // TODO: Implement @Create, @Update, @Delete methods as needed
}