package com.aravind.ctfhir.resourceproviders;

import java.util.HashMap;
import java.util.Map;

import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Coverage;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.api.server.IBundleProvider; // For beneficiary search param
import ca.uhn.fhir.rest.param.ReferenceParam; // For payor search param
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.IResourceProvider;

@Service
public class CoverageResourceProvider implements IResourceProvider {

    // private final CoverageService coverageService;

    // public CoverageResourceProvider(CoverageService coverageService) {
    //     this.coverageService = coverageService;
    // }

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return Coverage.class;
    }

    /**
     * Handles GET requests for a specific Coverage resource by ID.
     * Example: [base]/Coverage/123
     */
    @Read()
    public Coverage readCoverage(@IdParam IdType theId) {
        // System.out.println("Reading Coverage with ID: " + theId.getIdPart());
        // return coverageService.readCoverage(theId);
        return null; // Placeholder for actual implementation
    }

    /**
     * Handles search requests for Coverage resources.
     * Example: [base]/Coverage?beneficiary=Patient/456&status=active
     */
    @Search
    public IBundleProvider searchCoverage(
            @OptionalParam(name = Coverage.SP_RES_ID) TokenParam theCoverageId,
            @OptionalParam(name = Coverage.SP_IDENTIFIER) TokenParam theIdentifier,
            @OptionalParam(name = Coverage.SP_STATUS) TokenParam theStatus,
            @OptionalParam(name = Coverage.SP_TYPE) TokenParam theType,
            @OptionalParam(name = Coverage.SP_PATIENT) ReferenceParam thePatientRef,  
            @OptionalParam(name = Coverage.SP_BENEFICIARY, chainWhitelist = { Patient.SP_IDENTIFIER, Patient.SP_NAME }) ReferenceParam theBeneficiary,
            @OptionalParam(name = Coverage.SP_PAYOR, chainWhitelist = { Organization.SP_IDENTIFIER, Organization.SP_NAME }) ReferenceParam thePayor,
            @OptionalParam(name = Coverage.SP_SUBSCRIBER) ReferenceParam theSubscriber,  
            @OptionalParam(name = Coverage.SP_POLICY_HOLDER) ReferenceParam thePolicyHolder, 
            @OptionalParam(name = Coverage.SP_CLASS_VALUE) StringParam theClassValue 
    ) {
        Map<String, Object> searchParams = new HashMap<>();

        // Add non-null parameters to the map
        if (theCoverageId != null) searchParams.put(Coverage.SP_RES_ID, theCoverageId);
        if (theIdentifier != null) searchParams.put(Coverage.SP_IDENTIFIER, theIdentifier);
        if (theStatus != null) searchParams.put(Coverage.SP_STATUS, theStatus);
        if (theType != null) searchParams.put(Coverage.SP_TYPE, theType);
        if (thePatientRef != null) searchParams.put(Coverage.SP_PATIENT, thePatientRef);
        if (theBeneficiary != null) searchParams.put(Coverage.SP_BENEFICIARY, theBeneficiary);
        if (thePayor != null) searchParams.put(Coverage.SP_PAYOR, thePayor);
        if (theSubscriber != null) searchParams.put(Coverage.SP_SUBSCRIBER, theSubscriber);
        if (thePolicyHolder != null) searchParams.put(Coverage.SP_POLICY_HOLDER, thePolicyHolder);
        if (theClassValue != null) searchParams.put(Coverage.SP_CLASS_VALUE, theClassValue);

        System.out.println("Searching Coverage with params: " + searchParams);
        //return coverageService.searchCoverage(searchParams);
        return null; // Placeholder for actual implementation
    }

    // TODO: Implement @Create, @Update, @Delete methods as needed
    // @Create
    // public MethodOutcome createCoverage(@ResourceParam Coverage theCoverage) { ... }

    // @Update
    // public MethodOutcome updateCoverage(@IdParam IdType theId, @ResourceParam Coverage theCoverage) { ... }
}