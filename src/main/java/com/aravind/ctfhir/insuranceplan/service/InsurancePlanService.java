package com.aravind.ctfhir.insuranceplan.service;

 
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import org.hl7.fhir.r4.model.InsurancePlan;
import org.hl7.fhir.r4.model.IdType;

import java.util.Map;

public interface InsurancePlanService {

    /**
     * Reads an InsurancePlan resource by its ID.
     */
    InsurancePlan readInsurancePlan(IdType theId);

    /**
     * Searches for InsurancePlan resources based on the provided parameters.
     */
    IBundleProvider searchInsurancePlans(Map<String, Object> searchParams);

    // Add create, update, delete methods as needed
    // MethodOutcome createInsurancePlan(InsurancePlan theInsurancePlan);
    // MethodOutcome updateInsurancePlan(IdType theId, InsurancePlan theInsurancePlan);
}