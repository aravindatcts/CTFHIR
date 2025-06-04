package com.aravind.ctfhir.resourceproviders;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.ExplanationOfBenefit;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Organization;
import org.springframework.stereotype.Service;

import com.aravind.ctfhir.explanationofbenefit.service.ExplanationOfBenefitService;

import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.IncludeParam;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.param.DateParam;
import ca.uhn.fhir.rest.param.ReferenceParam;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.IResourceProvider;

@Service // Mark as a Spring component bean
public class ExplanationOfBenefitResourceProvider implements IResourceProvider {

    private final ExplanationOfBenefitService explanationOfBenefitService;

    // Spring will inject the ExplanationOfBenefitService bean
    public ExplanationOfBenefitResourceProvider(ExplanationOfBenefitService explanationOfBenefitService) {
        this.explanationOfBenefitService = explanationOfBenefitService;
    }

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return ExplanationOfBenefit.class;
    }

    /**
     * Handles GET requests for a specific ExplanationOfBenefit resource by ID.
     * Example: [base]/ExplanationOfBenefit/123
     */
    @Read()
    public ExplanationOfBenefit readExplanationOfBenefit(@IdParam IdType theId) {
        System.out.println("Reading ExplanationOfBenefit with ID: " + theId.getIdPart());
        return explanationOfBenefitService.readExplanationOfBenefit(theId);
    }

    /**
     * Handles search requests for ExplanationOfBenefit resources.
     * Example: [base]/ExplanationOfBenefit?patient=Patient/abc&status=active
     */
    @Search
    public IBundleProvider searchExplanationOfBenefits(
            @OptionalParam(name = ExplanationOfBenefit.SP_RES_ID) TokenParam theEoBId,
            @OptionalParam(name = ExplanationOfBenefit.SP_IDENTIFIER) TokenParam theIdentifier,
            @OptionalParam(name = ExplanationOfBenefit.SP_STATUS) TokenParam theStatus, // active | cancelled | draft | entered-in-error
            @OptionalParam(name = "type") TokenParam theType, // CodeableConcept for EOB type
            @OptionalParam(name = ExplanationOfBenefit.SP_PATIENT) ReferenceParam thePatient,
            @OptionalParam(name = ExplanationOfBenefit.SP_CREATED) DateParam theCreatedDate,
            @OptionalParam(name = "insurer", chainWhitelist = { Organization.SP_IDENTIFIER, Organization.SP_NAME }) ReferenceParam theInsurer,
            @OptionalParam(name = "periodstart") DateParam thePeriodStart, // Optional: For filtering by period start   
            @OptionalParam(name = "periodend") DateParam thePeriodEnd, // Optional: For filtering by period end
            // @OptionalParam(name = ExplanationOfBenefit.SP_PROVIDER, chainWhitelist = { Practitioner.SP_IDENTIFIER, Practitioner.SP_NAME, Organization.SP_IDENTIFIER, Organization.SP_NAME }) ReferenceParam theProvider,
            // @OptionalParam(name = ExplanationOfBenefit.SP_FACILITY, chainWhitelist = { Location.SP_IDENTIFIER, Location.SP_NAME }) ReferenceParam theFacility,
            // @OptionalParam(name = ExplanationOfBenefit.SP_CARE_TEAM, chainWhitelist = { Practitioner.SP_IDENTIFIER, Practitioner.SP_NAME, Organization.SP_IDENTIFIER, Organization.SP_NAME, PractitionerRole.SP_PRACTITIONER, PractitionerRole.SP_ORGANIZATION }) ReferenceParam theCareTeam,
            // @OptionalParam(name = ExplanationOfBenefit.SP_COVERAGE, chainWhitelist = { Coverage.SP_IDENTIFIER, Coverage.SP_BENEFICIARY, Coverage.SP_PAYOR }) ReferenceParam theCoverage,
            @OptionalParam(name = "_filter") StringParam theFilter,
            @IncludeParam(allow = { "ExplanationOfBenefit:coverage", "ExplanationOfBenefit:care-team", "ExplanationOfBenefit:provider" }) Set<Include> theIncludes

    ) {
        Map<String, Object> searchParams = new HashMap<>();

        // Add non-null parameters to the map
        if (theEoBId != null) searchParams.put(ExplanationOfBenefit.SP_RES_ID, theEoBId);
        if (theIdentifier != null) searchParams.put(ExplanationOfBenefit.SP_IDENTIFIER, theIdentifier);
        if (theStatus != null) searchParams.put(ExplanationOfBenefit.SP_STATUS, theStatus);
        if (theType != null) searchParams.put("type", theType);
        if (thePatient != null) searchParams.put(ExplanationOfBenefit.SP_PATIENT, thePatient);
        if (theCreatedDate != null) searchParams.put(ExplanationOfBenefit.SP_CREATED, theCreatedDate);
        if (theInsurer != null) searchParams.put("insurer", theInsurer);
        if (thePeriodStart != null) searchParams.put("periodstart", thePeriodStart); // Use custom periodstart parameter 
        if (thePeriodEnd != null) searchParams.put("periodend", thePeriodEnd);
        // if (theProvider != null) searchParams.put(ExplanationOfBenefit.SP_PROVIDER, theProvider);
        // if (theEnterer != null) searchParams.put(ExplanationOfBenefit.SP_ENTERER, theEnterer);
        // if (theFacility != null) searchParams.put(ExplanationOfBenefit.SP_FACILITY, theFacility);
        // if (theCareTeam != null) searchParams.put(ExplanationOfBenefit.SP_CARE_TEAM, theCareTeam);
        // if (theCoverage != null) searchParams.put(ExplanationOfBenefit.SP_COVERAGE, theCoverage);
        if (theFilter != null && !theFilter.isEmpty()) {
            searchParams.put("_filter", theFilter.getValue()); // Pass the filter string
        }

        System.out.println("Searching ExplanationOfBenefit with params: " + searchParams);
        // The service method will now also need to handle theIncludes
        return explanationOfBenefitService.searchExplanationOfBenefits(searchParams, theIncludes);
    }

    // TODO: Implement @Create, @Update, @Delete methods as needed
    // e.g.,
    // @Create
    // public MethodOutcome createExplanationOfBenefit(@ResourceParam ExplanationOfBenefit theExplanationOfBenefit) { ... }
}