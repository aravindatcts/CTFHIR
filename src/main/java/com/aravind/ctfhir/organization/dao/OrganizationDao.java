package com.aravind.ctfhir.organization.dao;

import com.aravind.ctfhir.model.OrganizationEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrganizationDao {

    /**
     * Finds an organization by its database ID.
     */
    Optional<OrganizationEntity> findById(Long id);

    /**
     * Finds organizations based on a map of search parameters.
     * Implement dynamic query building based on the map content.
     */
    List<OrganizationEntity> findByParams(Map<String, Object> searchParams);

    // Add other methods like save, update, delete as needed

}