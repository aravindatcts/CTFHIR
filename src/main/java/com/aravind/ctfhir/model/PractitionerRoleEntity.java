package com.aravind.ctfhir.model;

import java.util.Date;
 

// Basic placeholder for a PractitionerRole entity.
// Expand based on your actual database schema.
public class PractitionerRoleEntity {

    private Long id;
    private Boolean active;
    private Date periodStart;
    private Date periodEnd;

    private Long practitionerId; // Foreign key to PractitionerEntity
    private Long organizationId; // Foreign key to OrganizationEntity

    // For 'code' (role)
    private String roleSystem;
    private String roleCode;
    private String roleDisplay;

    // For 'specialty'
    private String specialtySystem;
    private String specialtyCode;
    private String specialtyDisplay;

    // Simplified: store one telecom. In FHIR, this is a list.
    private String telecomSystem; // e.g., phone, email
    private String telecomValue;
    private String telecomUse;    // e.g., work, mobile

    // IDs for related resources (assuming you have entities for these)
    // private List<Long> locationIds;
    // private List<Long> healthcareServiceIds;
    // private List<Long> endpointIds;

    // Add getters and setters for all fields
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    // ... add the rest for all fields
}