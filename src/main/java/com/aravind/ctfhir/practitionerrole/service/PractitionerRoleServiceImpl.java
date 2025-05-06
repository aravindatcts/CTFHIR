package com.aravind.ctfhir.practitionerrole.service;

import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.server.SimpleBundleProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import com.aravind.ctfhir.model.PractitionerRoleEntity;
import com.aravind.ctfhir.practitionerrole.dao.PractitionerRoleDao;
import org.hl7.fhir.r4.model.*;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // Mark as a Spring service bean
public class PractitionerRoleServiceImpl implements PractitionerRoleService {

    private final PractitionerRoleDao practitionerRoleDao;

    // Spring will inject the PractitionerRoleDao bean
    public PractitionerRoleServiceImpl(PractitionerRoleDao practitionerRoleDao) {
        this.practitionerRoleDao = practitionerRoleDao;
    }

    @Override
    public PractitionerRole readPractitionerRole(IdType theId) {
        Optional<PractitionerRoleEntity> entityOpt = practitionerRoleDao.findById(theId.getIdPartAsLong());
        if (entityOpt.isPresent()) {
            return convertEntityToFhir(entityOpt.get());
        } else {
            throw new ResourceNotFoundException(theId);
        }
    }

    @Override
    public IBundleProvider searchPractitionerRoles(Map<String, Object> searchParams) {
        List<PractitionerRoleEntity> entities = practitionerRoleDao.findByParams(searchParams);
        List<PractitionerRole> practitionerRoles = entities.stream()
                .map(this::convertEntityToFhir)
                .collect(Collectors.toList());
        return new SimpleBundleProvider(practitionerRoles);
    }

    // --- Helper Methods ---

    private PractitionerRole convertEntityToFhir(PractitionerRoleEntity entity) {
        // TODO: Implement the full conversion logic from PractitionerRoleEntity to FHIR PractitionerRole
        PractitionerRole practitionerRole = new PractitionerRole();
        practitionerRole.setId(new IdType("PractitionerRole", entity.getId().toString()));
        // practitionerRole.setActive(entity.getActive());

        // if (entity.getPractitionerId() != null) {
        //     practitionerRole.setPractitioner(new Reference("Practitioner/" + entity.getPractitionerId()));
        // }
        // if (entity.getOrganizationId() != null) {
        //     practitionerRole.setOrganization(new Reference("Organization/" + entity.getOrganizationId()));
        // }
        // Map other fields (period, role, specialty, telecom, location, healthcareService, endpoint)...
        return practitionerRole;
    }

    // TODO: Implement convertFhirToEntity if needed for create/update
}