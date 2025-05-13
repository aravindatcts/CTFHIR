package com.aravind.ctfhir.model;

import java.util.Map;

public class Practitioner {
    private String practitionerId;
    private String practitionerText;
    private Map<String, PractitionerIdentifierDetail> practitionerIdentifier;
    private Boolean practitionerActive;
    private Map<String, Extension> extension;

    // Constructors
    public Practitioner() {
    }

    public Practitioner(String practitionerId, String practitionerText, Map<String, PractitionerIdentifierDetail> practitionerIdentifier, Boolean practitionerActive, Map<String, Extension> extension) {
        this.practitionerId = practitionerId;
        this.practitionerText = practitionerText;
        this.practitionerIdentifier = practitionerIdentifier;
        this.practitionerActive = practitionerActive;
        this.extension = extension;
    }

    // Getters and Setters
    public String getPractitionerId() {
        return practitionerId;
    }

    public void setPractitionerId(String practitionerId) {
        this.practitionerId = practitionerId;
    }

    public String getPractitionerText() {
        return practitionerText;
    }

    public void setPractitionerText(String practitionerText) {
        this.practitionerText = practitionerText;
    }

    public Map<String, PractitionerIdentifierDetail> getPractitionerIdentifier() {
        return practitionerIdentifier;
    }

    public void setPractitionerIdentifier(Map<String, PractitionerIdentifierDetail> practitionerIdentifier) {
        this.practitionerIdentifier = practitionerIdentifier;
    }

    public Boolean getPractitionerActive() {
        return practitionerActive;
    }

    public void setPractitionerActive(Boolean practitionerActive) {
        this.practitionerActive = practitionerActive;
    }

    public Map<String, Extension> getExtension() {
        return extension;
    }

    public void setExtension(Map<String, Extension> extension) {
        this.extension = extension;
    }

    // toString (optional, for debugging)
}