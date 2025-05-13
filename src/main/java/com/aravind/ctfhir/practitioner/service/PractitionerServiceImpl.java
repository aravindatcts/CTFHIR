package com.aravind.ctfhir.practitioner.service;

import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.server.SimpleBundleProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import com.aravind.ctfhir.model.Practitioner;
import com.aravind.ctfhir.practitioner.dao.PractitionerDao;
import org.hl7.fhir.r4.model.IdType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Optional<Practitioner> entityOpt = practitionerDao.findById(theId.getIdPartAsLong());
        if (entityOpt.isPresent()) {
            return entityOpt.get();
        } else {
            throw new ResourceNotFoundException(theId);
        }
    }

    @Override
    public IBundleProvider searchPractitioners(Map<String, Object> searchParams) {
        List<Practitioner> practitioners = practitionerDao.findByParams(searchParams);
        // List<Practitioner> practitioners = entities.stream()
        //         .map(this::convertEntityToFhir)
        //         .collect(Collectors.toList());
        List<SimpleBundleProvider> bundleProviders = new ArrayList<>();
        bundleProviders.addAll(practitioners);
        return bundleProviders;
    }

    // --- Helper Methods ---

    // private Practitioner convertEntityToFhir(Practitioner entity) {
    //     // TODO: Implement the full conversion logic from Practitioner to FHIR Practitioner
    //     Practitioner practitioner = new Practitioner();
    //     practitioner.setId(new IdType("Practitioner", entity.getId().toString()));
       
    //     // Map other fields (active, gender, birthDate, identifier, telecom, address, etc.)...
    //     return practitioner;
    // }

    // TODO: Implement convertFhirToEntity if needed for create/update
}