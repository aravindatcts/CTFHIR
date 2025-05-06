package com.aravind.ctfhir.insuranceplan.dao;

import com.aravind.ctfhir.model.InsurancePlanEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface InsurancePlanDao {

    /**
     * Finds an insurance plan by its database ID.
     */
    Optional<InsurancePlanEntity> findById(Long id);

    /**
     * Finds insurance plans based on a map of search parameters.
     * Implement dynamic query building based on the map content.
     */
    List<InsurancePlanEntity> findByParams(Map<String, Object> searchParams);

    // Add other methods like save, update, delete as needed

}