package com.aravind.ctfhir.model; // Adjust package as needed

 
import java.util.Date;

/// Sample SAM PatientEntity class
public class PatientEntity {

    
    private Long id; // Use Long for auto-generated primary key
 
    private String systemIdentifier; // e.g., "http://your-system/patient-ids"

    
    private String valueIdentifier; // e.g., "patient-12345"

     
    private String firstName;

    
    private String lastName;

   
    private String gender; // e.g., "male", "female", "other", "unknown"
 
    private Date birthDate; // Use java.util.Date or java.time.LocalDate

    
    private Boolean isActive;

     
    private String streetAddress;

  
    private String city;
 
    private String state;
 
    private String zipCode;

 
    private String phoneNumber;
 
    private String emailAddress;

    // --- Constructors ---
    public PatientEntity() {
    }

    public PatientEntity(String systemIdentifier, String valueIdentifier, String firstName, String lastName, String gender, Date birthDate, Boolean isActive, String streetAddress, String city, String state, String zipCode, String phoneNumber, String emailAddress) {
        this.systemIdentifier = systemIdentifier;
        this.valueIdentifier = valueIdentifier;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.isActive = isActive;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    // --- Getters ---
    public Long getId() {
        return id;
    }

    public String getSystemIdentifier() {
        return systemIdentifier;
    }

    public String getValueIdentifier() {
        return valueIdentifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    // --- Setters ---
    public void setId(Long id) {
        this.id = id;
    }

    public void setSystemIdentifier(String systemIdentifier) {
        this.systemIdentifier = systemIdentifier;
    }

    public void setValueIdentifier(String valueIdentifier) {
        this.valueIdentifier = valueIdentifier;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // You might want to add equals() and hashCode() methods if using this in collections
    // You might also want to add a toString() method for easier debugging
}
