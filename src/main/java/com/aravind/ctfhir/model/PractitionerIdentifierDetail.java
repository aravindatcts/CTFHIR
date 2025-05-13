package com.aravind.ctfhir.model;

public class PractitionerIdentifierDetail {
    private String practitionerId;
    private String practitionerSystem;
    private String practitionerUse;
    private String practitionerValue;
    private String practitionerTypeCode;
    private String practitionerTypeDisplay;
    private String practitionerAssinger; // As per schema

    // Constructors
    public PractitionerIdentifierDetail() {
    }

    public PractitionerIdentifierDetail(String practitionerId, String practitionerSystem, String practitionerUse, String practitionerValue, String practitionerTypeCode, String practitionerTypeDisplay, String practitionerAssinger) {
        this.practitionerId = practitionerId;
        this.practitionerSystem = practitionerSystem;
        this.practitionerUse = practitionerUse;
        this.practitionerValue = practitionerValue;
        this.practitionerTypeCode = practitionerTypeCode;
        this.practitionerTypeDisplay = practitionerTypeDisplay;
        this.practitionerAssinger = practitionerAssinger;
    }

    // Getters and Setters
    public String getPractitionerId() {
        return practitionerId;
    }

    public void setPractitionerId(String practitionerId) {
        this.practitionerId = practitionerId;
    }

    public String getPractitionerSystem() {
        return practitionerSystem;
    }

    public void setPractitionerSystem(String practitionerSystem) {
        this.practitionerSystem = practitionerSystem;
    }

    public String getPractitionerUse() {
        return practitionerUse;
    }

    public void setPractitionerUse(String practitionerUse) {
        this.practitionerUse = practitionerUse;
    }

    public String getPractitionerValue() {
        return practitionerValue;
    }

    public void setPractitionerValue(String practitionerValue) {
        this.practitionerValue = practitionerValue;
    }

    public String getPractitionerTypeCode() {
        return practitionerTypeCode;
    }

    public void setPractitionerTypeCode(String practitionerTypeCode) {
        this.practitionerTypeCode = practitionerTypeCode;
    }

    public String getPractitionerTypeDisplay() {
        return practitionerTypeDisplay;
    }

    public void setPractitionerTypeDisplay(String practitionerTypeDisplay) {
        this.practitionerTypeDisplay = practitionerTypeDisplay;
    }

    public String getPractitionerAssinger() {
        return practitionerAssinger;
    }

    public void setPractitionerAssinger(String practitionerAssinger) {
        this.practitionerAssinger = practitionerAssinger;
    }

    // toString (optional, for debugging)
}