package com.aravind.ctfhir.practitioner.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.aravind.ctfhir.model.Practitioner;

public interface PractitionerDao {

    /**
     * Finds a practitioner by its database ID.
     */
    Optional<Practitioner> findById(Long id);

    /**
     * Finds practitioners based on a map of search parameters.
     * Implement dynamic query building based on the map content.
     */
    List<Practitioner> findByParams(Map<String, Object> searchParams);

    // Add other methods like save, update, delete as needed

}