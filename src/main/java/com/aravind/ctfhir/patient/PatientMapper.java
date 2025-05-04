package com.aravind.ctfhir.patient; // Adjust package as needed

import com.aravind.ctfhir.model.PatientEntity; // Assuming your SAM entity is here
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Enumerations;
 
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.Identifier;

 
import java.util.Collections; // For returning singleton lists

// Use Mapper to convet from SAM to FHIR Patient

public class PatientMapper {

    /**
     * Maps a PatientEntity database object to a FHIR Patient resource.
     *
     * @param entity The PatientEntity object from the database.
     * @return A FHIR Patient resource object.
     */
    public Patient toFhirPatient(PatientEntity entity) {
        if (entity == null) {
            return null;
        }

        Patient patient = new Patient();

        // Set resource ID
        if (entity.getId() != null) {
            patient.setId(new IdType("Patient", entity.getId()));
        }

        // Set Identifier (assuming a system and value in your entity)
        // You might have multiple identifiers, adapt as needed
        if (entity.getSystemIdentifier() != null && entity.getValueIdentifier() != null) {
             Identifier identifier = patient.addIdentifier();
             identifier.setSystem(entity.getSystemIdentifier()); // e.g., "http://your-system/patient-ids"
             identifier.setValue(entity.getValueIdentifier());
        }


        // Set Name
        if (entity.getFirstName() != null || entity.getLastName() != null) {
            HumanName name = patient.addName();
            if (entity.getFirstName() != null) {
                name.addGiven(entity.getFirstName());
            }
            if (entity.getLastName() != null) {
                name.setFamily(entity.getLastName());
            }
            // Add prefix, suffix, etc. if available in entity
        }

        // Set Gender
        if (entity.getGender() != null) {
            // Assuming entity.getGender() returns a String like "male", "female", "other", "unknown"
            try {
                 patient.setGender(Enumerations.AdministrativeGender.fromCode(entity.getGender().toLowerCase()));
            } catch (Exception e) {
                 // Handle invalid gender code, maybe log a warning or set to UNKNOWN
                 patient.setGender(Enumerations.AdministrativeGender.UNKNOWN);
            }
        }

        // Set BirthDate
        if (entity.getBirthDate() != null) {
            patient.setBirthDate(entity.getBirthDate());
        }

        // Set Active status (assuming a boolean field in your entity)
        if (entity.getIsActive() != null) {
             patient.setActive(entity.getIsActive());
        }

        // Set Address (assuming fields like street, city, state, zip in your entity)
        // You might have multiple addresses, adapt as needed
        if (entity.getStreetAddress() != null || entity.getCity() != null || entity.getState() != null || entity.getZipCode() != null) {
            Address address = patient.addAddress();
            if (entity.getStreetAddress() != null) {
                address.addLine(entity.getStreetAddress());
            }
            if (entity.getCity() != null) {
                address.setCity(entity.getCity());
            }
            if (entity.getState() != null) {
                address.setState(entity.getState());
            }
            if (entity.getZipCode() != null) {
                address.setPostalCode(entity.getZipCode());
            }
            // Set country, use, type if available
        }

        // Set Telecom (assuming fields like phone, email in your entity)
        // You might have multiple telecom contacts, adapt as needed
        if (entity.getPhoneNumber() != null) {
            ContactPoint phone = patient.addTelecom();
            phone.setSystem(ContactPoint.ContactPointSystem.PHONE);
            phone.setValue(entity.getPhoneNumber());
            // Set use (e.g., HOME, WORK) if available
        }
         if (entity.getEmailAddress() != null) {
            ContactPoint email = patient.addTelecom();
            email.setSystem(ContactPoint.ContactPointSystem.EMAIL);
            email.setValue(entity.getEmailAddress());
             // Set use (e.g., HOME, WORK) if available
        }


        // Add other fields as needed (e.g., Marital Status, Contact, Communication, etc.)

        return patient;
    }

    /**
     * Maps a list of PatientEntity database objects to a list of FHIR Patient resources.
     *
     * @param entities The list of PatientEntity objects.
     * @return A list of FHIR Patient resource objects.
     */
    public java.util.List<Patient> toFhirPatients(java.util.List<PatientEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                       .map(this::toFhirPatient)
                       .collect(java.util.stream.Collectors.toList());
    }
}
