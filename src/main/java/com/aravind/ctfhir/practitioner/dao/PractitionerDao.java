package com.aravind.ctfhir.practitioner.dao;

import com.aravind.ctfhir.model.PractitionerEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PractitionerDao {

    /**
     * Finds a practitioner by its database ID.
     */
    Optional<PractitionerEntity> findById(Long id);

    /**
     * Finds practitioners based on a map of search parameters.
     * Implement dynamic query building based on the map content.
     */
    List<PractitionerEntity> findByParams(Map<String, Object> searchParams);

    // Add other methods like save, update, delete as needed

}