package com.aravind.ctfhir.model;

import java.util.Date;
import java.util.List;

// Basic placeholder for a Practitioner entity.
// Expand based on your actual database schema for Practitioners.
public class PractitionerEntity {

    private Long id;
    private Boolean active;
    private String familyName;
    private String givenName;
    private String prefix; // e.g., "Dr."
    private String suffix; // e.g., "MD"
    private String gender; // male | female | other | unknown
    private Date birthDate;
    private List<String> identifierSystems; // For NPI, etc.
    private List<String> identifierValues;
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
    // ... add the rest for all fields (active, familyName, givenName, etc.)
}