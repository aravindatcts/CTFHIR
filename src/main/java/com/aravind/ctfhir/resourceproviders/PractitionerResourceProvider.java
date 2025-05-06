package com.aravind.ctfhir.resourceproviders;

import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.param.DateParam;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import com.aravind.ctfhir.practitioner.service.PractitionerService;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Practitioner;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PractitionerResourceProvider implements IResourceProvider {

    private final PractitionerService practitionerService;

    // Spring will inject the PractitionerService bean
    public PractitionerResourceProvider(PractitionerService practitionerService) {
        this.practitionerService = practitionerService;
    }

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return Practitioner.class;
    }

    /**
     * Handles GET requests for a specific Practitioner resource by ID.
     * Example: [base]/Practitioner/123
     */
    @Read()
    public Practitioner readPractitioner(@IdParam IdType theId) {
        System.out.println("Reading Practitioner with ID: " + theId.getIdPart());
        return practitionerService.readPractitioner(theId);
    }

    /**
     * Handles search requests for Practitioner resources.
     * Example: [base]/Practitioner?family=Smith&given=John
     */
    @Search
    public IBundleProvider searchPractitioners(
            @OptionalParam(name = Practitioner.SP_RES_ID) TokenParam thePractitionerId,
            @OptionalParam(name = Practitioner.SP_IDENTIFIER) TokenParam theIdentifier// e.g., NPI
           
            // Add other relevant search parameters (e.g., address, telecom, email, phone)
    ) {
        Map<String, Object> searchParams = new HashMap<>();

        // Add non-null parameters to the map
        if (thePractitionerId != null) searchParams.put(Practitioner.SP_RES_ID, thePractitionerId);
        if (theIdentifier != null) searchParams.put(Practitioner.SP_IDENTIFIER, theIdentifier);
      
      
       // if (theBirthDate != null) searchParams.put(Practitioner.SP_BIRTHDATE, theBirthDate);

        System.out.println("Searching Practitioner with params: " + searchParams);
        return practitionerService.searchPractitioners(searchParams);
    }

    // TODO: Implement @Create, @Update, @Delete methods as needed
}