package com.aravind.ctfhir.model;
import java.util.Map;

public class Extension {
    private String url;
    private String key;
    private String system;
    private Map<String, CodeableConceptDetail> codableConcept;
    private String text;
    private String value;

    // Constructors
    public Extension() {
    }

    public Extension(String url, String key, String system, Map<String, CodeableConceptDetail> codableConcept, String text, String value) {
        this.url = url;
        this.key = key;
        this.system = system;
        this.codableConcept = codableConcept;
        this.text = text;
        this.value = value;
    }

    // Getters and Setters
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public Map<String, CodeableConceptDetail> getCodeableConcept() {
        return codableConcept;
    }

    public void setCodeableConcept(Map<String, CodeableConceptDetail> codableConcept) {
        this.codableConcept = codableConcept;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // toString (optional, for debugging)
}