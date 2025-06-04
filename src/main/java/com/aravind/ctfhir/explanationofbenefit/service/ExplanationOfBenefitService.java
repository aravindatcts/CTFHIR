package com.aravind.ctfhir.explanationofbenefit.service;

import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import org.hl7.fhir.r4.model.ExplanationOfBenefit;
import org.hl7.fhir.r4.model.IdType;

import java.util.Map;
import java.util.Set;

public interface ExplanationOfBenefitService {

    /**
     * Reads an ExplanationOfBenefit resource by its ID.
     *
     * @param theId The ID of the ExplanationOfBenefit.
     * @return The ExplanationOfBenefit resource, or null if not found.
     */
    ExplanationOfBenefit readExplanationOfBenefit(IdType theId);

    /**
     * Searches for ExplanationOfBenefit resources based on the given parameters.
     *
     * @param searchParams A map of search parameters.
     * @param theIncludes  A set of _include parameters.
     * @return An IBundleProvider containing the search results.
     */
    IBundleProvider searchExplanationOfBenefits(Map<String, Object> searchParams, Set<Include> theIncludes);
}