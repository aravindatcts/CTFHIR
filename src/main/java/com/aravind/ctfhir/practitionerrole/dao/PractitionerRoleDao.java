package com.aravind.ctfhir.practitionerrole.dao;

import com.aravind.ctfhir.model.PractitionerRoleEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PractitionerRoleDao {

    /**
     * Finds a practitioner role by its database ID.
     */
    Optional<PractitionerRoleEntity> findById(Long id);

    /**
     * Finds practitioner roles based on a map of search parameters.
     * Implement dynamic query building based on the map content.
     */
    List<PractitionerRoleEntity> findByParams(Map<String, Object> searchParams);

    // Add other methods like save, update, delete as needed

}