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

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getAliases() { return aliases; }
    public void setAliases(List<String> aliases) { this.aliases = aliases; }

    public String getPlanTypeSystem() { return planTypeSystem; }
    public void setPlanTypeSystem(String planTypeSystem) { this.planTypeSystem = planTypeSystem; }

    public String getPlanTypeCode() { return planTypeCode; }
    public void setPlanTypeCode(String planTypeCode) { this.planTypeCode = planTypeCode; }

    public String getPlanTypeDisplay() { return planTypeDisplay; }
    public void setPlanTypeDisplay(String planTypeDisplay) { this.planTypeDisplay = planTypeDisplay; }

    public Date getPeriodStart() { return periodStart; }
    public void setPeriodStart(Date periodStart) { this.periodStart = periodStart; }

    public Date getPeriodEnd() { return periodEnd; }
    public void setPeriodEnd(Date periodEnd) { this.periodEnd = periodEnd; }

    public Long getOwnedById() { return ownedById; }
    public void setOwnedById(Long ownedById) { this.ownedById = ownedById; }

    public Long getAdministeredById() { return administeredById; }
    public void setAdministeredById(Long administeredById) { this.administeredById = administeredById; }

    public String getPlanDetailIdentifierValue() { return planDetailIdentifierValue; }
    public void setPlanDetailIdentifierValue(String planDetailIdentifierValue) { this.planDetailIdentifierValue = planDetailIdentifierValue; }

    public String getPlanNetworkId() { return planNetworkId; }
    public void setPlanNetworkId(String planNetworkId) { this.planNetworkId = planNetworkId; }

    public String getCoverageBenefitTypeSystem() { return coverageBenefitTypeSystem; }
    public void setCoverageBenefitTypeSystem(String coverageBenefitTypeSystem) { this.coverageBenefitTypeSystem = coverageBenefitTypeSystem; }

    public String getCoverageBenefitTypeCode() { return coverageBenefitTypeCode; }
    public void setCoverageBenefitTypeCode(String coverageBenefitTypeCode) { this.coverageBenefitTypeCode = coverageBenefitTypeCode; }

    public String getCoverageBenefitRequirement() { return coverageBenefitRequirement; }
    public void setCoverageBenefitRequirement(String coverageBenefitRequirement) { this.coverageBenefitRequirement = coverageBenefitRequirement; }
}