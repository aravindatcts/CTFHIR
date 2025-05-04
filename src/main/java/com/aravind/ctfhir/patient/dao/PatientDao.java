package com.aravind.ctfhir.patient.dao;


import com.aravind.ctfhir.model.PatientEntity;

import java.util.List;
import java.util.Optional;

public interface PatientDao {

    /**
     * Finds a patient by their database ID.
     *
     * @param id The database ID of the patient.
     * @return An Optional containing the PatientEntity if found, otherwise empty.
     */
    Optional<PatientEntity> findById(Long id);

    /**
     * Finds patients by their last name.
     *
     * @param lastName The last name to search for.
     * @return A list of matching PatientEntity objects.
     */
    List<PatientEntity> findByLastName(String lastName);

    // Add other methods as needed (e.g., findAll, save, update, delete)
}
