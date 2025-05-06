package com.aravind.ctfhir.organization.service;

import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.server.SimpleBundleProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import com.aravind.ctfhir.model.OrganizationEntity;
import com.aravind.ctfhir.organization.dao.OrganizationDao;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.IdType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // Mark as a Spring service bean
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;

    // Spring will inject the OrganizationDao bean (OrganizationDaoImpl)
    public OrganizationServiceImpl(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @Override
    public Organization readOrganization(IdType theId) {
        Optional<OrganizationEntity> entityOpt = organizationDao.findById(theId.getIdPartAsLong());
        if (entityOpt.isPresent()) {
            return convertEntityToFhir(entityOpt.get());
        } else {
            throw new ResourceNotFoundException(theId);
        }
    }

    @Override
    public IBundleProvider searchOrganization(Map<String, Object> searchParams) {
        List<OrganizationEntity> entities = organizationDao.findByParams(searchParams);
        List<Organization> organizations = entities.stream()
                .map(this::convertEntityToFhir)
                .collect(Collectors.toList());
        return new SimpleBundleProvider(organizations);
    }

    // --- Helper Methods ---

    private Organization convertEntityToFhir(OrganizationEntity entity) {
       
        Organization organization = new Organization();
        organization.setId(new IdType("Organization", entity.getId().toString()));
        //organization.setName(entity.getName());
        // Map other fields (active, type, alias, telecom, address, etc.)...
        return organization;
    }
 
}