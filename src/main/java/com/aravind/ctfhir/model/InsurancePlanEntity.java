package com.aravind.ctfhir.model;

import java.util.Date;
import java.util.List;

// Basic placeholder for an InsurancePlan entity.
// This is a complex resource; expand based on your actual database schema.
public class InsurancePlanEntity {

    private Long id;
    private Boolean active; // Corresponds to InsurancePlan.status (active, draft, retired)
    private String name; // InsurancePlan.name
    private List<String> aliases; // InsurancePlan.alias

    // For 'type' (e.g., medical, dental)
    private String planTypeSystem;
    private String planTypeCode;
    private String planTypeDisplay;

    private Date periodStart; // InsurancePlan.period.start
    private Date periodEnd;   // InsurancePlan.period.end

    private Long ownedById; // Foreign key to OrganizationEntity (InsurancePlan.ownedBy)
    private Long administeredById; // Foreign key to OrganizationEntity (InsurancePlan.administeredBy)

    // Simplified representation of coverage areas (References to Location)
    // private List<Long> coverageAreaLocationIds;

    // Placeholder for plan details (could be a separate table or JSONB)
    // For InsurancePlan.plan.identifier, .type, .network, .generalCost, .specificCost
    private String planDetailIdentifierValue; // Example: a specific plan ID within the product
    private String planNetworkId; // Reference to an Organization (Network) entity

    // Placeholder for coverage details (could be a separate table or JSONB)
    // For InsurancePlan.coverage.type, .network, .benefit
    private String coverageBenefitTypeSystem; // e.g., medical, vision
    private String coverageBenefitTypeCode;
    private String coverageBenefitRequirement; // e.g., "Referral required"


    // Add getters and setters for all fields
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    // ... add the rest for all fields
}