package com.aravind.ctfhir.resourceproviders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.stereotype.Service;

import com.aravind.ctfhir.model.USCorePatient;
import com.aravind.ctfhir.patient.service.PatientService;

import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.IncludeParam;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Sort;
import ca.uhn.fhir.rest.annotation.Update;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.param.DateParam;
import ca.uhn.fhir.rest.param.ReferenceParam;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.SimpleBundleProvider;  



@Service 
public class PatientResourceProvider implements IResourceProvider {

    private final PatientService patientService;  

    public PatientResourceProvider(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return Patient.class;
    }

    @Read()
    public Patient readPatient(@IdParam IdType theId) {
        System.out.println("Reading patient with ID: " + theId.getIdPart());
        return patientService.readPatient(theId);
    }


   	@Search(allowUnknownParams = true)
	public IBundleProvider findPatientsByParams(RequestDetails theRequestDetails,
			@OptionalParam(name = USCorePatient.SP_RES_ID) TokenParam thePatientId,
			@OptionalParam(name = USCorePatient.SP_IDENTIFIER) TokenParam thePatientIdentifier,
			@OptionalParam(name = USCorePatient.SP_ACTIVE) TokenParam theActive,
			@OptionalParam(name = USCorePatient.SP_FAMILY) StringParam theFamilyName,
			@OptionalParam(name = USCorePatient.SP_GIVEN) StringParam theGivenName,
			@OptionalParam(name = USCorePatient.SP_NAME) StringParam theName,
			@OptionalParam(name = USCorePatient.SP_GENDER) StringParam theGender,
			@OptionalParam(name = USCorePatient.SP_BIRTHDATE) DateParam theBirthDate,
			@OptionalParam(name = USCorePatient.SP_ADDRESS) StringParam theAddress,
			@OptionalParam(name = USCorePatient.SP_ADDRESS_CITY) StringParam theAddressCity,
			@OptionalParam(name = USCorePatient.SP_ADDRESS_STATE) StringParam theAddressState,
			@OptionalParam(name = USCorePatient.SP_ADDRESS_POSTALCODE) StringParam theAddressZip,
			@OptionalParam(name = USCorePatient.SP_EMAIL) TokenParam theEmail,
			@OptionalParam(name = USCorePatient.SP_PHONE) TokenParam thePhone,
			@OptionalParam(name = USCorePatient.SP_TELECOM) TokenParam theTelecom,
			@OptionalParam(name = USCorePatient.SP_ORGANIZATION, chainWhitelist = { "",
					Organization.SP_NAME }) ReferenceParam theOrganization,
			@Sort SortSpec theSort,

			@IncludeParam(allow = { "Patient:general-practitioner", "Patient:organization",
					"Patient:link" }) final Set<Include> theIncludes,

			@IncludeParam(allow = { "Encounter:subject",
					"Observation:subject" }, reverse = true) final Set<Include> theReverseIncludes) {

        System.out.println("Searching patients by family name: " + theFamilyName.getValue());



         // Create a map to hold the non-null parameters
        Map<String, Object> searchParams = new HashMap<>();

        // Convert all the non null parameters to a map
        // Add parameters to the map if they are not null
        if (thePatientId != null) searchParams.put(USCorePatient.SP_RES_ID, thePatientId);
        if (thePatientIdentifier != null) searchParams.put(USCorePatient.SP_IDENTIFIER, thePatientIdentifier);
        if (theActive != null) searchParams.put(USCorePatient.SP_ACTIVE, theActive);
        if (theFamilyName != null) searchParams.put(USCorePatient.SP_FAMILY, theFamilyName);
        if (theGivenName != null) searchParams.put(USCorePatient.SP_GIVEN, theGivenName);
        if (theName != null) searchParams.put(USCorePatient.SP_NAME, theName);
        if (theGender != null) searchParams.put(USCorePatient.SP_GENDER, theGender);
        if (theBirthDate != null) searchParams.put(USCorePatient.SP_BIRTHDATE, theBirthDate);
        if (theAddress != null) searchParams.put(USCorePatient.SP_ADDRESS, theAddress);
        if (theAddressCity != null) searchParams.put(USCorePatient.SP_ADDRESS_CITY, theAddressCity);
        if (theAddressState != null) searchParams.put(USCorePatient.SP_ADDRESS_STATE, theAddressState);
        if (theAddressZip != null) searchParams.put(USCorePatient.SP_ADDRESS_POSTALCODE, theAddressZip);
        if (theEmail != null) searchParams.put(USCorePatient.SP_EMAIL, theEmail);
        if (thePhone != null) searchParams.put(USCorePatient.SP_PHONE, thePhone);
        if (theTelecom != null) searchParams.put(USCorePatient.SP_TELECOM, theTelecom);
        if (theOrganization != null) searchParams.put(USCorePatient.SP_ORGANIZATION, theOrganization);

        // You can now use the 'searchParams' map, for example, pass it to the service layer
       
        // List<Patient> patients = patientService.findPatientsUsingArbitraryCtriteria(searchParams);

        // Convert all the non null parameters to a map

        List<Patient> patients = patientService.findPatientsByName(theFamilyName);
        return new SimpleBundleProvider(patients);
    }

    @Create()
    public MethodOutcome createPatient(@ResourceParam Patient thePatient) {
        return patientService.createPatient(thePatient);
    }

    @Update()
    public MethodOutcome updatePatient(@IdParam IdType theId, @ResourceParam Patient thePatient) {
        return patientService.updatePatient(theId, thePatient);
    }
}