package com.aravind.ctfhir.insuranceplan.service;

import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.server.SimpleBundleProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import com.aravind.ctfhir.model.InsurancePlanEntity;
import com.aravind.ctfhir.insuranceplan.dao.InsurancePlanDao;
import org.hl7.fhir.r4.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InsurancePlanServiceImpl implements InsurancePlanService {

    private final InsurancePlanDao insurancePlanDao;

    // Spring will inject the InsurancePlanDao bean
    public InsurancePlanServiceImpl(InsurancePlanDao insurancePlanDao) {
        this.insurancePlanDao = insurancePlanDao;
    }

    @Override
    public InsurancePlan readInsurancePlan(IdType theId) {
        Optional<InsurancePlanEntity> entityOpt = insurancePlanDao.findById(theId.getIdPartAsLong());
        if (entityOpt.isPresent()) {
            return convertEntityToFhir(entityOpt.get());
        } else {
            throw new ResourceNotFoundException(theId);
        }
    }

    @Override
    public IBundleProvider searchInsurancePlans(Map<String, Object> searchParams) {
        List<InsurancePlanEntity> entities = insurancePlanDao.findByParams(searchParams);
        List<InsurancePlan> insurancePlans = entities.stream()
                .map(this::convertEntityToFhir)
                .collect(Collectors.toList());
        return new SimpleBundleProvider(insurancePlans);
    }

    // --- Helper Methods ---

    private InsurancePlan convertEntityToFhir(InsurancePlanEntity entity) {
        // TODO: Implement the full conversion logic from InsurancePlanEntity to FHIR InsurancePlan
        InsurancePlan insurancePlan = new InsurancePlan();
        insurancePlan.setId(new IdType("InsurancePlan", entity.getId().toString()));

        // if (entity.getActive() != null) {
        //     insurancePlan.setStatus(entity.getActive() ? Enumerations.PublicationStatus.ACTIVE : Enumerations.PublicationStatus.RETIRED);
        //     // You might need more nuanced status mapping (e.g., to DRAFT)
        // }
        // insurancePlan.setName(entity.getName());

        // if (entity.getOwnedById() != null) {
        //     insurancePlan.setOwnedBy(new Reference("Organization/" + entity.getOwnedById()));
        // }
        // Map other fields (alias, type, period, administeredBy, coverageArea, plan, coverage, etc.)...
        return insurancePlan;
    }
 
}