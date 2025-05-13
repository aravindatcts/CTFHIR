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

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public String getFamilyName() { return familyName; }
    public void setFamilyName(String familyName) { this.familyName = familyName; }
    
    public String getGivenName() { return givenName; }
    public void setGivenName(String givenName) { this.givenName = givenName; }
    
    public String getPrefix() { return prefix; }
    public void setPrefix(String prefix) { this.prefix = prefix; }
    
    public String getSuffix() { return suffix; }
    public void setSuffix(String suffix) { this.suffix = suffix; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    
    public List<String> getIdentifierSystems() { return identifierSystems; }
    public void setIdentifierSystems(List<String> identifierSystems) { this.identifierSystems = identifierSystems; }
    
    public List<String> getIdentifierValues() { return identifierValues; }
    public void setIdentifierValues(List<String> identifierValues) { this.identifierValues = identifierValues; }
    
    public String getTelecomValue() { return telecomValue; }
    public void setTelecomValue(String telecomValue) { this.telecomValue = telecomValue; }
    
    public String getTelecomSystem() { return telecomSystem; }
    public void setTelecomSystem(String telecomSystem) { this.telecomSystem = telecomSystem; }
    
    public String getAddressLine() { return addressLine; }
    public void setAddressLine(String addressLine) { this.addressLine = addressLine; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}