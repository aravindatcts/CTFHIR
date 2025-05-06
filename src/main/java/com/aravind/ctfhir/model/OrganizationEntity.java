package com.aravind.ctfhir.model;

import java.util.List;

// Basic placeholder for an Organization entity.
// Expand based on your actual database schema for Organizations.
public class OrganizationEntity {

    private Long id;
    private Boolean active;
    private String typeSystem; // e.g., http://terminology.hl7.org/CodeSystem/organization-type
    private String typeCode;   // e.g., "prov", "dept", "ins", "pay", "edu", "reli", "crs", "cg", "other"
    private String name;
    private List<String> aliases; // Might be stored differently (e.g., separate table)
    private String telecomValue; // Simplified: store one telecom value
    private String telecomSystem; // e.g., phone, fax, email
    private String addressLine;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    // Add getters and setters for all fields
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    // ... add the rest
}