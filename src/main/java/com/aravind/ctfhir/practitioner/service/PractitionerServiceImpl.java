package com.aravind.ctfhir.practitioner.service;

import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.server.SimpleBundleProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import com.aravind.ctfhir.model.PractitionerEntity;
import com.aravind.ctfhir.practitioner.dao.PractitionerDao;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.IdType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // Mark as a Spring service bean
public class PractitionerServiceImpl implements PractitionerService {

    private final PractitionerDao practitionerDao;

    // Spring will inject the PractitionerDao bean (PractitionerDaoImpl)
    public PractitionerServiceImpl(PractitionerDao practitionerDao) {
        this.practitionerDao = practitionerDao;
    }

    @Override
    public Practitioner readPractitioner(IdType theId) {
        Optional<PractitionerEntity> entityOpt = practitionerDao.findById(theId.getIdPartAsLong());
        if (entityOpt.isPresent()) {
            return convertEntityToFhir(entityOpt.get());
        } else {
            throw new ResourceNotFoundException(theId);
        }
    }

    @Override
    public IBundleProvider searchPractitioners(Map<String, Object> searchParams) {
        List<PractitionerEntity> entities = practitionerDao.findByParams(searchParams);
        List<Practitioner> practitioners = entities.stream()
                .map(this::convertEntityToFhir)
                .collect(Collectors.toList());
        return new SimpleBundleProvider(practitioners);
    }

    // --- Helper Methods ---

    private Practitioner convertEntityToFhir(PractitionerEntity entity) {
        // TODO: Implement the full conversion logic from PractitionerEntity to FHIR Practitioner
        Practitioner practitioner = new Practitioner();
        practitioner.setId(new IdType("Practitioner", entity.getId().toString()));
       
        // Map other fields (active, gender, birthDate, identifier, telecom, address, etc.)...
        return practitioner;
    }

    // TODO: Implement convertFhirToEntity if needed for create/update
}