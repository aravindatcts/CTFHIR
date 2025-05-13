package com.aravind.ctfhir.model;

public class CodeableConceptDetail {
    private String code;
    private String display;

    // Constructors
    public CodeableConceptDetail() {
    }

    public CodeableConceptDetail(String code, String display) {
        this.code = code;
        this.display = display;
    }

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    // toString (optional, for debugging)
}
